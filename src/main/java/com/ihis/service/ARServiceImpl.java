package com.ihis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ihis.bindings.ApplicationForm;
import com.ihis.bindings.ViewApplications;
import com.ihis.entities.CitizenApplicationEntity;
import com.ihis.repo.CitizenApplicationRepository;

@Service
public class ARServiceImpl implements ARService {

	@Autowired
	private CitizenApplicationRepository repo;

	@Override
	public String apply(ApplicationForm form) {

		CitizenApplicationEntity entity = new CitizenApplicationEntity();
		BeanUtils.copyProperties(form, entity);

		// Gets state for ssn  from external RestAPI
		Long ssn = form.getSsn();
		String url = "https://ssa-web-api.herokuapp.com/ssn/" + ssn;
		RestTemplate restTemplate = new RestTemplate();
		String state = restTemplate.getForObject(url, String.class);

		entity.setState(state);

		CitizenApplicationEntity savedEntity = repo.save(entity);
		if (savedEntity == null) {
			return "Application Failed";
		}
		return "SUCCESS";
	}

	@Override
	public List<ViewApplications> viewApplications() {

		List<CitizenApplicationEntity> citizenRecords = repo.findAll();

		List<ViewApplications> viewApps = new ArrayList<>();

		for (CitizenApplicationEntity citizenRecord : citizenRecords) {
			ViewApplications view = new ViewApplications();
			BeanUtils.copyProperties(citizenRecord, view);
			viewApps.add(view);
		}
		return viewApps;
	}

}
