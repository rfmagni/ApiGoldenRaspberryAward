package prova.texoit.apigolden.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prova.texoit.apigolden.api.entities.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

	
    public Producer findByName(String name);
}
