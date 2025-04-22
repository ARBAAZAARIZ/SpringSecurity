package com.arbaaz.SpringSecurity5.config;

import com.arbaaz.SpringSecurity5.model.AdminUser;
import com.arbaaz.SpringSecurity5.repository.AdminUserRepository;
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
public class SpringSecurityUserDetailsManager implements UserDetailsService {


    @Autowired
    AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      AdminUser adminUser= adminUserRepository.findByName(username).orElseThrow(
                ()->{
                    throw new UsernameNotFoundException("User not found"+username);
                }
        );

        List<GrantedAuthority> authorities=List.of(new SimpleGrantedAuthority(adminUser.getRole()));

        return new User(adminUser.getName(), adminUser.getPwd(), authorities);
    }
}
