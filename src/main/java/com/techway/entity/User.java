package com.techway.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(unique = true, nullable = false, length = 50)
	@NotNull
	@Size(max = 50)
	@Email
	private String email;
	
	
	@NotNull
	private String password;
	
	@Nationalized
	@NotNull
	@Size(max = 50)
	private String fullname;	
	
	private String photo;
	
	@Column(name = "verification_code", length = 50)
    private String verificationCode;
     
	private boolean enabled;
 
	public User(String email, String password) {
        this.email = email;
        this.password = password;
    }	
	
	@ManyToMany(fetch = FetchType.LAZY ,
			cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
	
	public void addRole(Role role) {
		this.roles.add(role);
		role.getUsers().add(this);
	}
	
	public void removeRole(Role role) {
		this.getRoles().remove(role);
		role.getUsers().remove(this);
	}
	
	public void removeRole() {
		for(Role role : new HashSet<>(roles)) {
			removeRole(role);
		}
	}
}
