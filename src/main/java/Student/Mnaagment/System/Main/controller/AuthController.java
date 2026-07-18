package Student.Mnaagment.System.Main.controller;

import Student.Mnaagment.System.Main.Entity.AppUser;
import Student.Mnaagment.System.Main.dto.LoginRequest;
import Student.Mnaagment.System.Main.repository.UserRepo;
import Student.Mnaagment.System.Main.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){

        System.out.println("STEP 1");

        System.out.println("USERNAME = " + request.getUsername());

        System.out.println("PASSWORD = " + request.getPassword());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        System.out.println("STEP 2");

        String token =  jwtService.generateToken(
                request.getUsername()
        );
        return token;
    }

    @PostMapping("/register")
   public String register(@RequestBody AppUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()
                )
        );
      userRepo.save(user);
      return "User Registered";
   }
}
