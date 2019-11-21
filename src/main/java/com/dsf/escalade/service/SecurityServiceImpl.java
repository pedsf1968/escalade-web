package com.dsf.escalade.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {
   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private MyUserDetailsService userDetailsService;


   @Override
   public String findLoggedInUsername() {
      Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
      if (userDetails instanceof UserDetails) {
         return ((UserDetails)userDetails).getUsername();
      }

      return null;
   }

   @Override
   public void autoLogin(String username, String password) {
      UserDetails userDetails = null;
      userDetails = userDetailsService.loadUserByUsername(username);

      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

      authenticationManager.authenticate(usernamePasswordAuthenticationToken);


      if (usernamePasswordAuthenticationToken.isAuthenticated()) {
         SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
         log.debug(String.format("Auto login %s successfully!", username));
      }
   }
}
