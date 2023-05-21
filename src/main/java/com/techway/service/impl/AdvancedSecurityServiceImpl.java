package com.techway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.entity.AdvancedSecurity;
import com.techway.repository.AdvancedSecurityRepository;
import com.techway.service.AdvancedSecurityService;
@Service
public class AdvancedSecurityServiceImpl implements AdvancedSecurityService{

	@Autowired
    private AdvancedSecurityRepository repository;

    @Override
    public AdvancedSecurity getById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public AdvancedSecurity create(AdvancedSecurity advancedSecurity) {
        return repository.save(advancedSecurity);
    }

    @Override
    public AdvancedSecurity update(int id, AdvancedSecurity advancedSecurity) {
        AdvancedSecurity existingAdvancedSecurity = getById(id);
        if (existingAdvancedSecurity != null) {
            existingAdvancedSecurity.setName(advancedSecurity.getName());
            existingAdvancedSecurity.setPhones(advancedSecurity.getPhones());
            return repository.save(existingAdvancedSecurity);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

	@Override
	public List<AdvancedSecurity> findAll() {
		return repository.findAll();
	}

}
