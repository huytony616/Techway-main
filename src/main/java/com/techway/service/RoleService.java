package com.techway.service;

import java.util.List;

import com.techway.entity.Role;

public interface RoleService {
	
	Role findByName(String name);

	List<Role> listRoles();
}
