package com.majesty.pet_care.controller;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.majesty.pet_care.dto.EntityConverter;
import com.majesty.pet_care.dto.UserDto;
import com.majesty.pet_care.exception.ResourceNotFoundException;
import com.majesty.pet_care.exception.AlreadyExistsException;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.request.RegistrationRequest;
import com.majesty.pet_care.request.UserUpdateRequest;
import com.majesty.pet_care.response.ApiResponse;
import com.majesty.pet_care.service.user.UserService;
import com.majesty.pet_care.utils.FeedbackMessage;
import com.majesty.pet_care.utils.UrlMapping;

import lombok.RequiredArgsConstructor;


@RequestMapping(UrlMapping.USERS)
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EntityConverter<User, UserDto> entityConverter;

    @PostMapping(UrlMapping.REGISTER_USER)
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest request) {
        try {
            User theUser = userService.register(request);
            UserDto registeredUser = entityConverter.mapEntityToDto(theUser, UserDto.class);
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.CREATE_SUCCESS,registeredUser));

        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(Response.SC_CONFLICT).body(new ApiResponse(e.getMessage(), null));

        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

        }

    }

    @PutMapping(UrlMapping.UPDATE_USER)
    public ResponseEntity<ApiResponse> update(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {

        try {
            User user = userService.update(userId, request);
            UserDto userDto = entityConverter.mapEntityToDto(user, UserDto.class);
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.UPDATE_SUCCESS,userDto));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

        }
               
    }

    @GetMapping(UrlMapping.GET_USER_BY_ID)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long userId) {
        try {
            User user = userService.findById(userId);
            UserDto theUser = entityConverter.mapEntityToDto(user, UserDto.class);
            return ResponseEntity.ok(new ApiResponse(FeedbackMessage.FOUND,theUser));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

        }
   }

   @DeleteMapping(UrlMapping.DELETE_USER_BY_ID)
   public ResponseEntity<ApiResponse> delete(@PathVariable Long userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.status(Response.SC_FOUND).body(new ApiResponse(FeedbackMessage.DELETE_SUCCESS,null));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
            
        }
    }

    @GetMapping(UrlMapping.GET_ALL_USERS)
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<UserDto> theUsers = userService.getAllUsers();
        return ResponseEntity.status(Response.SC_FOUND).body(new ApiResponse(FeedbackMessage.FOUND,theUsers));
    }

}