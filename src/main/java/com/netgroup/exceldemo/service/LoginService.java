package com.netgroup.exceldemo.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.netgroup.exceldemo.data.Utente;
import com.netgroup.exceldemo.exception.UserNotLoggedException;

public interface LoginService {

	Optional<Utente> getUserFromDbAndVerifyPassword(String id, String password) throws UserNotLoggedException;

	String createJwt(String subject, String name, String permission, Date date) throws UnsupportedEncodingException;

	Map<String, Object> verifyJwtAndGetData(HttpServletRequest request)
			throws UserNotLoggedException, UnsupportedEncodingException;

}
