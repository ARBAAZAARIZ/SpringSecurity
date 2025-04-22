package com.arbaaz.SpringSecurity8.config;

import com.arbaaz.SpringSecurity8.model.NexusCustomer;
import com.arbaaz.SpringSecurity8.repository.NexusCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NexusBankUserDetailsService implements UserDetailsService {
    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */

    @Autowired
    private NexusCustomerRepository nexusCustomerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        NexusCustomer nexusCustomer=nexusCustomerRepository.findByEmail(username).orElseThrow(
                ()-> new UsernameNotFoundException("User don't Exist with username " + username)
        );

        List<GrantedAuthority> authorities=List.of(new SimpleGrantedAuthority(nexusCustomer.getRole()));

        return new User(nexusCustomer.getEmail(),nexusCustomer.getPwd(),authorities);
    }
}
