package com.techway.service;

import java.util.List;

import com.techway.dto.ReplyCommentDto;

public interface ReplyCommentService {

	List<ReplyCommentDto> getReplyCommentsByCommentId(Long commentId);
    ReplyCommentDto save(String email, ReplyCommentDto replyComment);
    void delete(String email, Long replyCommentId);

}
