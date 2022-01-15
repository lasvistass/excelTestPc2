package com.netgroup.exceldemo.client.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netgroup.exceldemo.client.model.ResponseToken;
import com.netgroup.exceldemo.client.model.User;
import com.netgroup.exceldemo.data.dao.Excel;
import com.netgroup.exceldemo.service.ExcelService;



@Service
public class ServiceTemplate{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ExcelService excelService;

	private static final String REGISTRATION_URL = "http://localhost:8080/users/register";
	private static final String AUTHENTICATION_URL = "http://localhost:8080/users/authenticate";
	private static final String RESPONSE_URL = "http://localhost:8080/excel/token";

	public Excel[] getResponse() throws JsonProcessingException {

		Excel[] response = null;
//		// create user registration object
//		RegistrationUser registrationUser = getRegistrationUser();
//		// convert the user registration object to JSON
//		String registrationBody = getBody(registrationUser);
//		// create headers specifying that it is JSON request
//		HttpHeaders registrationHeaders = getHeaders();
//		HttpEntity<String> registrationEntity = new HttpEntity<String>(registrationBody, registrationHeaders);

		try {
//			// Register User
//			ResponseEntity<String> registrationResponse = restTemplate.exchange(REGISTRATION_URL, HttpMethod.POST,
//					registrationEntity, String.class);
//			   // if the registration is successful		
//			if (registrationResponse.getStatusCode().equals(HttpStatus.OK)) {

				// create user authentication object
				User authenticationUser = getAuthenticationUser();
				// convert the user authentication object to JSON
				String authenticationBody = getBody(authenticationUser);
				// create headers specifying that it is JSON request
				HttpHeaders authenticationHeaders = getHeaders();
				HttpEntity<String> authenticationEntity = new HttpEntity<String>(authenticationBody,
						authenticationHeaders);

				// Authenticate User and get JWT
				ResponseEntity<ResponseToken> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
						HttpMethod.POST, authenticationEntity, ResponseToken.class);
					
				// if the authentication is successful		
				if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
					String token = "Bearer " + authenticationResponse.getBody().getToken();
					HttpHeaders headers = getHeaders();
					headers.set("Authorization", token);
					HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
					// Use Token to get Response
					ResponseEntity<Excel[]> helloResponse = restTemplate.exchange(RESPONSE_URL, HttpMethod.GET, jwtEntity,
							Excel[].class);
					if (helloResponse.getStatusCode().equals(HttpStatus.OK)) {
						response = helloResponse.getBody();
						excelService.arrayToList(response);
					}
				}
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response;
	}

	
	private User getRegistrationUser() {
		User user = new User();
		user.setUsername("user");
		user.setPassword("user");
		return user;
	}

	private User getAuthenticationUser() {
		User user = new User();
		user.setUsername("user");
		user.setPassword("user");
		return user;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	private String getBody(final User user) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(user);
	}
}