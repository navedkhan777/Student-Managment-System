package Student.Mnaagment.System.Main.controller;

import Student.Mnaagment.System.Main.service.JwtService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class JwtTestController {

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public String testToken(){

         String token = jwtService.generateToken("admin");

         return jwtService.extractUsername(token);
    }
}
