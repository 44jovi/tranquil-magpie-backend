package com.tranquilmagpie.user.api;

import com.tranquilmagpie.user.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-09T13:56:09.424558+01:00[Europe/London]")
@Controller
@RequestMapping("${openapi.user.base-path:/v1}")
public class UsersApiController implements UsersApi {

    private final NativeWebRequest request;

    @Autowired
    public UsersApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    //    Return stubbed 'user' data
    @Override
    public ResponseEntity<List<User>> getUsers() {
        User exampleUser = new User();
        exampleUser.setDob(LocalDate.parse("1234-05-06"));
        exampleUser.setEmail("joe.bloggs@example.com");
        exampleUser.setId("1234");
        exampleUser.setUsername("joe1234");
        exampleUser.setFirstName("Joe");
        exampleUser.setLastName("Bloggs");
//        return UsersApi.super.getUsers();
        // ".ok" for status code 200
        return ResponseEntity.ok(Arrays.asList(exampleUser));
    }

}
