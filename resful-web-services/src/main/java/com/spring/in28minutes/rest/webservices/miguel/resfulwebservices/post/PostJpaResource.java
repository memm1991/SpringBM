package com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.post;

import com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.jpa.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class
PostJpaResource {

    private PostRepository repository;

    public PostJpaResource(PostRepository repository ) {
        this.repository = repository;
    }

    //@GetMapping("/jpa/post/{id}")
    //public Optional<Post> retrivePostFromUser(@PathVariable int id){

    //}
}
