package prova.texoit.apigolden.api.builder;

import prova.texoit.apigolden.api.entities.GoldenRaspberryAward;
import prova.texoit.apigolden.api.entities.Movie;

public class GoldenRaspberryAwardBuilder {

	private GoldenRaspberryAward goldenRaspberryAward = new GoldenRaspberryAward();

	public GoldenRaspberryAwardBuilder withMovie(Movie movie) {
		goldenRaspberryAward.setMovie(movie);
		return this;
	}

	public GoldenRaspberryAwardBuilder withYear(Integer year) {
		goldenRaspberryAward.setYear(year);
		return this;
	}

	public GoldenRaspberryAward build() {
		return goldenRaspberryAward;
	}

}
