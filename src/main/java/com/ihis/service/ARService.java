package com.ihis.service;

import java.util.List;

import com.ihis.bindings.ApplicationForm;
import com.ihis.bindings.ViewApplications;

public interface ARService {

	public String apply(ApplicationForm form);

	public List<ViewApplications> viewApplications();
}
