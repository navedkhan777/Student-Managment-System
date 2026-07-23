package Student.Mnaagment.System.Main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class OtpService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //Opt Generate
    public String generateOtp(String email){

        String otp = String.valueOf((int)(100000 + Math.random() * 9000000));

        redisTemplate.opsForValue()
                .set(email, otp, 60, TimeUnit.SECONDS);

        return otp;
    }

    //Verify Otp
    public boolean verifyOtp(String email, String otp){

        Object saveOtp = redisTemplate.opsForValue().get(email);

        if(saveOtp == null){
            return false;
        }

        return saveOtp.toString().equals(otp);
    }
}
