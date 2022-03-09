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
@Table(name = "GOLDENRASPBERRYAWARD")
public class GoldenRaspberryAward {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@JoinColumn(name = "movie_id", referencedColumnName = "id")
	@ManyToOne
	private Movie movie;

	@Column(nullable = false)
	private Integer year;

	public GoldenRaspberryAward() {

	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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
		GoldenRaspberryAward other = (GoldenRaspberryAward) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "GoldenRaspberryAward{" + "id=" + id + '}';
	}

}
