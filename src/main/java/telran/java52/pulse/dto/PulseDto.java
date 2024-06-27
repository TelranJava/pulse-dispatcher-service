package telran.java52.pulse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@EqualsAndHashCode(of = "id")
public class PulseDto {
	int id;
	long timestamp;
	int payload;
}


// https://docs.spring.io/spring-cloud-stream/docs/3.2.9/reference/html/spring-cloud-stream.html#spring-cloud-stream-preface-creating-sample-application