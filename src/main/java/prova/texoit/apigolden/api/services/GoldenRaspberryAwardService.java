package prova.texoit.apigolden.api.services;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import prova.texoit.apigolden.api.bean.MovieBean;
import prova.texoit.apigolden.api.dto.IntervalAwardDTO;
import prova.texoit.apigolden.api.dto.IntervalGoldenRaspberryAwardDTO;
import prova.texoit.apigolden.api.entities.GoldenRaspberryAward;
import prova.texoit.apigolden.api.entities.Movie;
import prova.texoit.apigolden.api.entities.MovieProducer;
import prova.texoit.apigolden.api.entities.Producer;
import prova.texoit.apigolden.api.entities.Studio;
import prova.texoit.apigolden.api.repository.GoldenRaspberryAwardRepository;
import prova.texoit.apigolden.api.util.MovieListCvsUtil;

@Service
public class GoldenRaspberryAwardService {

	@Autowired
	private ProducerService producerService;

	@Autowired
	private StudioService studioService;

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieProducerService movieProducerService;

	@Autowired
	private GoldenRaspberryAwardRepository goldenRaspberryAwardRepository;

	public void saveCVS() throws IOException {
		List<MovieBean> movies = MovieListCvsUtil.readFileCVS();

		for (MovieBean movieBean : movies) {

			List<Producer> producers = saveProducer(movieBean);

			Studio studio = saveStudio(movieBean);

			Movie movie = saveMovie(movieBean, producers, studio);

			saveGoldenRaspberryAward(movieBean, movie);
		}
	}
	
	public GoldenRaspberryAward save(GoldenRaspberryAward goldesnRaspberryAward) {
		return goldenRaspberryAwardRepository.save(goldesnRaspberryAward);
	}
	
	public GoldenRaspberryAward update(Long id, GoldenRaspberryAward goldenRaspberryAward) {
		Optional<GoldenRaspberryAward> goldenRaspberryAwardData = goldenRaspberryAwardRepository.findById(id);

		if (goldenRaspberryAwardData.isPresent()) {

			if (!goldenRaspberryAward.getId().equals(id)) {
				String mensagemErro = MessageFormat.format(
						"O Id {0} enviado ?? diferente do id {1} da entidade envidada. ", id,
						goldenRaspberryAward.getId());

				throw new RuntimeException(mensagemErro);
			}

			return save(goldenRaspberryAward);
		} else {

			String mensagemErro = MessageFormat
					.format("N??o foi localizado GoldenRaspberryAward para altera????o com o id {0}", id);

			throw new RuntimeException(mensagemErro);
		}
	}
	
	public void deleteById(Long id) {
		goldenRaspberryAwardRepository.deleteById(id);
	}
	
	public GoldenRaspberryAward findByIdOrElseThrow(Long id) {
		
		String mensagemErro = MessageFormat
				.format("N??o foi localizado GoldenRaspberryAward com o id {0}", id);
		
		return goldenRaspberryAwardRepository.findById(id).orElseThrow(() -> new RuntimeException(mensagemErro));
	}
	
	public IntervalGoldenRaspberryAwardDTO findIntervalAward() {
		HashMap<Integer, List<IntervalAwardDTO>> mapIntervalAwards = findIntervalBetweenAwards();

		IntervalGoldenRaspberryAwardDTO goldenRaspberryAwardDTO = new IntervalGoldenRaspberryAwardDTO();
		goldenRaspberryAwardDTO.setIntervalsMin(findIntervalMinBetweenAwards(mapIntervalAwards));
		goldenRaspberryAwardDTO.setIntervalsMax(findIntervalMaxBetweenAwards(mapIntervalAwards));

		return goldenRaspberryAwardDTO;
	}
	
