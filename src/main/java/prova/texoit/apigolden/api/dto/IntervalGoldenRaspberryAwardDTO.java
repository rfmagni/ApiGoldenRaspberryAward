package prova.texoit.apigolden.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntervalGoldenRaspberryAwardDTO {

	@JsonProperty("min")
	private List<IntervalAwardDTO> intervalsMin;

	@JsonProperty("max")
	private List<IntervalAwardDTO> intervalsMax;

	public IntervalGoldenRaspberryAwardDTO() {

	}

	public List<IntervalAwardDTO> getIntervalsMin() {
		return intervalsMin;
	}

	public void setIntervalsMin(List<IntervalAwardDTO> intervalsMin) {
		this.intervalsMin = intervalsMin;
	}

	public List<IntervalAwardDTO> getIntervalsMax() {
		return intervalsMax;
	}

	public void setIntervalsMax(List<IntervalAwardDTO> intervalsMax) {
		this.intervalsMax = intervalsMax;
	}

}
