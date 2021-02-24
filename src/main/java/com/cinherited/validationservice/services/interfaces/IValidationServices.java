package com.cinherited.validationservice.services.interfaces;

import com.cinherited.validationservice.dtos.ValidationDTO;

public interface IValidationServices {
    boolean checkIsEmailValid(ValidationDTO validationDTO);

    boolean checkIsNameValid(ValidationDTO validationDTO);

    boolean checkIsPhoneNumberValid(ValidationDTO validationDTO);

    boolean checkIsCountryValid(ValidationDTO validationDTO);
}
