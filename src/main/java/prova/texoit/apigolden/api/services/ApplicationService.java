package prova.texoit.apigolden.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class ApplicationService implements ApplicationRunner {

	@Autowired
	private GoldenRaspberryAwardService goldenRaspberryAwardService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		goldenRaspberryAwardService.saveCVS();

	}

}
