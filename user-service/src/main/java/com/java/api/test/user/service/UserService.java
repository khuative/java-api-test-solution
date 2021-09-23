package com.java.api.test.user.service;

import com.java.api.test.user.model.User;
import com.java.api.test.user.repository.UserRepository;
import com.java.api.test.user.request.AddUserRequest;
import com.java.api.test.user.request.UpdateUserRequest;
import com.java.api.test.user.response.UserResponse;
import com.java.api.test.user.util.format.FormatUtility;
import com.java.api.test.user.util.response.GenericApiError;
import com.java.api.test.user.util.response.GenericApiResponse;
import com.java.api.test.user.util.response.PagedResponse;
import com.java.api.test.user.validation.ValidateUserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    @Autowired
    ValidateUserProperties validateUserProperties;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FormatUtility formatUtility;

    public ResponseEntity addUser(AddUserRequest request){
        ResponseEntity theResponse = validateUserProperties.isValidAddUserRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setSurname(request.getSurname());
        user.setTitle(request.getTitle());
        user.setJobTitle(request.getJobTitle());
        user.setDob(LocalDate.parse(request.getDob()));
        userRepository.save(user);

        return ResponseEntity.ok(new GenericApiResponse("User Created Successfully!"));
    }
    public ResponseEntity updateUser(UpdateUserRequest request){

        ResponseEntity theResponse = validateUserProperties.isValidUpdateUserRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }

        User user = userRepository.findById(request.getId()).orElse(null);
        if(user==null){
            return new ResponseEntity<>(new GenericApiError("User Not Found"), HttpStatus.NOT_FOUND);
        }
        user.setFirstName(request.getFirstName());
        user.setSurname(request.getSurname());
        user.setTitle(request.getTitle());
        user.setJobTitle(request.getJobTitle());
        user.setDob(LocalDate.parse(request.getDob()));
        userRepository.save(user);
        return ResponseEntity.ok(new GenericApiResponse("User Updated Successfully!"));

    }

    public ResponseEntity getAllUsers(int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        // validate Page size and page number
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve results

        Page<User> users = userRepository.findAll(pageable);

        List<UserResponse> userResponses = users.stream().map(this::mapUserEntityToSpecificResponse).collect(toList());

        return   ResponseEntity.ok(new PagedResponse<>(userResponses, users.getNumber(),
                users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast()));

    }
    public ResponseEntity searchUsersByName(String query,int page, int size) {
        if(query==null || query.isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Query cannot be empty"), HttpStatus.EXPECTATION_FAILED);
        }
        // validate Page size and page number
        formatUtility.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve results
        Page<User> users = userRepository.findByFirstNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(query,query,pageable);

        List<UserResponse> userResponses = users.stream().map(this::mapUserEntityToSpecificResponse).collect(toList());

        return   ResponseEntity.ok(new PagedResponse<>(userResponses, users.getNumber(),
                users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast()));

    }
    public ResponseEntity searchUsersById(long userId,int page, int size) {
        // validate Page size and page number
        formatUtility.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve results
        User user = userRepository.findById(userId).orElse(null);
        if(user==null){
            return new ResponseEntity<>(new GenericApiError("User Not Found"), HttpStatus.NOT_FOUND);
        }

        return   ResponseEntity.ok(this.mapUserEntityToSpecificResponse(user));

    }
    private UserResponse mapUserEntityToSpecificResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setSurname(user.getSurname());
        response.setTitle(user.getTitle());
        response.setJobTitle(user.getJobTitle());
        if(user.getDob()!=null){
            response.setDob(user.getDob().toString());
        }
        if(user.getCreatedAt()!=null){
            response.setDob(user.getCreatedAt().toString());
        }
        if(user.getUpdatedAt()!=null){
            response.setDob(user.getUpdatedAt().toString());
        }
            return response;
    }
}
