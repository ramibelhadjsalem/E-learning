package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.SignUpDto;
import com.example.Elearning.DTOs.Request.LoginForm;

import com.example.Elearning.DTOs.Response.JwtRefreshResponse;
import com.example.Elearning.DTOs.Response.JwtResponse;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.Models.ERole;
import com.example.Elearning.Models.Role;
import com.example.Elearning.Models.User;
import com.example.Elearning.Repositorys.RoleRpository;
import com.example.Elearning.Repositorys.UserRepository;
import com.example.Elearning.Security.serviceUser.UserDetailsImpl;
import com.example.Elearning.Security.serviceUser.jwt.JwtUtils;
import com.example.Elearning.Services.RoleService;
import com.example.Elearning.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  UserService userService;
  @Autowired
  RoleService roleService;
  @Autowired
  AuthenticationManager authenticationManager;
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
    String refreshJwt = jwtUtils.generateRefreshJwtToken(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(
                         jwt,
                         refreshJwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }
  @PostMapping("/signup/user")
  public ResponseEntity addUser(@Valid @RequestBody SignUpDto signUpDto){
    if (userService.existsByUsername(signUpDto.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }
    User user = new User(signUpDto.getUsername(),
            signUpDto.getEmail(),
            encoder.encode(signUpDto.getPassword()));

   /* Role userRole = roleRpository.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));*/
    Role userRole = roleService.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    user.getRoles().add(userRole);
    userService.saveUser(user);
    return new ResponseEntity<>(new MessageResponse("User registered successfully!"), HttpStatus.CREATED);
  }

  @PostMapping("/signup/admin")

  public ResponseEntity addAdmin(@Valid @RequestBody SignUpDto signUpDto){
    if (userService.existsByUsername(signUpDto.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }
    User user = new User(signUpDto.getUsername(),
            signUpDto.getEmail(),
            encoder.encode(signUpDto.getPassword()));

    Role userRole =roleService.findByName(ERole.ROLE_ADMIN)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    user.getRoles().add(userRole);
    userService.saveUser(user);
    return new ResponseEntity<>(new MessageResponse("Admin registered successfully!"), HttpStatus.CREATED);
  }

  @PostMapping("/signup/prof")

  public ResponseEntity addProf(@Valid @RequestBody SignUpDto signUpDto){
    if (userService.existsByUsername(signUpDto.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }
    User user = new User(signUpDto.getUsername(),
            signUpDto.getEmail(),
            encoder.encode(signUpDto.getPassword()));

    Role userRole = roleService.findByName(ERole.ROLE_PROF)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    user.getRoles().add(userRole);
    userService.saveUser(user);
    return new ResponseEntity<>(new MessageResponse("profisseur registered successfully!"), HttpStatus.CREATED);
  }
  @PostMapping("/refreshtoken") // Todo:RefreshToken
  public ResponseEntity<?> refreshToken(@NotNull @RequestBody String refreshToken){
    if(jwtUtils.validateJwtToken(refreshToken)==false) return new ResponseEntity<>(new MessageResponse("refreshToken not valid"),HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(new JwtRefreshResponse("fezfzefzefe"),HttpStatus.UNAUTHORIZED);
  }
}
