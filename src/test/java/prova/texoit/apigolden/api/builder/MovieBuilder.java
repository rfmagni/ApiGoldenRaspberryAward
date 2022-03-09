package prova.texoit.apigolden.api.builder;

import prova.texoit.apigolden.api.entities.Movie;
import prova.texoit.apigolden.api.entities.Producer;
import prova.texoit.apigolden.api.entities.Studio;

public class MovieBuilder {

	private Movie movie = new Movie();

	public MovieBuilder withName(String name) {
		movie.setName(name);
		return this;
	}

	public MovieBuilder withProducer(Producer producer) {
		movie.setProducer(producer);
		return this;
	}

	public MovieBuilder withStudio(Studio studio) {
		movie.setStudio(studio);
		return this;
	}

	public MovieBuilder withYear(Integer year) {
		movie.setYear(year);
		return this;
	}

	public Movie build() {
		return movie;
	}

}
