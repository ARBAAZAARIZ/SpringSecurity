package com.arbaaz.SpringSecurity11_1.config;

import com.arbaaz.SpringSecurity11_1.model.NexusCustomer;
import com.arbaaz.SpringSecurity11_1.repository.NexusCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NexusBankUserDetailService implements UserDetailsService {

    private final NexusCustomerRepository nexusCustomerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

           Optional<NexusCustomer> requestedCustomer= nexusCustomerRepository.findByEmail(username);

           if(requestedCustomer.isPresent()){
               NexusCustomer customer=requestedCustomer.get();

               System.out.println("Customer is "+customer);

               System.out.println("Authorities are "+customer.getAuthorities());

               List<GrantedAuthority> authorities=customer.getAuthorities().stream().map(auth->
                       new SimpleGrantedAuthority(auth.getName())).collect(Collectors.toList());
               return new User(customer.getEmail(), customer.getPwd(), authorities);

           }else{
               throw new UsernameNotFoundException("User not found with email: " + username);
           }



    }
}
