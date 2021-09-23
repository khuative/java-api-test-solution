package com.java.api.test.user.response;

import lombok.Data;


@Data
public class UserResponse {
    private long id;
    private String title;
    private String firstName;
    private String surname;
    private String dob;
    private String jobTitle;
    private String createdAt;
    private String updatedAt;
}
