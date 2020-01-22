package uce.edu.ec.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "uce.edu.ec.app")
public class BienesAppBootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BienesAppBootApplication.class, args);

	}
}
