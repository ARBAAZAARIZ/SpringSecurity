package com.arbaaz.SpringSecurity11.config;

//import com.arbaaz.SpringSecurity11.model.NexusCustomer;
//import com.arbaaz.SpringSecurity11.repository.NexusCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//import java.util.List;

@Component
public class NexusCustomAuthenticationProvider implements AuthenticationProvider {


//    @Autowired
//    NexusCustomerRepository nexusCustomerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    NexusCustomerUserDetailService nexusCustomerUserDetailService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email= authentication.getName();
        System.out.println(email);
        String password=authentication.getCredentials().toString();

        // Retrieve the user (using your custom repository)
        UserDetails userDetails=nexusCustomerUserDetailService.loadUserByUsername(email);

        System.out.println(email + " from AuthenticationProvider");

        // Validate password (assuming your passwords are encoded)
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }

//        // Create an authenticated token with granted authorities
//        List<SimpleGrantedAuthority> authorities=List.of(new SimpleGrantedAuthority(userDetails.getRole()));

        // Create an authenticated token with granted authorities
        return new UsernamePasswordAuthenticationToken(email,null,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Return true if the authentication type is UsernamePasswordAuthenticationToken
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }



//    What’s happening?
//
//The provider extracts the email and password.
//
//It queries your repository (or other data source) to fetch the user.
//
//It checks the password using passwordEncoder.
//
//Finally, it creates an authenticated UsernamePasswordAuthenticationToken with the user’s role(s).
}
