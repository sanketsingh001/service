package com.example.demo.controller;

import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.LoanApplication;
import com.example.demo.services.LoanApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="/api/v1")
@AllArgsConstructor
public class LoanApplicationController {
	
	private LoanApplicationService service;
	
	@PostMapping(path="/loanapplication")
	@Operation(description = "Method is used to save loan Application Details Database")
	public ResponseEntity<LoanApplication> save(@RequestBody LoanApplication entity) {
	
	
				URI location=
		           ServletUriComponentsBuilder
		          .fromCurrentRequest()
		           .path("/{id}")
		          .buildAndExpand(entity.getApplicationNumber())
		          .toUri();  
    return ResponseEntity.created(location).body(service.save(entity));
		
	}
	
	@GetMapping(path="/loanapplication")
	public List<LoanApplication> findAll() {
		
		return this.service.findAll();
	}
	
	@GetMapping(path="/loanapplication/{id}")
	public LoanApplication findById(@PathVariable("id") int id) {
		
		return this.service.findById(id);
	}
	
	@PutMapping(path="/loanapplication")
	public LoanApplication update(@RequestBody LoanApplication entity) {
		
		return this.service.update(entity);
	}
	
	@DeleteMapping(path="/loanapplication/delete/{id}")
	public LoanApplication remove(@PathVariable("id") int id) {
		LoanApplication application= this.findById(id);
		this.service.remove(id);
		return application;
	}
	
	
	@GetMapping(path="/loanapplication/sort/{propName}")
	public List<LoanApplication> sortByProp(@PathVariable("propName") String propName) {
		
		return this.service.findAllSortedBy(propName);
	}
	

	@GetMapping(path="/loanapplication/findByPage/{page}/{size}/{propName}")
	public List<LoanApplication> findByPage(@PathVariable("page") int page,@PathVariable("size") int size,@PathVariable("propName") String propName) {
		
		return this.service.findByPage(page, size, propName);
	}

}
