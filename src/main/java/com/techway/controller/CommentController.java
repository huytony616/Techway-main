package com.techway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.dto.CommentDto;
import com.techway.service.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
	
    @Autowired
    private CommentService commentService;


    // retrieve comments of a product
    @GetMapping("/product/{id}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByProductId(@PathVariable(value = "id") Long productId) {
        return new ResponseEntity<>(commentService.findAllByProductIdOrderByCreatedDateDesc(productId), HttpStatus.OK);
    }

    //create new Comment for a Product
    @PostMapping("/product/{id}/order/{orderId}")
    public ResponseEntity<CommentDto> createComment(Authentication authentication, 
    		@PathVariable(value = "id") Long productId,
    		@PathVariable(value = "oderId") String orderId,
            @RequestBody @Validated CommentDto commentRequest) {
    	String email = authentication.getName();
    	System.out.println("User's email: " +  email);
        CommentDto comment = commentService.save(email, productId, orderId, commentRequest);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
   
    
    //
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteComment(Authentication authentication, @PathVariable("id") Long commentId) {
    	String email = authentication.getName();
    	System.out.println("User's email: " +  email);
    	
        Boolean deletedCommentStatus = commentService.delete(email, commentId);
        if (deletedCommentStatus) {
            return ResponseEntity.ok().body(deletedCommentStatus);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(deletedCommentStatus);
        }
    }

//    @GetMapping("/user")
//    public String getUser(Authentication authentication) {
//        String username = authentication.getName();// Lấy tên đăng nhập của người dùng
//        return "Xin chào " + username;
//    }
}
