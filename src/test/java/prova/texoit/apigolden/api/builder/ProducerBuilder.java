package prova.texoit.apigolden.api.builder;

import prova.texoit.apigolden.api.entities.Producer;

public class ProducerBuilder {

	private Producer producer = new Producer();

	public ProducerBuilder withName(String name) {
		producer.setName(name);
		return this;
	}

	public Producer build() {
		return producer;
	}

}
