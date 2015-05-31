package test.medicalclinic.db;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Test {

	public static void main(String[] args) {
		
		String pass = "z";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String password = encoder.encode(pass);
		
		System.out.println("Has³o zakodowane " + password);
		
		String encodePassowd = encoder.encode(pass);
		
		System.out.println("Has³o zakodowane " + encodePassowd);
	  }

	}