package com.arbaaz.SpringSecurity9.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationEvents {


    public void onFailure(AuthorizationDeniedEvent deniedEvent){
        log.error("Authorization denied :{}: due to : {} :",deniedEvent.getAuthentication().get().getName(),
                deniedEvent.getAuthorizationResult().toString());
    }

}
