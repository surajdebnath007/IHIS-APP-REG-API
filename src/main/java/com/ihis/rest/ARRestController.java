package com.ihis.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ihis.bindings.ApplicationForm;
import com.ihis.bindings.ViewApplications;
import com.ihis.service.ARService;

@RestController
public class ARRestController {

	@Autowired
	private ARService service;

	@PostMapping(value = "/apply")
	public String apply(ApplicationForm form) {
		return service.apply(form);
	}

	@GetMapping(value = "/applications")
	public List<ViewApplications> getApplications() {
		return service.viewApplications();
	}
}
