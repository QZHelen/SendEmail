package sendEmail_1;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import javax.activation.*;

public class SendMultipleEmails_3 {
	public static void main(String[] args) throws Exception {
		try {

			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);	// host address
			props.put("mail.smtp.user", from);	// from email
			props.put("mail.smtp.password", pass);	// password
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			Session session = Session.getDefaultInstance(props, new GMailAuthenticator(from, pass));
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);

			String[] strs = new String[] { "1", "2", "3", "4" };

			for (String name : strs) {

				String to = ""; // to email address
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				InternetAddress[] address = { new InternetAddress(to) };
				message.setRecipients(Message.RecipientType.TO, address);

				message.setSubject("[重要]UNC接机志愿者确认邮件");
				message.setText(ParseFile1.parse(name));

				message.saveChanges();
				transport.sendMessage(message, address);

			}
			transport.close();

		} catch (Exception ex) {

			System.out.println("<html><head></head><body>");
			System.out.println("ERROR: " + ex);
			System.out.println("</body></html>");
		}
	}
}