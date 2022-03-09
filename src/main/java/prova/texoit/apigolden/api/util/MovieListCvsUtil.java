package prova.texoit.apigolden.api.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import prova.texoit.apigolden.api.bean.MovieBean;

public class MovieListCvsUtil {
	
	public static List<MovieBean> readFileCVS() throws IOException {

		Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/movielist.csv"));

		CsvToBean<MovieBean> csvToBean = new CsvToBeanBuilder<MovieBean>(reader)
				.withSeparator(';')
				.withType(MovieBean.class)
				.build();

		List<MovieBean> movieListBeans = csvToBean.parse();

		return movieListBeans;

	}

}
