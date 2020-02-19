package com.uraan.skybook.helpdesk.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uraan.skybook.helpdesk.models.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {

	List<Issue> findByUserId(String userId);
}
