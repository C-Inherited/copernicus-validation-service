package com.cinherited.validationservice.controllers.interfaces;

import com.cinherited.validationservice.dtos.ValidationDTO;
import org.springframework.web.bind.annotation.RequestHeader;

public interface IValidationController {

    boolean checkIsEmailValid(ValidationDTO validationDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    boolean checkIsNameValid(ValidationDTO validationDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    boolean checkIsPhoneNumberValid(ValidationDTO validationDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    boolean checkIsCountryValid(ValidationDTO validationDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

}
