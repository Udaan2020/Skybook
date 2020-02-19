package com.uraan.skybook.helpdesk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uraan.skybook.helpdesk.models.Issue;
import com.uraan.skybook.helpdesk.repositories.IssueRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/helpdesk")
public class HelpdeskController {

	@Autowired
	private IssueRepository issueRepo;
	
	@GetMapping("/get/all")
	public List<Issue> get()
	{
		return issueRepo.findAll();
	}
	
	@GetMapping("/get/user/{id}")
	public List<Issue> getByUser(@PathVariable("id") String id)
	{
		return issueRepo.findByUserId(id);
	}
	
	@PostMapping("/post/issue")
	private boolean postIssue(@Valid @RequestBody Issue issue)
	{
		try
		{
			issueRepo.saveAndFlush(issue);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}
	
	@PutMapping("/issue/resolve/{id}")
	private void resolve(@PathVariable("id") String id)
	{
		Issue issue = issueRepo.findById(Long.parseLong(id)).get();
		issue.setStatus("Resolved");
		issueRepo.saveAndFlush(issue);
		
	}
	
	@DeleteMapping("/issue/remove/{id}")
	private void remove(@PathVariable("id") String id)
	{
		Issue issue = issueRepo.findById(Long.parseLong(id)).get();
		issueRepo.delete(issue);
	}
}
