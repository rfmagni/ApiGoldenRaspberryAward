package prova.texoit.apigolden.api.builder;

import prova.texoit.apigolden.api.entities.Movie;
import prova.texoit.apigolden.api.entities.MovieProducer;
import prova.texoit.apigolden.api.entities.Producer;

public class MovieProducerBuilder {

	private MovieProducer movieProducer = new MovieProducer();

	public MovieProducerBuilder withMovie(Movie movie) {
		movieProducer.setMovie(movie);
		return this;
	}

	public MovieProducerBuilder withProducer(Producer producer) {
		movieProducer.setProducer(producer);
		return this;
	}

	public MovieProducer build() {
		return movieProducer;
	}

}
