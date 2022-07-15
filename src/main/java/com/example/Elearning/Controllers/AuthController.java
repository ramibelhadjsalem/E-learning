package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.*;

import com.example.Elearning.DTOs.Response.JwtRefreshResponse;
import com.example.Elearning.DTOs.Response.JwtResponse;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.Models.LevelModel.Level;
import com.example.Elearning.Models.UserModel.ERole;
import com.example.Elearning.Models.UserModel.Role;
import com.example.Elearning.Models.UserModel.User;
import com.example.Elearning.Security.serviceUser.UserDetailServiceImpl;
import com.example.Elearning.Security.serviceUser.UserDetailsImpl;
import com.example.Elearning.Security.serviceUser.jwt.JwtUtils;
import com.example.Elearning.Services.LevelServices.LevelService;
import com.example.Elearning.Services.SubjectServices.SectionService;
import com.example.Elearning.Services.Userservices.RoleService;
import com.example.Elearning.Services.Userservices.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired private ModelMapper mapper ;
  @Autowired
  UserService userService;
  @Autowired
  RoleService roleService;
  @Autowired private LevelService levelService;
  @Autowired private SectionService sectionServic;
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  PasswordEncoder encoder;
  @Autowired
  JwtUtils jwtUtils;
  @Value("${jwtSecret}")
  private String jwtSecret;

  @Value("${jwtExpirationMs}")
  private int jwtExpirationMs;
  @Autowired private UserDetailServiceImpl userDetailService;

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
  public ResponseEntity addUser(@Valid @RequestBody SignupUser signUpDto){
    if (userService.existsByPhoneNumber(signUpDto.getPhoneNumber())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: phoneNumber is already taken!"));
    }
    User user = mapper.map(signUpDto ,User.class);
    user.setPassword(encoder.encode((signUpDto.getPassword())));
    user.setUsername(signUpDto.getPhoneNumber());

    user.setLevel(levelService.getByid(signUpDto.getIdlevel()));
    user.setSection(sectionServic.findById(signUpDto.getIdsection()));

    Role userRole = roleService.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    user.getRoles().add(userRole);
    userService.saveUser(user);
    return new ResponseEntity<>(new MessageResponse("User registered successfully!"), HttpStatus.CREATED);
  }

  /*@PostMapping("/signup/admin")

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
  }*/

  @PostMapping("/signup/prof")

  public ResponseEntity addProf(@Valid @RequestBody SignUpProf signUpDto){

    if (userService.exitsByEmailOrPhoneNumber(signUpDto.getEmail(),signUpDto.getPhoneNumber())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: email or phoneNumber is already taken!"));
    }
    User user = mapper.map(signUpDto ,User.class);
    user.setPassword(encoder.encode((signUpDto.getPassword())));
    user.setUsername(signUpDto.getPhoneNumber());

    user.setLevel(levelService.getByid(signUpDto.getIdlevel()));

    Role userRole = roleService.findByName(ERole.ROLE_PROF)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    user.getRoles().add(userRole);

    userService.saveUser(user);


    return new ResponseEntity<>(new MessageResponse("profisseur registered successfully!"), HttpStatus.CREATED);
  }
  @PostMapping("/refreshtoken") // Todo:RefreshToken
  public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenDto refreshToken){
    if(jwtUtils.validateJwtToken(refreshToken.getRefreshToken())==false)
          return new ResponseEntity<>(new MessageResponse("refreshToken not valid"),HttpStatus.BAD_REQUEST);


    UserDetailsImpl userPrincipal = (UserDetailsImpl) userDetailService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(refreshToken.getRefreshToken()));
    String token = Jwts.builder()
            .setSubject((userPrincipal.getUsername()))
            .claim("roles",userPrincipal.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();


    return new ResponseEntity<>(new JwtRefreshResponse(token),HttpStatus.OK);
  }
}
