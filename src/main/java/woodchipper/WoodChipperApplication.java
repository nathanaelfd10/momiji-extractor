package woodchipper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class WoodChipperApplication {

	@Bean
	public Runner runner() {
		return new Runner();
	}

	public static void main(String[] args) {
		SpringApplication.run(WoodChipperApplication.class, args);
	}

}
