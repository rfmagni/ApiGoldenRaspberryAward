package prova.texoit.apigolden.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import prova.texoit.apigolden.api.dto.GoldenRaspberryAwardDTO;
import prova.texoit.apigolden.api.dto.IntervalGoldenRaspberryAwardDTO;
import prova.texoit.apigolden.api.entities.GoldenRaspberryAward;
import prova.texoit.apigolden.api.services.GoldenRaspberryAwardService;

@RestController
@RequestMapping(path = "/api/v1/goldenraspberryaward")
public class GoldenRaspberryAwardResource {

	@Autowired
	private GoldenRaspberryAwardService goldenRaspberryAwardService;
	
	@RequestMapping(value = "/intervalaward", method = RequestMethod.GET)
	public ResponseEntity<IntervalGoldenRaspberryAwardDTO> intervalAward() {
		IntervalGoldenRaspberryAwardDTO goldenRaspberryAwardDTO = goldenRaspberryAwardService.findIntervalAward();
		
		return new ResponseEntity<>(goldenRaspberryAwardDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<GoldenRaspberryAwardDTO> save(@RequestBody GoldenRaspberryAwardDTO dto) {
		GoldenRaspberryAward goldenRaspberryAward = dto.convertDTOToEntity();

		goldenRaspberryAward = goldenRaspberryAwardService.save(goldenRaspberryAward);

		return new ResponseEntity<>(GoldenRaspberryAwardDTO.convertEntityToDTO(goldenRaspberryAward),
				HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<GoldenRaspberryAwardDTO> update(@PathVariable("id") Long id, @RequestBody GoldenRaspberryAwardDTO dto) {
		GoldenRaspberryAward goldenRaspberryAward = dto.convertDTOToEntity();
		goldenRaspberryAward.setId(id);
		
		goldenRaspberryAward = goldenRaspberryAwardService.update(id, goldenRaspberryAward);

		return new ResponseEntity<>(GoldenRaspberryAwardDTO.convertEntityToDTO(goldenRaspberryAward),
				HttpStatus.OK);
	}
	
	@PatchMapping(path = "/updateYear/{id}")
	public ResponseEntity<GoldenRaspberryAwardDTO> updateYear(@PathVariable("id") Long id, @RequestBody GoldenRaspberryAwardDTO dto) {
		GoldenRaspberryAward goldenRaspberryAward = goldenRaspberryAwardService.findByIdOrElseThrow(id);
		goldenRaspberryAward.setYear(dto.getYear());

		goldenRaspberryAward = goldenRaspberryAwardService.update(id, goldenRaspberryAward);

		return new ResponseEntity<>(GoldenRaspberryAwardDTO.convertEntityToDTO(goldenRaspberryAward),
				HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		goldenRaspberryAwardService.findByIdOrElseThrow(id);

		goldenRaspberryAwardService.deleteById(id);
		
		String response = "Deleção feita com sucesso";

		return new ResponseEntity<String>(response, HttpStatus.NO_CONTENT);
	}

}
