package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.LoginForm;
import com.example.Elearning.DTOs.Request.SignupRequest;
import com.example.Elearning.DTOs.Response.JwtResponse;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.Models.ERole;
import com.example.Elearning.Models.Role;
import com.example.Elearning.Models.User;
import com.example.Elearning.Repositorys.RoleRpository;
import com.example.Elearning.Repositorys.UserRepository;
import com.example.Elearning.Security.services.UserDetailsImpl;
import com.example.Elearning.Security.services.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRpository roleRpository ;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles.isEmpty()) {
      Role userRole = roleRpository.findByName(String.valueOf(ERole.ROLE_ADMIN))
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRpository.findByName(String.valueOf(ERole.ROLE_ADMIN))
              .orElseThrow(() -> new RuntimeException("Error:admin Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRpository.findByName(String.valueOf(ERole.ROLE_MODERATOR))
              .orElseThrow(() -> new RuntimeException("Error:moderator Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRpository.findByName(String.valueOf(ERole.ROLE_USER))
              .orElseThrow(() -> new RuntimeException("Error:user Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
