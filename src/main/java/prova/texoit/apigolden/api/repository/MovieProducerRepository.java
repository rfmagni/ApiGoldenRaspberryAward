package prova.texoit.apigolden.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prova.texoit.apigolden.api.entities.MovieProducer;

public interface MovieProducerRepository extends JpaRepository<MovieProducer, Long> {

	MovieProducer findByMovieIdAndProducerId(Long movie, Long producer);
}
