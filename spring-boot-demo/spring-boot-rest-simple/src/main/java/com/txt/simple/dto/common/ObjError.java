package com.txt.simple.dto.common;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor()
@NoArgsConstructor
@Builder
@ToString
public class ObjError {
    private String objectIdLas;
    private String programLas;
    private String functionUI;
    private String errorCode;
    private String errorDescription;
    private String errorTime;
    private List<ObjErrorList> oErrorList;

    public ObjError(String errorCode, String errorDescription) {
        super();
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

}
