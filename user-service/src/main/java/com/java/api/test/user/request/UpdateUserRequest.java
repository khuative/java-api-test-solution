package com.java.api.test.user.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private long id;
    private String title;
    private String firstName;
    private String surname;
    private String dob;
    private String jobTitle;
}
