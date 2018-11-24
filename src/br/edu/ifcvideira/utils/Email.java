package br.edu.ifcvideira.utils;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Email {
	public static void enviarEmailRecuperacao(String emailDestino, String codigo) {
		String destino = emailDestino;
		String email = "projetointegradorlojamoda@gmail.com"; 
		String senha = "0nL7%oW4l$!#XtI";  

		
	    Properties props = new Properties();  
	    props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    
	    Session session = Session.getDefaultInstance(props, new Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {  
	    	   return new PasswordAuthentication(email,senha);  
	       }  
	   });  
	    
	    try {
		    Transport transport = session.getTransport();  
		    InternetAddress enderecoOrigem = new InternetAddress(email);  
	
		    MimeMessage message = new MimeMessage(session);  
		    String msg ="O seu código de recuperação é:   " + codigo + "\nEscreva o código e clique em recuperar senha.";
		    message.setSender(enderecoOrigem);  
		    message.setSubject("Recuperação de senha");  
		    message.setContent(msg, "text/plain");  
		    message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));  
	
		    transport.connect();  
		    Transport.send(message);  
		    transport.close();
		    
		    JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso");
	    }catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	}	
}
