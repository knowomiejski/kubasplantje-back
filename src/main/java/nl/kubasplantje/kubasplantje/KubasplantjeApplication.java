package nl.kubasplantje.kubasplantje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import nl.kubasplantje.kubasplantje.configuration.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class KubasplantjeApplication {
	public static void main(String[] args) {
		SpringApplication.run(KubasplantjeApplication.class, args);
	}
}
