package com.cinherited.validationservice.controllers.impl;

import com.cinherited.validationservice.controllers.interfaces.IValidationController;
import com.cinherited.validationservice.dtos.ValidationDTO;
import com.cinherited.validationservice.services.interfaces.IValidationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/validation")
public class ValidationController implements IValidationController {

    @Autowired
    IValidationServices validationServices;


    @GetMapping("/email")
    public boolean checkIsEmailValid(@RequestBody @Valid ValidationDTO validationDTO) {
        return validationServices.checkIsEmailValid(validationDTO);
    }

    @GetMapping("/name")
    public boolean checkIsNameValid(@RequestBody @Valid ValidationDTO validationDTO) {
        return true;
    }
    @GetMapping("/phone-number")
    public boolean checkIsPhoneNumberValid(@RequestBody @Valid ValidationDTO validationDTO) {
        return true;
    }

    @GetMapping("/country")
    public boolean checkIsCountryValid(@RequestBody @Valid ValidationDTO validationDTO) {
        return true;
    }

}
