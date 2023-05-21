package com.techway.controller;

import java.security.Principal;
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

import com.techway.dto.ReplyCommentDto;
import com.techway.repository.CommentRepository;
import com.techway.repository.UserRepository;
import com.techway.service.ReplyCommentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/replies")
public class ReplyCommentController {

	@Autowired
	ReplyCommentService replyCommentService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommentRepository commentRepository;
	

	//1. lấy danh sách reply theo commentId sắp xếp theo lated createDate  -> ANY ROLE
	//2. Trả lời comment -> ROLE_STAFF, ROLE_DIRE
	//3. Xóa reply  -> chỉ người tạo hoặc ROLE_DIRE được phép xóa

	
	@GetMapping("/comment/{commentId}")
	public ResponseEntity<List<ReplyCommentDto>> getCommentsByCommentId(@PathVariable("commentId") Long commentId, Principal principal){
		List<ReplyCommentDto> list = replyCommentService.getReplyCommentsByCommentId(commentId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ReplyCommentDto> replyToCustomer(@RequestBody @Validated ReplyCommentDto dto,
			Authentication authn){
		String email = authn.getName();
		System.out.println(email); 
		ReplyCommentDto reply = replyCommentService.save(email, dto);
		return new ResponseEntity<ReplyCommentDto>(reply, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{replyId}")
	public ResponseEntity<Void> deleteReply(@PathVariable("replyId") Long replyId, Authentication authn){
		try {
			String email = authn.getName();
			replyCommentService.delete(email, replyId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
