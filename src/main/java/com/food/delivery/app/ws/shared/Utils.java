package com.food.delivery.app.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final String NUMBERS = "0123456789";
	private final int ITERATIONS = 10000;
	private final int KEY_LENGTH = 256;

	public String generateUserId(int length) {
		return generateRandomString(length);
	}

	public String generateFoodId(int length) {
		return generateRandomString(length);
	}

	public String generateOrderId(int length) {
		return generateRandomNumber(length);
	}

	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}

		return new String(returnValue);
	}

	private String generateRandomNumber(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		returnValue.append("#");

		for (int i = 0; i < length; i++) {
			returnValue.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
		}

		return new String(returnValue);
	}
}
