package prova.texoit.apigolden.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prova.texoit.apigolden.api.entities.Movie;
import prova.texoit.apigolden.api.entities.MovieProducer;
import prova.texoit.apigolden.api.entities.Producer;
import prova.texoit.apigolden.api.repository.MovieProducerRepository;
import prova.texoit.apigolden.api.repository.MovieRepository;
import prova.texoit.apigolden.api.repository.ProducerRepository;

@Service
public class MovieProducerService {

	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	@Autowired
	private MovieProducerRepository movieProducerRepository;

	public List<MovieProducer> insertIfNotExists(Movie movie, List<Producer> producers) {
		List<MovieProducer> movieProducers = new ArrayList<MovieProducer>();
		
		producers.stream().forEach(p -> {
			MovieProducer movieProducer = new MovieProducer();
			movieProducer.setMovie(movieRepository.getById(movie.getId()));
			movieProducer.setProducer(producerRepository.getById(p.getId()));

			movieProducer = insertIfNotExists(movieProducer);

			movieProducers.add(movieProducer);
		});

		return movieProducers;
	}

	public MovieProducer insertIfNotExists(MovieProducer movieProducer) {
		MovieProducer movieProducerData = movieProducerRepository
				.findByMovieIdAndProducerId(movieProducer.getMovie().getId(), movieProducer.getProducer().getId());

		if (movieProducerData != null) {
			return movieProducerData;
		}

		movieProducer = movieProducerRepository.save(movieProducer);

		return movieProducer;
	}

}
