package org.learning.mapping.one2many.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.learning.mapping.one2many.model.Comment;
import org.learning.mapping.one2many.model.CommentImage;
import org.learning.mapping.one2many.model.Post;
import org.learning.mapping.one2many.repository.PostRepository;
import org.learning.mapping.one2one.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            Comment comment = Comment.builder().post(post).images(singleComment.getImages()).text(singleComment.getText()).build();

            // Retrieve images associated with the comment
            List<CommentImage> images = comment.getImages();

            // Map each image to its corresponding entity and set the comment
            List<CommentImage> imageList = images.stream().map(singleImage -> CommentImage.builder().comment(comment).image(singleImage.getImage()).build()).toList();

            // Set the images for the comment
            comment.setImages(imageList);

            return comment;

        }).toList();

        // Set the comments for the post
        post.setComments(commentList);

        // Save the post along with its associated comments and comment images
        return postRepository.save(post);
    }

    /*
    This explicit access forces the lob streams to be loaded within the transactional context of the method.
    As a result, when the Post entity is returned from the method, the lob streams are already loaded and accessible,
    which prevents the "Unable to access lob stream" error from occurring later.
     */

    @Transactional
    public Post getPostById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            // Ensure lob stream is loaded
            post.getComments().forEach(comment -> {
                comment.getImages().forEach(commentImage -> {
                    // Access lob stream if needed
                    byte[] imageData = commentImage.getImage();
                });
            });
            return post;
        } else {
            return null;
        }
    }

}
