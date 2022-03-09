package prova.texoit.apigolden.api.dto;

import prova.texoit.apigolden.api.entities.GoldenRaspberryAward;
import prova.texoit.apigolden.api.entities.Movie;

public class GoldenRaspberryAwardDTO {

	private Long id;

	private Long idMovie;

	private Integer year;

	public GoldenRaspberryAwardDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Long idMovie) {
		this.idMovie = idMovie;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public GoldenRaspberryAward convertDTOToEntity() {
		GoldenRaspberryAward goldenRaspberryAward = new GoldenRaspberryAward();

		goldenRaspberryAward.setId(id);
		goldenRaspberryAward.setMovie(new Movie(idMovie));
		goldenRaspberryAward.setYear(year);

		return goldenRaspberryAward;
	}

	public static GoldenRaspberryAwardDTO convertEntityToDTO(GoldenRaspberryAward goldenRaspberryAward) {
		GoldenRaspberryAwardDTO goldenRaspberryAwardDTO = new GoldenRaspberryAwardDTO();

		goldenRaspberryAwardDTO.setId(goldenRaspberryAward.getId());
		goldenRaspberryAwardDTO.setIdMovie(goldenRaspberryAward.getMovie().getId());
		goldenRaspberryAwardDTO.setYear(goldenRaspberryAward.getYear());

		return goldenRaspberryAwardDTO;
	}

}
