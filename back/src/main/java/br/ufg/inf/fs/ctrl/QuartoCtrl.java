package br.ufg.inf.fs.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.exceptions.QuartoException;
import br.ufg.inf.fs.business.QuartoBusiness;
import br.ufg.inf.fs.entities.Quarto;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "quartos")
public class QuartoCtrl {

	@Autowired
	private QuartoBusiness business;

	@GetMapping
	public ResponseEntity<List<Quarto>> findAll() {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		List<Quarto> list = new ArrayList<Quarto>();

		try {
			list = business.findAll();
			if (list.size() == 0) {
				headers.add("message", Messages.get("0107"));
			}
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<List<Quarto>>(list, headers, status);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Quarto> findById(@PathVariable Integer id) {
		Quarto retorno = new Quarto();

		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		try {
			retorno = business.findById(id);
			if (retorno == null) {
				headers.add("message", Messages.get("0107"));
			}
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<Quarto>(retorno, headers, status);
	}

	@PostMapping
	public ResponseEntity<Quarto> insert(@RequestBody Quarto quarto) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.CREATED;

		try {
			quarto = business.insert(quarto);
			headers.add("message", Messages.get("0101"));
		} catch (QuartoException e) {
			headers.add("message", Messages.get(e.getMessage()));
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			headers.add("message", Messages.get("0102"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Quarto>(quarto, headers, status);
	}

	@PutMapping
	public ResponseEntity<Quarto> update(@RequestBody Quarto quarto) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			quarto = business.update(quarto);
			headers.add("message", Messages.get("0103"));
		} catch (QuartoException e) {
			headers.add("message", Messages.get(e.getMessage()));
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			headers.add("message", Messages.get("0104"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Quarto>(quarto, headers, status);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.NO_CONTENT;

		try {
			business.delete(id);
		} catch (Exception e) {
			headers.add("message", Messages.get("0106"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Void>(headers, status);
	}

}
