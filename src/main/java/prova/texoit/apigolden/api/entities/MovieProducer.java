package prova.texoit.apigolden.api.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIEPRODUCER")
public class MovieProducer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@JoinColumn(name = "movie_id", referencedColumnName = "id")
	@ManyToOne
	private Movie movie;

	@JoinColumn(name = "producer_id", referencedColumnName = "id")
	@ManyToOne
	private Producer producer;

	public MovieProducer() {

	}

	public MovieProducer(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producer getProducer() {
		return producer;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
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
		MovieProducer other = (MovieProducer) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "MovieProducer{" + "id=" + id + " }";
	}

}
