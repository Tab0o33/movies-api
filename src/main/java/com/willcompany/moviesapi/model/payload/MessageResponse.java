package com.willcompany.moviesapi.model.payload;

import lombok.Data;

@Data
public class MessageResponse {

	private String message;

	public MessageResponse(String message) {
		this.message = message;
	}

}
