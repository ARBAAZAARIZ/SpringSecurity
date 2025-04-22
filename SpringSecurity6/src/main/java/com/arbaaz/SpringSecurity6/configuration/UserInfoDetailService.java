package com.arbaaz.SpringSecurity6.configuration;

import com.arbaaz.SpringSecurity6.model.UserInfo;
import com.arbaaz.SpringSecurity6.repository.UserInfoRepository;
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
public class UserInfoDetailService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo=userInfoRepository.findByName(username).orElseThrow(
                ()->{
                    return new UsernameNotFoundException("User not found");
                }
        );

        List<GrantedAuthority> authorities=List.of(new SimpleGrantedAuthority(userInfo.getRole()));

        return new User(userInfo.getName(),userInfo.getPwd(),authorities);
    }
}
