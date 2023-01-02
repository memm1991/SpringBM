package com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity(name = "post_details")
public class Post  {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 7)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post(String description) {
        this.description = description;
    }

    protected Post(){}



    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "description='" + description + '\'' +
                '}';
    }
}
