package utilities;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail("teamschool8@gmail.com", "jorrom@gmail.com", "Testing123", "Testing only \n\n Hello Spring Email Sender");
	}
}