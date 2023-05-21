package com.techway.service;

import java.util.List;

import com.techway.entity.AdvancedSecurity;

public interface AdvancedSecurityService {
	public AdvancedSecurity getById(int id);
    public AdvancedSecurity create(AdvancedSecurity advancedSecurity);
    public AdvancedSecurity update(int id, AdvancedSecurity advancedSecurity);
    public void delete(int id);
	public List<AdvancedSecurity> findAll();
}
