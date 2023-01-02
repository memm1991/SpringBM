package com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException  {
    public PostNotFoundException(String s)  {
        super(s);
    }
}
