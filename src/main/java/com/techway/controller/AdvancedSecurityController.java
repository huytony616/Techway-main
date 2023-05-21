package com.techway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.entity.AdvancedSecurity;
import com.techway.service.AdvancedSecurityService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/advanced-securities")
public class AdvancedSecurityController {

    @Autowired
    private AdvancedSecurityService advancedSecurityService;
    
    @GetMapping
	public ResponseEntity<List<AdvancedSecurity>> getAllAdvancedSecurity() {
		List<AdvancedSecurity> advancedSecurities = advancedSecurityService.findAll();
		return new ResponseEntity<>(advancedSecurities, HttpStatus.OK);
	}

    @GetMapping("/{id}")
    public ResponseEntity<AdvancedSecurity> getById(@PathVariable int id) {
        AdvancedSecurity advancedSecurity = advancedSecurityService.getById(id);
        if (advancedSecurity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(advancedSecurity);
    }

    @PostMapping
    public ResponseEntity<AdvancedSecurity> create(@RequestBody AdvancedSecurity advancedSecurity) {
        AdvancedSecurity createdAdvancedSecurity = advancedSecurityService.create(advancedSecurity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdvancedSecurity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvancedSecurity> update(@PathVariable int id, @RequestBody AdvancedSecurity advancedSecurity) {
        AdvancedSecurity existingAdvancedSecurity = advancedSecurityService.getById(id);
        if (existingAdvancedSecurity == null) {
            return ResponseEntity.notFound().build();
        }
        AdvancedSecurity updatedAdvancedSecurity = advancedSecurityService.update(id, advancedSecurity);
        return ResponseEntity.ok(updatedAdvancedSecurity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        AdvancedSecurity existingAdvancedSecurity = advancedSecurityService.getById(id);
        if (existingAdvancedSecurity == null) {
            return ResponseEntity.notFound().build();
        }
        advancedSecurityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
