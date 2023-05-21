 package com.techway.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techway.RoleName;
import com.techway.ShippingStatus;
import com.techway.dto.CommentDto;
import com.techway.entity.Comment;
import com.techway.entity.Order;
import com.techway.entity.Product;
import com.techway.entity.Role;
import com.techway.entity.User;
import com.techway.exception.APIException;
import com.techway.repository.CommentRepository;
import com.techway.repository.OrderDetailRepository;
import com.techway.repository.OrderRepository;
import com.techway.repository.ProductRepository;
import com.techway.repository.ReplyCommentRepository;
import com.techway.repository.RoleRepository;
import com.techway.repository.UserRepository;
import com.techway.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReplyCommentRepository replyCommentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private RoleRepository roleRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<CommentDto> findAllByProductIdOrderByCreatedDateDesc(Long productId) {
    	try {
    		 List<CommentDto> comments = commentRepository.findAllByProductIdOrderByCreatedDateDesc(productId).stream().map(
    	        		comment -> fromEntity(comment)).collect(Collectors.toList()
    	        );
    		 return comments;
		} catch (Exception e) {
			return null;
		}
    }



    @Override
    public CommentDto save(String email, Long productId, String orderId, CommentDto commentRequest) {
    	Order order = orderRepository.findByOrderIdAndUserEmail(orderId, email);
    	if (order.getShippingStatus() != String.valueOf(ShippingStatus.DELIVERED)) {
			return null;
		}
    	if(!orderDetailRepository.existsByOrder_IdAndProduct_Id(order.getId(), productId)) {
    		return null;
    	}    	
    	commentRequest.setCreatedBy(email);
    	commentRequest.setProductId(productId);
    	Comment savedComment = commentRepository.save(toEntity(commentRequest));
        return fromEntity(savedComment);
    }

    @Override
    @Transactional
    public Boolean delete(String email, Long commentId) {
        
        try {
        	User user = userRepository.findByEmail(email).get();
            
            Role roleDire = roleRepository.findByName(String.valueOf(RoleName.ROLE_DIRE)).get();
    		if(!user.getRoles().contains(roleDire)) {
    			Comment comment = commentRepository.findByEmailAndCommentId(email, commentId).get();
    			System.out.println(fromEntity(comment));
    		}	
    		replyCommentRepository.deleteByCommentId(commentId);
    		commentRepository.deleteById(commentId);  
    		return true;
		} catch (APIException e) {
			System.out.println(String.format("User with email %s is not permitted to delete comment with id %d", email, commentId));
			return false;
		}  
    }
    
    public CommentDto fromEntity(Comment comment) {
	    CommentDto commentDto = new CommentDto();
	    commentDto.setId(comment.getId());
	    commentDto.setContent(comment.getContent());
	    commentDto.setCreatedBy(comment.getUser().getFullname());
	    commentDto.setCreatedDate(comment.getCreatedDate());
	    return commentDto;
	}
	
	public  Comment toEntity(CommentDto commentDto) {
		Comment comment = new Comment();
	    comment.setContent(commentDto.getContent());
	    comment.setUser(userRepository.findByEmail(commentDto.getCreatedBy()).get());
	    Product product = productRepository.findById(commentDto.getProductId()).get();
	    comment.setProduct(product);
	    comment.setCreatedDate(commentDto.getCreatedDate());
	    return comment;
	}

}
