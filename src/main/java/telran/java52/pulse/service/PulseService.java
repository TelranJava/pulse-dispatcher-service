package telran.java52.pulse.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import telran.java52.pulse.dto.PulseDto;

@Configuration
@RequiredArgsConstructor
public class PulseService {

	final StreamBridge streamBridge;
	
	@Value("${pulse.min:50}") // переменные обьявлены в appl properties, если переменной не существует то через ":" можно указать дефолтное значение 
	private int minPulse;
	@Value("${pulse.max:100}")
	private int maxPulse;

	@Bean
	Consumer<PulseDto> dispatchData() {
		return (data) -> {
			if (data.getPayload() < minPulse) {
				// send to lowPulseTopic
				streamBridge.send("lowpulse-out-0", data);
				return;
			}
			if (data.getPayload() > maxPulse) {
				// send to highPulseTopic
				streamBridge.send("highpulse-out-0", data);
				return;
			}

			long delay = System.currentTimeMillis() - data.getTimestamp();
			System.out.println("Halthy - delay: " + delay + ",id: " + data.getId() + ", pulse: " + data.getPayload());
		};
	}
}
