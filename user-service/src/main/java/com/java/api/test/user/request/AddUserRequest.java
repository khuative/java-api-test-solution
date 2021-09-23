package com.java.api.test.user.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AddUserRequest {
    private String title;
    private String firstName;
    private String surname;
    private String dob;
    private String jobTitle;
}
