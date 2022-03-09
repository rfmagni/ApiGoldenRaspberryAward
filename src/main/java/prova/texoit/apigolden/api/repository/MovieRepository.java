package prova.texoit.apigolden.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prova.texoit.apigolden.api.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	public Movie findByName(String name);
}
