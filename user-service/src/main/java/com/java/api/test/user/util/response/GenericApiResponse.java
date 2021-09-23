package com.java.api.test.user.util.response;

import lombok.Data;

@Data
public class GenericApiResponse {
    private String status;
    private String message;

    public GenericApiResponse(String message) {
        super();
        this.status = "success";
        this.message = message;
    }
}
