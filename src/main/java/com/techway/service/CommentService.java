package com.techway.service;

import java.util.List;

import com.techway.dto.CommentDto;

public interface CommentService {
	
	//List  comment theo productId sắp xếp theo ngày tạo mới nhất
	List<CommentDto> findAllByProductIdOrderByCreatedDateDesc(Long productId);

	//Tạo comment
	CommentDto save(String email, Long productId, String orderId, CommentDto commentRequest);

	//delete comment
    Boolean delete(String email, Long commentId);
}
