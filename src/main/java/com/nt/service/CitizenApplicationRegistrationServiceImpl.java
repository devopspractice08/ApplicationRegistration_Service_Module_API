package com.nt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.entity.CitizenAppRegistrationEntity;
import com.nt.exceptions.InvalidSSNException;
import com.nt.repository.ICititzenApplicationRegistrationRepository;

import reactor.core.publisher.Mono;

@Service
public class CitizenApplicationRegistrationServiceImpl implements ICitizenApplicationRegistrationService {
	@Autowired
	private ICititzenApplicationRegistrationRepository cititzenRepo;
//	@Autowired
//	private RestTemplate template;
	@Autowired
	private WebClient client;
	@Value("${ar.ssa-web.url}")
	private String endPointUrl;
	@Value("${ar.state}")
	private String targetState;

	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs) throws InvalidSSNException{
		// External RestAPI Url
		// String SSAWebUrl="http://localhost:8080/ssa-web-api/find/{ssn}";

		// perform webservice call to check whether ssn is valid or not and get to state
		// name(Using RestTemplate)
		// ResponseEntity<String> response=template.exchange(endPointUrl,
		// HttpMethod.GET,
		// null,String.class,inputs.getSsn());
		// perform webservice call to check whether ssn is valid or not and get to state
		// name(Using WebClient)
         
		// get state name
		
		Mono<String> response=client.get().uri(endPointUrl,inputs.getSsn()).retrieve()
				.onStatus(HttpStatus.BAD_REQUEST::equals, res->res.bodyToMono(String.class).map(ex->new InvalidSSNException("Invalid ssn"))).bodyToMono(String.class);
		
		String stateName=response.block();
        
		// register citizen if he belongs to California State
		if (stateName.equalsIgnoreCase(targetState)) {
			// prepare Entity obj
			CitizenAppRegistrationEntity entity = new CitizenAppRegistrationEntity();
			BeanUtils.copyProperties(inputs, entity);
			entity.setStateName(stateName);
			// save the obj
			int appId = cititzenRepo.save(entity).getAppId();
			return appId;

		}//if
	
		throw new InvalidSSNException("Invalid SSN");	
}// method

}
