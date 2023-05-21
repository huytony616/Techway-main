package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techway.entity.ReplyComment;

public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Long>{
	
	List<ReplyComment> findByCommentIdOrderByCreatedDateDesc(Long commentId);
	
	void deleteByUserIdAndId(Long userId, Long id);
//	
//	void deleteByCommentId(String email, Long commentId);
	
	@Transactional
    @Modifying
    @Query("DELETE FROM ReplyComment r WHERE r.comment.id = :commentId")
    void deleteByCommentId(@Param("commentId") Long commentId);

}