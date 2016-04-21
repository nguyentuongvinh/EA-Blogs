package cs544.lab.ea_blogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;

public class SchedulingReport {
	@Autowired
	private MailSender mailSender;
	
//	@Scheduled(cron="0 0/1 * * * *")
	@Scheduled(cron="0 0 0 * * *")
	public void reportNumOfArticlesPosted() {
		System.out.println("Scheduling is running");
		
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("eablobtest@gmail.com");
		smm.setTo("phaolovinh@gmail.com");
		smm.setSubject("EA Blog Article Report");
		smm.setText("This is scheduling task report!!!");
		
		mailSender.send(smm);
	}
}
