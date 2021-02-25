package com.cinherited.validationservice.controllers.impl;

import com.cinherited.validationservice.controllers.interfaces.IValidationController;
import com.cinherited.validationservice.dtos.AuthenticationRequest;
import com.cinherited.validationservice.dtos.AuthenticationResponse;
import com.cinherited.validationservice.dtos.ValidationDTO;
import com.cinherited.validationservice.security.MyUserDetailsService;
import com.cinherited.validationservice.services.interfaces.IValidationServices;
import com.cinherited.validationservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/validation")
public class ValidationController implements IValidationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;


    @Autowired
    IValidationServices validationServices;


    @PostMapping("/email")
    public boolean checkIsEmailValid(@RequestBody @Valid ValidationDTO validationDTO, @RequestHeader(value = "Authorization") String authorizationHeader) {
        return validationServices.checkIsEmailValid(validationDTO);
    }

    @PostMapping("/name")
    public boolean checkIsNameValid(@RequestBody @Valid ValidationDTO validationDTO, @RequestHeader(value = "Authorization") String authorizationHeader) {
        return validationServices.checkIsNameValid(validationDTO);
    }
    @PostMapping("/phone-number")
    public boolean checkIsPhoneNumberValid(@RequestBody @Valid ValidationDTO validationDTO, @RequestHeader(value = "Authorization") String authorizationHeader) {
        return validationServices.checkIsPhoneNumberValid(validationDTO);
    }

    @PostMapping("/country")
    public boolean checkIsCountryValid(@RequestBody @Valid ValidationDTO validationDTO, @RequestHeader(value = "Authorization") String authorizationHeader) {
        return validationServices.checkIsCountryValid(validationDTO);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
