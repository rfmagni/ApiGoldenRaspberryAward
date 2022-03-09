package prova.texoit.apigolden.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import prova.texoit.apigolden.api.builder.GoldenRaspberryAwardBuilder;
import prova.texoit.apigolden.api.builder.MovieBuilder;
import prova.texoit.apigolden.api.builder.ProducerBuilder;
import prova.texoit.apigolden.api.builder.StudioBuilder;
import prova.texoit.apigolden.api.dto.IntervalAwardDTO;
import prova.texoit.apigolden.api.dto.IntervalGoldenRaspberryAwardDTO;
import prova.texoit.apigolden.api.entities.GoldenRaspberryAward;
import prova.texoit.apigolden.api.entities.Movie;
import prova.texoit.apigolden.api.entities.Producer;
import prova.texoit.apigolden.api.entities.Studio;
import prova.texoit.apigolden.api.repository.GoldenRaspberryAwardRepository;
import prova.texoit.apigolden.api.repository.MovieRepository;
import prova.texoit.apigolden.api.repository.ProducerRepository;
import prova.texoit.apigolden.api.repository.StudioRepository;


@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GoldenRaspberryAwardServiceTest {

	@Autowired
	private ProducerRepository producerRepository;

	@Autowired
	private StudioRepository studioRepository;

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private GoldenRaspberryAwardRepository goldenRaspberryAwardRepository;

	@Autowired
	private GoldenRaspberryAwardService goldenRaspberryAwardService;
	

	@Test
	@Order(1)
	public void deve_retornar_premios_no_intervalo_minimo_e_maximo_quando_existir_so_um_produtor() {
		Producer producerDerek = createProducer("Bo Derek");
		Studio studioCannon = createStudio("Cannon Films");
		Movie movieBoleto = createMovie("Boleto", producerDerek, studioCannon, 1984);
		createGoldenRaspberryAward(movieBoleto, 1984);
		
		Studio studioTriumph = createStudio("Triumph Releasing");
		Movie movieGhosts = createMovie("Ghosts Can't Do It", producerDerek, studioTriumph, 1990);
		createGoldenRaspberryAward(movieGhosts, 1990);
		
		IntervalGoldenRaspberryAwardDTO intervalAwards = goldenRaspberryAwardService.findIntervalAward();
		
		assertNotNull(intervalAwards);
		assertNotNull(intervalAwards.getIntervalsMin());
		assertNotNull(intervalAwards.getIntervalsMax());
	}
	
	@Test
	@Order(2)
	public void deve_retornar_premios_no_intervalo_minimo_maximo_quando_existir_dois_filmes_mesmo_intervalo () {
		Producer producerDerek = createProducer("Bo Derek");
		Studio studioCannon = createStudio("Cannon Films");
		Movie movieBoleto = createMovie("Boleto", producerDerek, studioCannon, 1984);
		createGoldenRaspberryAward(movieBoleto, 1984);
		
		Movie movieAvatar = createMovie("Avatar", producerDerek, studioCannon, 1985);
		 createGoldenRaspberryAward(movieAvatar, 1985);
		
		Producer producerJohn = createProducer("John");
		Movie movieJohn1986  = createMovie("John", producerJohn, studioCannon, 1986);
		createGoldenRaspberryAward(movieJohn1986, 1986);
		
		Movie movieJohn1987 = createMovie("John 1987", producerJohn, studioCannon, 1987);
		createGoldenRaspberryAward(movieJohn1987, 1987);
		
		Producer producerMax = createProducer("Max");
		Movie movieJohn1995  = createMovie("Max 1995", producerMax, studioCannon, 1995);
		createGoldenRaspberryAward(movieJohn1995, 1995);
		
		Movie movieJohn2000 = createMovie("Max 2000", producerMax, studioCannon, 2000);
		createGoldenRaspberryAward(movieJohn2000, 2000);
	
		IntervalGoldenRaspberryAwardDTO intervalAwards = goldenRaspberryAwardService.findIntervalAward();
		
		assertNotNull(intervalAwards);
		assertEquals(intervalAwards.getIntervalsMin().size(), 2);
		assertEquals(intervalAwards.getIntervalsMax().size(), 1);
		
		IntervalAwardDTO intervalMinAward1 = intervalAwards.getIntervalsMin().get(0);
		IntervalAwardDTO intervalMinAward2 = intervalAwards.getIntervalsMin().get(1);
		IntervalAwardDTO intervalMaxAward1 = intervalAwards.getIntervalsMax().get(0);
		
		assertEquals(intervalMinAward1.getProducer(), producerDerek.getName());
		assertEquals(intervalMinAward1.getPreviousWin(), 1984);
		assertEquals(intervalMinAward1.getFollowingWin(), 1985);
		assertEquals(intervalMinAward1.getInterval(), 1);
		assertEquals(intervalMinAward2.getProducer(), producerJohn.getName());
		assertEquals(intervalMinAward2.getPreviousWin(), 1986);
		assertEquals(intervalMinAward2.getFollowingWin(), 1987);
		assertEquals(intervalMinAward2.getInterval(), 1);
		
		assertEquals(intervalMaxAward1.getProducer(), producerMax.getName());
		assertEquals(intervalMaxAward1.getPreviousWin(), 1995);
		assertEquals(intervalMaxAward1.getFollowingWin(), 2000);
		assertEquals(intervalMaxAward1.getInterval(), 5);
	}
	
	@Test
	@Order(3)
	public void deve_retornar_premios_no_intervalo_minimo_maximo_quando_existir_filmes_intervalos_diferentes_mesmo_intervalo () {
		Studio studioCannon = createStudio("Cannon Films");
		
		Producer producerJohn = createProducer("John");
		Movie movieJohn1986  = createMovie("John", producerJohn, studioCannon, 1986);
		createGoldenRaspberryAward(movieJohn1986, 1986);
		
		Movie movieJohn1989 = createMovie("John 1989", producerJohn, studioCannon, 1989);
		createGoldenRaspberryAward(movieJohn1989, 1989);
		
		Producer producerDerek = createProducer("Bo Derek");
		Movie movieBoleto = createMovie("Boleto", producerDerek, studioCannon, 1984);
		createGoldenRaspberryAward(movieBoleto, 1984);
		
		Movie movieAvatar = createMovie("Avatar", producerDerek, studioCannon, 1985);
		createGoldenRaspberryAward(movieAvatar, 1985);

		Producer producerMax = createProducer("Max");
		Movie movieJohn1995  = createMovie("Max 1995", producerMax, studioCannon, 1995);
		createGoldenRaspberryAward(movieJohn1995, 1995);
		
		Movie movieJohn2000 = createMovie("Max 2000", producerMax, studioCannon, 2000);
		createGoldenRaspberryAward(movieJohn2000, 2000);
	
		IntervalGoldenRaspberryAwardDTO intervalAwards = goldenRaspberryAwardService.findIntervalAward();
		
		assertNotNull(intervalAwards);
		assertEquals(intervalAwards.getIntervalsMin().size(), 1);
		assertEquals(intervalAwards.getIntervalsMax().size(), 1);
		
		IntervalAwardDTO intervalMinAward1 = intervalAwards.getIntervalsMin().get(0);
		IntervalAwardDTO intervalMaxAward1 = intervalAwards.getIntervalsMax().get(0);
		
		assertEquals(intervalMinAward1.getProducer(), producerDerek.getName());
		assertEquals(intervalMaxAward1.getProducer(), producerMax.getName());
	}

	private Producer createProducer(String name) {
		ProducerBuilder builder = new ProducerBuilder();

		Producer producer = builder.withName(name).build();
		
		producer = producerRepository.save(producer);

		return producer;
	}

	private Studio createStudio(String name) {
		StudioBuilder builder = new StudioBuilder();

		Studio studio = builder.withName(name).build();
		
		studio = studioRepository.save(studio);

		return studio;
	}
	
	private Movie createMovie(String name, Producer producer, Studio studio, Integer year) {
		MovieBuilder builder = new MovieBuilder();

		Movie movie = builder.withName(name)
				.withProducer(producer)
				.withStudio(studio)
				.withYear(year)
				.build();
		
		movie = movieRepository.save(movie);

		return movie;
	}
	
	private GoldenRaspberryAward createGoldenRaspberryAward(Movie movie, Integer year) {
		GoldenRaspberryAwardBuilder builder = new GoldenRaspberryAwardBuilder();

		GoldenRaspberryAward goldenRaspberryAward = builder.withMovie(movie)
				.withYear(year)
				.build();
		
		goldenRaspberryAward = goldenRaspberryAwardRepository.save(goldenRaspberryAward);

		return goldenRaspberryAward;
	}
	

}
