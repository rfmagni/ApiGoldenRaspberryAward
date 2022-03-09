package prova.texoit.apigolden.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prova.texoit.apigolden.api.entities.GoldenRaspberryAward;

public interface GoldenRaspberryAwardRepository extends JpaRepository<GoldenRaspberryAward, Long> {

	GoldenRaspberryAward findByMovieId(Long movie);
}
