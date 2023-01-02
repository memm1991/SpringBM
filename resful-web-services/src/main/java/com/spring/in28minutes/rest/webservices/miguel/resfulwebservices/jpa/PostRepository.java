package com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.jpa;

import com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
