package prova.texoit.apigolden.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prova.texoit.apigolden.api.bean.MovieBean;
import prova.texoit.apigolden.api.entities.Movie;
import prova.texoit.apigolden.api.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	public Movie insertIfNotExists(Movie movie) {
		Movie movieData = movieRepository.findByName(movie.getName());

		if (movieData != null) {
			return movieData;
		}

		movie = movieRepository.save(movie);

		return movie;
	}

	public Movie convertMovieBeanToMovie(MovieBean movieBean) {
		Movie movie = new Movie();

		movie.setName(movieBean.getTitle());
		movie.setYear(movieBean.getYear());

		return movie;
	}

}
