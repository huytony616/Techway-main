package com.techway.dto;

import java.util.HashSet;
import java.util.Set;

import com.techway.RoleName;
import com.techway.entity.Role;
import com.techway.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
	private Long id;
	private String fullName;
	private String email;
	private String photo;
	private Set<String> roles = new HashSet<>();
	
	public static AccountDto fromEntity(User account) {
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setFullName(account.getFullname());
        dto.setEmail(account.getEmail());
        dto.setPhoto(account.getPhoto());

        Set<String> roles = new HashSet<>();
        for (Role role : account.getRoles()) {
            roles.add(role.getName().toString()); // lấy tên của role
        }
        dto.setRoles(roles);

        return dto;
    }

    public User toEntity() {
        User account = new User();
        account.setId(this.getId());
        account.setFullname(this.getFullName());
        account.setEmail(this.getEmail());
        account.setPhoto(this.getPhoto());

        Set<Role> roles = new HashSet<>();
        for (String roleName : this.getRoles()) {
            Role role = new Role();
            role.setName(String.valueOf(RoleName.valueOf(roleName)));
            roles.add(role);
        }
        account.setRoles(roles);

        return account;
    }
}
