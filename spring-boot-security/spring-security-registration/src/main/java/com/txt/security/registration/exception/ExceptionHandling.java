package com.txt.security.registration.exception;

import com.txt.security.registration.dto.common.APIResponseDTO;
import com.txt.security.registration.util.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling extends BaseExceptionHandling {

    @ExceptionHandler(InvalidCredentialException.class)
    ResponseEntity<Object> invalidCredentialExceptionExceptionHandle(InvalidCredentialException ex) {
        APIResponseDTO<?> responseDTO = ApiUtil.status400(serviceName, ex.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    ResponseEntity<Object> unauthorizedExceptionHandle(UnauthorizedException ex) {
        APIResponseDTO<?> responseDTO = ApiUtil.status401(serviceName, ex.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
    }
}
