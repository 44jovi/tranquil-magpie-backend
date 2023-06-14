package com.tranquilmagpie.spring.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * All users
 */

@Entity
@Table(name = "users")
// TODO: check schema name
@Schema(name = "backend", description = "All users")
@JsonTypeName("user")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-12T13:45:18.754877+01:00[Europe/London]")
public class User {
    @Id
    // TODO: check usage of @GeneratedValue
    private Long id = null;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;

    public User id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     */

    @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     *
     * @return email
     */

    @Schema(name = "email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    /**
     * Get username
     *
     * @return username
     */

    @Schema(name = "username", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Get firstName
     *
     * @return firstName
     */

    @Schema(name = "firstName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Get lastName
     *
     * @return lastName
     */

    @Schema(name = "lastName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    /**
     * Get dob
     *
     * @return dob
     */
    @Valid
    @Schema(name = "dob", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("dob")
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.id, user.id) &&
                Objects.equals(this.email, user.email) &&
                Objects.equals(this.username, user.username) &&
                Objects.equals(this.firstName, user.firstName) &&
                Objects.equals(this.lastName, user.lastName) &&
                Objects.equals(this.dob, user.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, firstName, lastName, dob);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

