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

    @Override
    public boolean checkIsNameValid(ValidationDTO validationDTO) {
        if (validationDTO.getValidationType() != ValidationType.NAME) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (validationDTO.getStrToValidate() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (!validationDTO.getStrToValidate().trim().contains(" ")) return false;
        else {
            char[] chars = validationDTO.getStrToValidate().toCharArray();
            for (char c : chars) if (Character.isDigit(c)) return false;
        }
        return true;
    }

    @Override
    public boolean checkIsPhoneNumberValid(ValidationDTO validationDTO) {
        return validationDTO.getStrToValidate().matches("\\d{9}") && (validationDTO.getStrToValidate().charAt(0) == '6' || validationDTO.getStrToValidate().charAt(0) == '9');
    }

    @Override
    public boolean checkIsCountryValid(ValidationDTO validationDTO) {
        char[] chars = validationDTO.getStrToValidate().toCharArray();
        for (char c : chars) if (Character.isDigit(c)) return false;
        return true;
    }
}