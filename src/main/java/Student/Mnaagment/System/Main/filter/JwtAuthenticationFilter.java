package Student.Mnaagment.System.Main.filter;

import Student.Mnaagment.System.Main.config.CustomUserDetailsService;
import Student.Mnaagment.System.Main.repository.UserRepo;
import Student.Mnaagment.System.Main.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
        throws ServletException, IOException{

        String authHeader = request.getHeader("Authorization");

        System.out.println("Header = "+ authHeader);

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);

            System.out.println("TOKEN = " + token);

            String username = jwtService.extractUsername(token);
            System.out.println("USERNAME = " + username);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            System.out.println("USER FOUND = " + userDetails.getUsername());


            UsernamePasswordAuthenticationToken  authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }
}
