package com.txt.security.registration.exception;

import com.txt.security.registration.common.component.ApiController;
import com.txt.security.registration.dto.common.APIResponseDTO;
import com.txt.security.registration.util.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandling extends ApiController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundExceptionHandle(ResourceNotFoundException ex) {
        APIResponseDTO<?> responseDTO = ApiUtil.status404(serviceName, ex.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessRuntimeException.class)
    public ResponseEntity<Object> businessRuntimeExceptionHandle(BusinessRuntimeException ex) {
        APIResponseDTO<?> responseDTO = ApiUtil.status500(serviceName, ex.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ex.printStackTrace();
        // Handle the exception
        APIResponseDTO<?> responseDTO = ApiUtil.status500(serviceName, ex.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> resourceNotFoundExceptionHandle(MethodArgumentNotValidException ex) {
        APIResponseDTO<?> responseDTO = ApiUtil.status400(serviceName, ex.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleThrowable(Throwable t) {
        t.printStackTrace();
        // Handle any other unhandled exception
        APIResponseDTO<?> responseDTO = ApiUtil.status500(serviceName, t.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
