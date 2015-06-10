package medicalclinic.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Klasa służąca do kodowania hasła
 * */
public class EncryptionPassword {
	
	/**
	 * Kodowanie hasła
	 * */
	public static String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}
}
