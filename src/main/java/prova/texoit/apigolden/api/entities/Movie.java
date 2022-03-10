package prova.texoit.apigolden.api.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", length = 300, nullable = false)
	private String name;

	@ManyToOne
	private Studio studio;

	@Column(nullable = false)
	private Integer year;

	@OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
	private List<MovieProducer> movieProducers;

	public Movie() {

	}

	public Movie(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Studio getStudio() {
		return studio;
	}

	public void setStudio(Studio studio) {
		this.studio = studio;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<MovieProducer> getMovieProducers() {
		return movieProducers;
	}

	public void setMovieProducers(List<MovieProducer> movieProducers) {
		this.movieProducers = movieProducers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Movie{" + "id=" + id + ", name='" + name + '\'' + '}';
	}

}
