package com.arbaaz.SpringSecurity11.filter;

import com.arbaaz.SpringSecurity11.contants.ApplicationConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {
    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwtToken=request.getHeader(ApplicationConstants.JWT_HEADER);

        System.out.println("Raw JWT Token: " + jwtToken);
        System.out.println("Authorization Header: " + request.getHeader("Authorization"));

        System.out.println(jwtToken);
        System.out.println("JWT Token in Request Header: " + request.getHeader("Authorization"));

        if(null!=jwtToken){
            try{
                Environment evn=getEnvironment();
                if(null!=evn){
                    String secret=evn.getProperty(ApplicationConstants.JWT_SECRET_KEY,ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                    SecretKey secretKey= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                    if(null!=secretKey){
                        Claims claims=Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwtToken).getPayload();
                        String username=String.valueOf(claims.get("username"));
                        String authorities=String.valueOf(claims.get("authorities"));
                        Authentication authentication=new UsernamePasswordAuthenticationToken(username,null,
                                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                        System.out.println(authentication.getName() + " from JWTTokenValidatorFilter");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }


            }catch (Exception ex){
                throw new BadCredentialsException("Invalid Token Received!");
            }
        }

        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/auth/user");
    }
}
