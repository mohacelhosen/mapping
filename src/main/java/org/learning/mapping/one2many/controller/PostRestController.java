package org.learning.mapping.one2many.controller;

import lombok.AllArgsConstructor;
import org.learning.mapping.one2many.model.Post;
import org.learning.mapping.one2many.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostRestController {

    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        Post savedPost = postService.savePost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
}
