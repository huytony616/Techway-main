package com.techway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techway.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	//lay ds comment theo productId sắp sếp theo ng ày tạo mới nhất
	List<Comment> findAllByProductIdOrderByCreatedDateDesc(Long productId);
	
	@Query("SELECT c FROM Comment c WHERE c.user.email = :email AND c.id = :commentId")
    Optional<Comment> findByEmailAndCommentId(@Param("email") String email, @Param("commentId") Long commentId);

	void deleteByProduct_Id(Long productId);
	
	@Modifying
    @Query("DELETE FROM Comment c WHERE c.user.email = :email")
    void deleteByUser_Email(String email);

}
