package prova.texoit.apigolden.api.builder;

import prova.texoit.apigolden.api.entities.Studio;

public class StudioBuilder {

	private Studio studio = new Studio();

	public StudioBuilder withName(String name) {
		studio.setName(name);
		return this;
	}

	public Studio build() {
		return studio;
	}

}
