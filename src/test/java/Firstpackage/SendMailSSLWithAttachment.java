package Firstpackage;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailSSLWithAttachment extends Firstclass{

	public static void mail(String[] args) {

		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");

		props.put("mail.smtp.socketFactory.port", "465");

		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("srilanakamrst@gmail.com", "pandian!@#");

			}

		});

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("travelsvsb@gmail.com"));

			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("travelspss3@gmail.com"));

			message.setSubject("pandian Firstclass Automation Result");

			BodyPart messageBodyPart1 = new MimeBodyPart();

			messageBodyPart1.setText("AUTOMATION RESULTS");

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filename = "C:\\Users\\Pandiyan\\eclipse-workspace\\Myfirstmavenproject\\test-output\\Default suite\\Default test.html";

			DataSource source = new FileDataSource(filename);

			messageBodyPart2.setDataHandler(new DataHandler(source));

			messageBodyPart2.setFileName(filename);

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart2);

			multipart.addBodyPart(messageBodyPart1);

			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Email Sent Successfully");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}
}


