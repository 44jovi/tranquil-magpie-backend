/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.tranquilmagpie.user.api;

import com.tranquilmagpie.user.model.User;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-07T17:15:20.356286+01:00[Europe/London]")
@Validated
@Tag(name = "user", description = "User ")
public interface UsersApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /users : List All users
     * Gets a list of all &#x60;user&#x60; entities.
     *
     * @return Successful response - returns an array of &#x60;user&#x60; entities. (status code 200)
     */
    @Operation(
        operationId = "getUsers",
        summary = "List All users",
        description = "Gets a list of all `user` entities.",
        tags = { "user" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful response - returns an array of `user` entities.", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users",
        produces = { "application/json" }
    )
    default ResponseEntity<List<User>> getUsers(

    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"value\" : { \"id\" : 44, \"email\" : \"example1@example.com\", \"username\" : \"example1\", \"firstName\" : \"Example\", \"lastName\" : \"One\", \"dob\" : \"1944-04-04\" } }, { \"value\" : { \"id\" : 44, \"email\" : \"example1@example.com\", \"username\" : \"example1\", \"firstName\" : \"Example\", \"lastName\" : \"One\", \"dob\" : \"1944-04-04\" } } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
