package com.example.shop14.controller;

import com.example.shop14.entity.Comment;
import com.example.shop14.entity.Product;
import com.example.shop14.repo.CommentRepository;
import com.example.shop14.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    // DELETE /comments/{id}
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET /comments/{id}
    @GetMapping("/comments/{id}")
    public Comment getCommentByCommentId(@PathVariable Long id) {
        return commentRepository.findById(id)
                .orElse(null);
    }

    // POST http://localhost:8080/products/{productId}/comments - добавление коммента к продукту
    @PostMapping("/products/{productId}/comments")
    public ResponseEntity<Comment> createComment(
            @PathVariable Long productId,
            @RequestBody Comment comment
    ) {
        // Find the product by its id
        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            // If the product doesn't exist, return a not found response
            return ResponseEntity.notFound().build();
        } else {
            // Set the product association for the comment
            comment.setProduct(product);
            // Save the comment, return OK
            return ResponseEntity.ok(commentRepository.save(comment));
        }
    }
}
