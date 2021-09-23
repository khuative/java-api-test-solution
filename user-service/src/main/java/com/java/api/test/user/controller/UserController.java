package com.java.api.test.user.controller;

import com.java.api.test.user.request.AddUserRequest;
import com.java.api.test.user.request.UpdateUserRequest;
import com.java.api.test.user.service.UserService;
import com.java.api.test.user.util.constants.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/view/all/")
    @Operation(description="view all Users")
    public ResponseEntity getAllUsers(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getAllUsers(page,size);
    }
    @GetMapping("/search/byName/{query}")
    @Operation(description="search Users by name")
    public ResponseEntity searchUsersByName(@PathVariable String query ,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.searchUsersByName(query,page,size);
    }
    @GetMapping("/search/byId/{userId}")
    @Operation(description="search Users by id")
    public ResponseEntity searchUsersById(@PathVariable long userId ,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.searchUsersById(userId,page,size);
    }
    @PostMapping("/add")
    @Operation(description="add User")
    public ResponseEntity addUser(@Valid @RequestBody AddUserRequest request){
        return userService.addUser(request);
    }

    @PostMapping("/update")
    @Operation(description="update User")
    public ResponseEntity updateUser(@Valid @RequestBody UpdateUserRequest request){
        return userService.updateUser(request);
    }
}
