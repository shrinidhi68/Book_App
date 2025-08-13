package com.stackroute.userauthenticationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Invalid credentials")
public class UserAlreadyExistsException extends Throwable {

	public UserAlreadyExistsException(String string) {
		super(string);
	}
}
