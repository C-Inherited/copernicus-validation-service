package com.cinherited.validationservice.services.impl;

import com.cinherited.validationservice.dtos.ValidationDTO;
import com.cinherited.validationservice.enums.ValidationType;
import com.cinherited.validationservice.services.interfaces.IValidationServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

@Service
public class ValidationServices implements IValidationServices {
    @Override
    public boolean checkIsEmailValid(ValidationDTO validationDTO) {
        if (validationDTO.getValidationType() != ValidationType.EMAIL) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (validationDTO.getStrToValidate() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"+
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        return (pattern.matcher(validationDTO.getStrToValidate()).find());
    }
}
