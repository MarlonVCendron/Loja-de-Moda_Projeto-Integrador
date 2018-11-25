package br.edu.ifcvideira.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Senha {
	public static String encriptarSenha(String senha) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
			
			StringBuffer hexString = new StringBuffer();
		    for (int i = 0; i < hash.length; i++) {
		    	String hex = Integer.toHexString(0xff & hash[i]);
		    	if(hex.length() == 1) {
		    		hexString.append('0');
		    	}
		        hexString.append(hex);
		    }
		    
			return hexString.toString();
		}catch (Exception e) {
			return "";
		}	
	}
}
