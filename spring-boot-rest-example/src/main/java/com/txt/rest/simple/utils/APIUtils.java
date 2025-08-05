package com.txt.rest.simple.utils;

import com.txt.rest.simple.constant.Constants;
import com.txt.rest.simple.dto.common.APIStandardResponseDTO;
import com.txt.rest.simple.dto.common.ErrorDTO;
import com.txt.rest.simple.dto.common.ResponseCode;
import com.txt.rest.simple.dto.common.ResponseStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

public class APIUtils {

    public static String getExchangeId(String exchangeId) {
        if (StringUtils.isBlank(exchangeId)) {
            return UUID.randomUUID().toString();
        }
        return exchangeId;
    }

    public static <T> APIStandardResponseDTO<T> statusSuccess(T data) {
        APIStandardResponseDTO<T> PVAStandardResponseDTO = new APIStandardResponseDTO<>();
        PVAStandardResponseDTO.setData(data);
        return PVAStandardResponseDTO;
    }

    public static <T> APIStandardResponseDTO<T> statusError(String code, String message) {
        APIStandardResponseDTO<T> PVAStandardResponseDTO = new APIStandardResponseDTO<>();
        PVAStandardResponseDTO.setStatus(Constants.FAILED);
        PVAStandardResponseDTO.setResponseStatus(ResponseStatus.builder().code(code).message(message).build());
        return PVAStandardResponseDTO;
    }

    public static <T> APIStandardResponseDTO<T> statusError(String message) {
        APIStandardResponseDTO<T> PVAStandardResponseDTO = new APIStandardResponseDTO<>();
        PVAStandardResponseDTO.setStatus(Constants.FAILED);
        PVAStandardResponseDTO.setResponseStatus(ResponseStatus.builder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(message)
                .build());
        return PVAStandardResponseDTO;
    }

    public static <T> APIStandardResponseDTO<T> statusError() {
        APIStandardResponseDTO<T> PVAStandardResponseDTO = new APIStandardResponseDTO<>();
        PVAStandardResponseDTO.setStatus(Constants.FAILED);
        PVAStandardResponseDTO.setResponseStatus(ResponseStatus.builder()
                .code(String.valueOf(ResponseCode.FAILED.getCode()))
                .message(String.valueOf(ResponseCode.FAILED.getDescription()))
                .build());
        return PVAStandardResponseDTO;
    }

    public static <T> APIStandardResponseDTO<T> statusError(String code, String message, List<ErrorDTO> errorDTOS) {
        APIStandardResponseDTO<T> PVAStandardResponseDTO = new APIStandardResponseDTO<>();
        PVAStandardResponseDTO.setStatus(Constants.FAILED);
        PVAStandardResponseDTO.setResponseStatus(ResponseStatus.builder()
                .code(code).message(message).errorDTOs(errorDTOS)
                .build());
        return PVAStandardResponseDTO;
    }

    public static <T> APIStandardResponseDTO<T> statusError(ResponseCode responseCode) {
        APIStandardResponseDTO<T> PVAStandardResponseDTO = new APIStandardResponseDTO<>();
        PVAStandardResponseDTO.setStatus(Constants.FAILED);
        PVAStandardResponseDTO.setResponseStatus(ResponseStatus.builder()
                .code(String.valueOf(responseCode.getCode()))
                .message(String.valueOf(responseCode.getDescription()))
                .build());
        return PVAStandardResponseDTO;
    }

}
