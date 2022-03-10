package prova.texoit.apigolden.api.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prova.texoit.apigolden.api.bean.MovieBean;
import prova.texoit.apigolden.api.entities.Producer;
import prova.texoit.apigolden.api.repository.ProducerRepository;

@Service
public class ProducerService {

	@Autowired
	private ProducerRepository producerRepository;

	public Producer insertIfNotExists(Producer producer) {
		Producer producerData = producerRepository.findByName(producer.getName());

		if (producerData != null) {
			return producerData;
		}

		producer = producerRepository.save(producer);

		return producer;
	}

	public List<Producer> convertMovieBeanToProducer(MovieBean movieBean) {
		List<Producer> producers = new ArrayList<Producer>();
		
		List<String> namesProducersWithAnd = Arrays.asList(movieBean.getProducers().split(","));

		List<String> namesProducers = new ArrayList<String>();

		namesProducersWithAnd.stream().forEach((name) -> {
			namesProducers.addAll(Arrays.asList(name.split("and")));
		});

		namesProducers.stream().forEach(name -> {
			Producer producer = new Producer();
			producer.setName(name.trim());
			producers.add(producer);
		});

		return producers;
	}
	

}
