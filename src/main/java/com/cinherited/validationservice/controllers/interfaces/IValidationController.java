package com.cinherited.validationservice.controllers.interfaces;

import com.cinherited.validationservice.dtos.ValidationDTO;

public interface IValidationController {

    boolean checkIsEmailValid(ValidationDTO validationDTO);
}
