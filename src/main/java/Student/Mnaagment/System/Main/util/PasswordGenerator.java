package Student.Mnaagment.System.Main.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator implements CommandLineRunner{

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void run(String...args){

        String hash = passwordEncoder.encode("admin123");

        System.out.println("HASH = " + hash);

        System.out.println(
            "MATCH = " +
                    passwordEncoder.matches(
                            "admin123",
                            hash
                    )
        );
    }
}
