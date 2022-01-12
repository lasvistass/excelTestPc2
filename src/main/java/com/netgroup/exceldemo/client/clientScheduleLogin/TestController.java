package com.netgroup.exceldemo.client.clientScheduleLogin;
//package com.netgroup.exceldemo.clientScheduleLogin.controller;
//
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.netgroup.exceldemo.clientScheduleLogin.model.ResponseToken;
//import com.netgroup.exceldemo.clientScheduleLogin.model.User;
//
//
//
//@RestController
//public class TestController {
//
//	@Autowired
//	RestTemplate restTemplate;
//
//	private static final String REGISTRATION_URL = "http://localhost:8080/users/register";
//	private static final String AUTHENTICATION_URL = "http://0af1-94-34-10-193.ngrok.io/api/auth/signin";
//	private static final String RESPONSE_URL = "http://0af1-94-34-10-193.ngrok.io/api/home/list";
//
//	@RequestMapping(value = "/getResponse", method = RequestMethod.GET)
//	public List<Excel> getResponse() throws JsonProcessingException {
//
//		List<Excel> excels = null;
//		Excel[] result = null;
//		// create user registration object
//		User user = getRegistrationUser();
//		// convert the user registration object to JSON
//		String registrationBody = getBody(user);
//		// create headers specifying that it is JSON request
//		HttpHeaders registrationHeaders = getHeaders();
//		HttpEntity<String> registrationEntity = new HttpEntity<String>(registrationBody, registrationHeaders);
//
////		 {
////			 Register User
////			ResponseEntity<String> registrationResponse = restTemplate.exchange(REGISTRATION_URL, HttpMethod.POST,
////					registrationEntity, String.class);
////			    if the registration is successful		
////			System.out.println(registrationResponse.getStatusCode().equals(HttpStatus.OK));
////			
////			if (registrationResponse.getStatusCode().equals(HttpStatus.OK)) 
//		try{
//
//				// create user authentication object
//				User authenticationUser = getAuthenticationUser();
//				// convert the user authentication object to JSON
//				String authenticationBody = getBody(authenticationUser);
//				// create headers specifying that it is JSON request
//				HttpHeaders authenticationHeaders = getHeaders();
//				HttpEntity<String> authenticationEntity = new HttpEntity<String>(authenticationBody,
//						authenticationHeaders);
//
//				// Authenticate User and get JWT
//				ResponseEntity<ResponseToken> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
//						HttpMethod.POST, authenticationEntity, ResponseToken.class);
//					
//				// if the authentication is successful		
//				System.out.println(authenticationResponse.getStatusCode().equals(HttpStatus.OK));
//				// if the authentication is successful		
//				if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
//					String token = "Bearer " + authenticationResponse.getBody().getToken();
//					HttpHeaders headers = getHeaders();
//					headers.set("accesToken", token);
//					HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
//					// Use Token to get Response
//					ResponseEntity<Excel[]> response = restTemplate.exchange(RESPONSE_URL, HttpMethod.GET, jwtEntity,
//							Excel[].class);
//					if (response.getStatusCode().equals(HttpStatus.OK)) {
//						 result = response.getBody();
//					}
//					excels = Arrays.asList(result);
//					return excels;
//				}
//				
//				
//			
//
//			
//				
//		}catch(Exception ex)
//		{
//			System.out.println("exception");
//			
//		}
//		return excels;
//		
//	}
//	
//	private User getRegistrationUser() {
//		User user = new User();
//		user.setUsername("utente");
//		user.setPassword("password");
//		return user;
//	}
//
//	private User getAuthenticationUser() {
//		User user = new User();
//		user.setUsername("utente");
//		user.setPassword("password");
//		return user;
//	}
//
//	private HttpHeaders getHeaders() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//		return headers;
//	}
//
//	private String getBody(final User user) throws JsonProcessingException {
//		return new ObjectMapper().writeValueAsString(user);
//	}
//}