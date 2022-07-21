package com.example.Elearning.Controllers;

import com.example.Elearning.DTOs.Request.*;

import com.example.Elearning.DTOs.Response.JwtRefreshResponse;
import com.example.Elearning.DTOs.Response.JwtResponse;
import com.example.Elearning.DTOs.Response.MessageResponse;
import com.example.Elearning.Models.UserModel.ERole;
import com.example.Elearning.Models.UserModel.Role;
import com.example.Elearning.Models.UserModel.User;
import com.example.Elearning.Security.Twilio.SmsRequest;
import com.example.Elearning.Security.Twilio.SmsService;
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

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired private SmsService smsService;
  @Autowired private ModelMapper mapper;
  @Autowired RoleService roleService;
  @Autowired LevelService levelService;
  @Autowired private SectionService sectionService;
  @Autowired
  UserService userService;

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
    if(userService.confirmed(loginForm.getUsername())==false){
      return new ResponseEntity<>("Account not aprooved",HttpStatus.NOT_ACCEPTABLE);
    }
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

    User user = mapper.map(signUpDto ,User.class);
    user.setPassword(encoder.encode(signUpDto.getPassword()));

    user.setLevel(levelService.getByid(signUpDto.getIdlevel()));
    user.setSection(sectionService.findById(signUpDto.getIdsection()));


    Role userRole = roleService.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    user.getRoles().add(userRole);

    user.setSmsCode( smsService.ConfirmAccout(signUpDto.getUsername()));

  /*  smsService.generateNewPassword(signUpDto.getUsername());*/
    userService.saveUser(user);

    return new ResponseEntity<>(new MessageResponse("le code de confirmation a ete envoyee a :"+signUpDto.getUsername()), HttpStatus.CREATED);
  }


  @PostMapping("/signup/prof")

  public ResponseEntity addProf(@Valid @RequestBody SignUpProf signUpDto){
      if (userService.existByEamilorPhoneNumber(signUpDto.getUsername(),signUpDto.getEmail())) {
          return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("PhoneNumber Or Email is already taken!"));
      }

      User user = mapper.map(signUpDto ,User.class);
      user.setPassword(encoder.encode(signUpDto.getPassword()));

      user.setLevel(levelService.getByid(signUpDto.getIdLevel()));

      Role userRole =roleService.findByName(ERole.ROLE_PROF)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      user.getRoles().add(userRole);
      user.setSmsCode( smsService.ConfirmAccout(signUpDto.getUsername()));
      userService.saveUser(user);
    return new ResponseEntity<>(new MessageResponse("Admin registered successfully!"), HttpStatus.CREATED);
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


  @PostMapping("/confirmsms")
  public ResponseEntity confirmAccountWithSms(@Valid @RequestBody ConfirmSms confirmSms){
    User user = userService.findByUsername(confirmSms.getPhoneNumber()).get();

    if(user ==null) return new ResponseEntity("Not valid PhoneNumber",HttpStatus.NOT_FOUND);
    User userFound = user;
    if(user.getSmsCode().equals(confirmSms.getCode())==true ){
      userFound.setConfirmed(true);
      userService.saveUser(userFound);
      return new ResponseEntity(new MessageResponse("succeded"),HttpStatus.OK);
    }

    return new ResponseEntity("Not valid code",HttpStatus.NOT_ACCEPTABLE);
  }

  @PostMapping("/resetpassword")
  public ResponseEntity ResetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto){
    Optional<User> user = userService.findByUsername(resetPasswordDto.getPhoneNumber());

    if(user ==null) return new ResponseEntity("Not valid PhoneNumber",HttpStatus.NOT_FOUND);
    User userFound = user.get();
    userFound.setPassword(encoder.encode(smsService.generateNewPassword(resetPasswordDto.getPhoneNumber())));
    userService.saveUser(userFound);

      return  ResponseEntity.ok().body(new MessageResponse("password changed "));


  }

}
