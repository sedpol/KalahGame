package tr.com.sedatpolat.kalah;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tr.com.sedatpolat.kalah.core.service.KalahService;
import tr.com.sedatpolat.kalah.core.service.KalahServiceImpl;
import tr.com.sedatpolat.kalah.core.service.PlayerService;
import tr.com.sedatpolat.kalah.core.service.PlayerServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	public KalahService kalahService() {
		return new KalahServiceImpl();
	}
	
	@Bean
	public PlayerService playerService() {
		return new PlayerServiceImpl();
	}
}
