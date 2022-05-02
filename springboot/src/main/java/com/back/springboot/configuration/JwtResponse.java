package com.back.springboot.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {
    private String message;
    private Status status;
    private String exceptionType;
    private String jwt;
    private Long id;
    private String username;
    private String email;
    private Jws<Claims> jws;

    public enum Status {
        SUCCESS, ERROR
    }

    public JwtResponse() {
    }

    public JwtResponse(String jwt) {
        this.jwt = jwt;
        this.status = Status.SUCCESS;
    }

    public JwtResponse(String accessToken, String username, String email) {
        this.jwt = accessToken;
        this.username = username;
        this.email = email;
    }

    public JwtResponse(Jws<Claims> jws) {
        this.jws = jws;
        this.status = Status.SUCCESS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Jws<Claims> getJws() {
        return jws;
    }

    public void setJws(Jws<Claims> jws) {
        this.jws = jws;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
