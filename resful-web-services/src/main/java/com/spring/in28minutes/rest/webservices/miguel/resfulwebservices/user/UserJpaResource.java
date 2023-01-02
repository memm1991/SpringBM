package com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.user;

import com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.jpa.PostRepository;
import com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.jpa.UserRepository;
import com.spring.in28minutes.rest.webservices.miguel.resfulwebservices.post.Post;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private UserRepository repository;

    private PostRepository postRepository;

    public UserJpaResource(UserDaoService service , UserRepository repository,PostRepository postRepository){
        this.repository = repository;
        this.postRepository = postRepository;
    }
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}/post/{post_id}")
    public EntityModel<Post> retrieveUser(@PathVariable int id, @PathVariable int post_id) {
        Optional<User> userOptional = repository.findById(id);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id:"+id);
        }

        User user = userOptional.get();
        Post postedFound = user.getPost().stream()
                .filter(p -> p.getId().equals(post_id))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException("id:"+post_id));

        EntityModel<Post> entityModel = EntityModel.of(postedFound);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePostFromUser(id));
        entityModel.add(link.withRel("all-post"));
        return entityModel;
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
         repository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser (@Valid @RequestBody User user) {
       User savedUser = repository.save(user);
       URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/post")
    public List<Post> retrievePostFromUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        EntityModel<User> entityModel = EntityModel.of(user.get());
        return user.get().getPost();
    }
    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        post.setUser(user.get());
        Post savedPost= postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
