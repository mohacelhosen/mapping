package org.learning.mapping.one2many.service;

import lombok.AllArgsConstructor;
import org.learning.mapping.one2many.model.Comment;
import org.learning.mapping.one2many.model.CommentImage;
import org.learning.mapping.one2many.model.Post;
import org.learning.mapping.one2many.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;


    /**
     * Saves a post along with its associated comments and comment images.
     *
     * @param post The post to be saved.
     * @return The saved post.
     */
    public Post savePost(Post post) {

        // Retrieve comments associated with the post
        List<Comment> comments = post.getComments();

        // Map each comment to its corresponding entity and populate its images
        List<Comment> commentList = comments.stream().map(singleComment -> {
            // Create a new comment entity with the post, text, and images
            Comment comment = Comment.builder()
                    .post(post)
                    .images(singleComment.getImages())
                    .text(singleComment.getText())
                    .build();

            // Retrieve images associated with the comment
            List<CommentImage> images = comment.getImages();

            // Map each image to its corresponding entity and set the comment
            List<CommentImage> imageList = images.stream().map(singleImage -> CommentImage.builder()
                    .comment(comment)
                    .image(singleImage.getImage())
                    .build()).toList();

            // Set the images for the comment
            comment.setImages(imageList);

            return comment;

        }).toList();

        // Set the comments for the post
        post.setComments(commentList);

        // Save the post along with its associated comments and comment images
        return postRepository.save(post);
    }
}
