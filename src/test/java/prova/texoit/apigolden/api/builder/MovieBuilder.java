package prova.texoit.apigolden.api.builder;

import java.util.List;

import prova.texoit.apigolden.api.entities.Movie;
import prova.texoit.apigolden.api.entities.MovieProducer;
import prova.texoit.apigolden.api.entities.Studio;

public class MovieBuilder {

	private Movie movie = new Movie();

	public MovieBuilder withName(String name) {
		movie.setName(name);
		return this;
	}

	public MovieBuilder withMovieProducers(List<MovieProducer> movieProducers) {
		movie.setMovieProducers(movieProducers);
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
