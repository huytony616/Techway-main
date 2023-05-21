package com.techway.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;

import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	
	private String shippingStatus;
	private double shipping; //phí ship
	
	@Temporal(TemporalType.DATE)
	@Column(name = "order_date")
	Date orderDate = new Date();
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private User user;
	
	@Nationalized
	private String address;
	private String phone;
	
	public double getSubtotal() {
		double subtotal = 0;
		for (OrderDetail o : orderDetails) {
			subtotal = subtotal + (o.getProduct().getPrice() * o.getQuantity());			
		}
		return subtotal;
	}
}
