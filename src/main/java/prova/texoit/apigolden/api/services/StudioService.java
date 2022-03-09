package prova.texoit.apigolden.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prova.texoit.apigolden.api.bean.MovieBean;
import prova.texoit.apigolden.api.entities.Studio;
import prova.texoit.apigolden.api.repository.StudioRepository;

@Service
public class StudioService {

	@Autowired
	private StudioRepository studioRepository;

	public Studio insertIfNotExists(Studio studio) {

		Studio studioData = studioRepository.findByName(studio.getName());

		if (studioData != null) {
			return studioData;
		}

		studio = studioRepository.save(studio);

		return studio;
	}

	public Studio convertMovieBeanToStudio(MovieBean movieBean) {
		Studio studio = new Studio();

		studio.setName(movieBean.getStudios());

		return studio;
	}

}
