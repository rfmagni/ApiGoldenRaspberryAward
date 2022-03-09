package prova.texoit.apigolden.api.services;

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

	public Producer convertMovieBeanToProducer(MovieBean movieBean) {

		Producer producer = new Producer();

		producer.setName(movieBean.getProducers());

		return producer;
	}

}
