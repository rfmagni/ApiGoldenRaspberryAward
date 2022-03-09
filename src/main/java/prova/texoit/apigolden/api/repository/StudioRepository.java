package prova.texoit.apigolden.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prova.texoit.apigolden.api.entities.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long> {

	public Studio findByName(String name);
}
