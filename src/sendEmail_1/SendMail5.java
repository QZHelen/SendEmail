package sendEmail_1;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import javax.activation.*;

public class SendMail5 {
	public static void main(String[] args) throws Exception {
		try {

			String host = "hostAddress";	// hostAddress
			String from = "from@email.com";	// from email
			String pass = "pw";	// password
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
						
				Session session = Session.getDefaultInstance(props, new GMailAuthenticator(from, pass));

				MimeMessage message = new MimeMessage(session);
				Address fromAddress = new InternetAddress(from);
				String to = "toEmail@email.com";	// to email
				Address toAddress = new InternetAddress(to);
				message.setFrom(fromAddress);
				message.setRecipient(Message.RecipientType.TO, toAddress);

				message.setSubject("[重要]等待接机申请确认");	// Subject of the email
				message.setText("\n您好，\n\nUNCFACSS中国学生会收到您的接机申请，请您核对以下信息：" 
						+ "\n\n祝好！" + "\nUNC FACSS");	// Content
				Transport transport = session.getTransport("smtp");
				transport.connect(host, from, pass);
				message.saveChanges();
				Transport.send(message);
				transport.close();

		} catch (Exception ex) {

			System.out.println("<html><head></head><body>");
			System.out.println("ERROR: " + ex);
			System.out.println("</body></html>");
		}
	}
}

class GMailAuthenticator extends Authenticator {
	String user;
	String pw;

	public GMailAuthenticator(String username, String password) {
		super();
		this.user = username;
		this.pw = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, pw);
	}
}
