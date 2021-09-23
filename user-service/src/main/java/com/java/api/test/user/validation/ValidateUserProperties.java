package com.java.api.test.user.validation;

import com.java.api.test.user.model.User;
import com.java.api.test.user.repository.UserRepository;
import com.java.api.test.user.request.AddUserRequest;
import com.java.api.test.user.request.UpdateUserRequest;
import com.java.api.test.user.util.format.FormatUtility;
import com.java.api.test.user.util.response.GenericApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class ValidateUserProperties {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FormatUtility formatUtility;

    public ResponseEntity isValidAddUserRequest(AddUserRequest request){
        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getSurname()==null || request.getSurname().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getTitle()==null || request.getTitle().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Title cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getJobTitle()==null || request.getJobTitle().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Job Title cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getDob()==null || request.getDob().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("DOB cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        if(!formatUtility.isValidDate(request.getDob())){
            return new ResponseEntity<>(new GenericApiError("DOB is not valid"), HttpStatus.EXPECTATION_FAILED);
        }
        ///////////////////////check if DOB is after yesterday./////////////
        LocalDate yesterday = LocalDate.now().minusDays(1);
        if(LocalDate.parse(request.getDob()).isAfter(yesterday)){
            return new ResponseEntity<>(new GenericApiError("DOB cannot today or in the future"), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateUserRequest(UpdateUserRequest request){
        User user = userRepository.findById(request.getId()).orElse(null);
        if(user==null){
            return new ResponseEntity<>(new GenericApiError("User Not Found from Id Provided"), HttpStatus.NOT_FOUND);
        }
        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getSurname()==null || request.getSurname().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getTitle()==null || request.getTitle().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Title cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getJobTitle()==null || request.getJobTitle().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Job Title cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getDob()==null || request.getDob().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("DOB cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        if(!formatUtility.isValidDate(request.getDob())){
            return new ResponseEntity<>(new GenericApiError("DOB is not valid"), HttpStatus.EXPECTATION_FAILED);
        }
        ///////////////////////check if DOB is after yesterday./////////////
        LocalDate yesterday = LocalDate.now().minusDays(1);
        if(LocalDate.parse(request.getDob()).isAfter(yesterday)){
            return new ResponseEntity<>(new GenericApiError("DOB cannot today or in the future"), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }

}
