package com.txt.security.registration.util;

import com.txt.security.registration.dto.common.APIResponseDTO;
import com.txt.security.registration.dto.common.MessageCode;
import com.txt.security.registration.dto.common.APIMultipleResponseDTO;
import com.txt.security.registration.dto.common.ResponseCode;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiUtil {

    public static <T> APIResponseDTO<T> status200(T data) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setData(data);
        APIResponseDTO.setCode(String.valueOf(HttpStatus.OK.value()));
        APIResponseDTO.setMessage("SUCCESS");
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> status200(String serviceName, T data) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setData(data);
        APIResponseDTO.setCode(serviceName.concat("_").concat(String.valueOf(HttpStatus.OK.value())));
        APIResponseDTO.setMessage("SUCCESS");
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> status200(String serviceName, T data, MessageCode messageCode) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setData(data);
        APIResponseDTO.setCode(messageCode.getCode());
        APIResponseDTO.setMessage(messageCode.getMessage());
        return APIResponseDTO;
    }

    public static <T extends APIResponseDTO<P>, P> APIMultipleResponseDTO<T> status207(List<T> dataList) {
        APIMultipleResponseDTO<T> PVAStandardMultipleResponse = new APIMultipleResponseDTO<>();
        PVAStandardMultipleResponse.setResponseStatus(dataList);
        PVAStandardMultipleResponse.setCode(String.valueOf(HttpStatus.MULTI_STATUS.value()));
        PVAStandardMultipleResponse.setMessage("MULTIPLE_STATUS");
        return PVAStandardMultipleResponse;
    }

    public static <T extends APIResponseDTO<P>, P> APIMultipleResponseDTO<T> status207(String serviceName, List<T> dataList) {
        APIMultipleResponseDTO<T> PVAStandardMultipleResponse = new APIMultipleResponseDTO<>();
        PVAStandardMultipleResponse.setResponseStatus(dataList);
        PVAStandardMultipleResponse.setCode(serviceName.concat("_").concat(String.valueOf(HttpStatus.MULTI_STATUS.value())));
        PVAStandardMultipleResponse.setMessage("MULTIPLE_STATUS");
        return PVAStandardMultipleResponse;
    }

    public static <T> APIResponseDTO<T> statusError(String serviceName, HttpStatus httpStatus, String message) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(message);
        APIResponseDTO.setCode(getResponseCode(serviceName, httpStatus));
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> statusError(String serviceName, HttpStatus httpStatus, String detailCode, String message) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(message);
        APIResponseDTO.setCode(getResponseCode(serviceName, httpStatus, detailCode));
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> status404(String serviceName, String message) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(message);
        APIResponseDTO.setCode(getResponseCode(serviceName, HttpStatus.NOT_FOUND));
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> status404(String serviceName, String detailCode, String message) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(message);
        APIResponseDTO.setCode(getResponseCode(serviceName, HttpStatus.NOT_FOUND, detailCode));
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> status400(String serviceName, String message) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(message);
        APIResponseDTO.setCode(getResponseCode(serviceName, HttpStatus.BAD_REQUEST));
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> status400(String serviceName, String detailCode, String message) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(message);
        APIResponseDTO.setCode(getResponseCode(serviceName, HttpStatus.BAD_REQUEST, detailCode));
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> status401(String serviceName, String message) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(message);
        APIResponseDTO.setCode(getResponseCode(serviceName, HttpStatus.UNAUTHORIZED));
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> status401(String serviceName, String detailCode, String message) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(message);
        APIResponseDTO.setCode(getResponseCode(serviceName, HttpStatus.UNAUTHORIZED, detailCode));
        return APIResponseDTO;
    }


    public static <T> APIResponseDTO<T> status500(String serviceName, String message) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(message);
        APIResponseDTO.setCode(getResponseCode(serviceName, HttpStatus.INTERNAL_SERVER_ERROR));
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> status500(String serviceName, String detailCode, String message) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(message);
        APIResponseDTO.setCode(getResponseCode(serviceName, HttpStatus.INTERNAL_SERVER_ERROR, detailCode));
        return APIResponseDTO;
    }

    public static <T> APIResponseDTO<T> status(String serviceName, ResponseCode responseCode) {
        APIResponseDTO<T> APIResponseDTO = new APIResponseDTO<>();
        APIResponseDTO.setMessage(responseCode.getMessage());
        APIResponseDTO.setCode(responseCode.getCode());
        return APIResponseDTO;
    }

    public static String getResponseCode(String serviceName, HttpStatus httpStatus) {
        return serviceName.concat("_").concat(String.valueOf(httpStatus.value()));
    }

    public static String getResponseCode(String serviceName, HttpStatus httpStatus, String detailCode) {
        return serviceName.concat("_").concat(String.valueOf(httpStatus.value())).concat(detailCode);
    }
}