	private HashMap<Integer, List<IntervalAwardDTO>> findIntervalBetweenAwards() {
		List<GoldenRaspberryAward> awards = goldenRaspberryAwardRepository.findAll(Sort.by(Sort.Direction.ASC, "year"));
		
		List<GoldenRaspberryAward> awardsSub = new ArrayList<GoldenRaspberryAward>(awards);
		
		HashMap<Integer, List<IntervalAwardDTO>> mapIntervalAwards = new HashMap<Integer, List<IntervalAwardDTO>>();

		for (GoldenRaspberryAward award : awards) {
			
			awardsSub.remove(award);
			
			for (GoldenRaspberryAward awardSub : awardsSub) {

				for (MovieProducer movieProducer : award.getMovie().getMovieProducers()) {
					
					Producer producerAward = movieProducer.getProducer();
					
					for (MovieProducer movieProducerSub : awardSub.getMovie().getMovieProducers()) {
						Producer producerAwardSub = movieProducerSub.getProducer();

						if (producerAward.equals(producerAwardSub)) {

							Integer interval = awardSub.getYear() - award.getYear();

							IntervalAwardDTO intervalAwardDTO = new IntervalAwardDTO(producerAward.getName(), interval,
									award.getYear(), awardSub.getYear());

							if (!mapIntervalAwards.containsKey(interval)) {
								List<IntervalAwardDTO> intervals = new ArrayList<IntervalAwardDTO>();
								intervals.add(intervalAwardDTO);
								
								mapIntervalAwards.put(interval,  intervals);
							} else {
								List<IntervalAwardDTO> intervals = mapIntervalAwards.get(interval);
								intervals.add(intervalAwardDTO);

								mapIntervalAwards.put(interval,  intervals);
							}
							
							break;

						}
						
					}

				}
			}
		}

		return mapIntervalAwards;
	}
	
	private List<IntervalAwardDTO> findIntervalMinBetweenAwards(
			HashMap<Integer, List<IntervalAwardDTO>> mapIntervalAwards) {

		return mapIntervalAwards.get(mapIntervalAwards.keySet().stream().findFirst().get());
	}
	
	private List<IntervalAwardDTO> findIntervalMaxBetweenAwards(HashMap<Integer, List<IntervalAwardDTO>> mapIntervalAwards) {
		Integer lastElement = null;

		for (Integer element : mapIntervalAwards.keySet()) {
			lastElement = element;
		}

		return mapIntervalAwards.get(lastElement);
	}
	
	private List<Producer> saveProducer(MovieBean movieBean) {
		List<Producer> producersSave = new ArrayList<Producer>();
		
		List<Producer> producersConverted = producerService.convertMovieBeanToProducer(movieBean);

		producersConverted.stream().forEach(producer -> {
			producersSave.add(producerService.insertIfNotExists(producer));
		});
		
		return producersSave;
	}

	private Studio saveStudio(MovieBean movieBean) {
		Studio studio = studioService.convertMovieBeanToStudio(movieBean);

		return studioService.insertIfNotExists(studio);
	}

	private Movie saveMovie(MovieBean movieBean, List<Producer> producers, Studio studio) {
		Movie movie = movieService.convertMovieBeanToMovie(movieBean);
		movie.setStudio(studio);

		movie = movieService.insertIfNotExists(movie);

		movieProducerService.insertIfNotExists(movie, producers);

		return movie;
	}

	private void saveGoldenRaspberryAward(MovieBean movieBean, Movie movie) {
		if (movieBean.getWinner() != null && !movieBean.getWinner().isEmpty() && movieBean.getWinner().equals("yes")) {

			GoldenRaspberryAward goldenRaspberryAward = new GoldenRaspberryAward();
			goldenRaspberryAward.setYear(movieBean.getYear());
			goldenRaspberryAward.setMovie(movie);

			GoldenRaspberryAward goldenRaspberryAwardData = goldenRaspberryAwardRepository.findByMovieId(movie.getId());

			if (goldenRaspberryAwardData == null) {
				goldenRaspberryAwardRepository.save(goldenRaspberryAward);
			}

		}
	}

}
