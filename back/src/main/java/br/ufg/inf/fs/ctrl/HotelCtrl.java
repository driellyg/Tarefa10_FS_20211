package br.ufg.inf.fs.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.HotelBusiness;
import br.ufg.inf.fs.entities.Hotel;
import br.ufg.inf.fs.exceptions.HotelException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "hoteis")
public class HotelCtrl {

	@Autowired
	private HotelBusiness business;

	@GetMapping
	public ResponseEntity<List<Hotel>> findAll() {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		List<Hotel> list = new ArrayList<Hotel>();
		try {
			list = business.findAll();
			if (list.size() == 0) {
				headers.add("message", Messages.get("0207"));
			}
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<List<Hotel>>(list, headers, status);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Hotel> findById(@PathVariable Integer id) {
		Hotel retorno = new Hotel();

		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		try {
			retorno = business.findById(id);
			if (retorno == null) {
				headers.add("message", Messages.get("0207"));
			}
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<Hotel>(retorno, headers, status);
	}

	@PostMapping
	public ResponseEntity<Hotel> insert(@RequestBody Hotel hotel) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.CREATED;

		try {
			hotel = business.insert(hotel);
			headers.add("message", Messages.get("0201"));
		} catch (HotelException e) {
			headers.add("message", Messages.get(e.getMessage()));
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			headers.add("message", Messages.get("0202"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Hotel>(hotel, headers, status);
	}

	@PutMapping
	public ResponseEntity<Hotel> update(@RequestBody Hotel hotel) {

		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			hotel = business.update(hotel);
			headers.add("message", Messages.get("0203"));
		} catch (HotelException e) {
			headers.add("message", Messages.get(e.getMessage()));
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			headers.add("message", Messages.get("0204"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Hotel>(hotel, headers, status);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.NO_CONTENT;

		try {
			business.delete(id);
		} catch (Exception e) {
			headers.add("message", Messages.get("0206"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Void>(headers, status);
	}

	@GetMapping("/name/{str}")
	public ResponseEntity<List<Hotel>> findByName(@PathVariable String str) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		List<Hotel> list = new ArrayList<Hotel>();
		try {
			list = business.findName(str);
			if (list.size() == 0) {
				headers.add("message", Messages.get("0207"));
			}
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<List<Hotel>>(list, headers, status);

	}

	@GetMapping("/estrela/{qtd}")
	public ResponseEntity<List<Hotel>> findQtdEstrelas(@PathVariable Integer qtd) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		List<Hotel> list = new ArrayList<Hotel>();
		try {
			list = business.findEstelas(qtd);
			if (list.size() == 0) {
				headers.add("message", Messages.get("0207"));
			}
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<List<Hotel>>(list, headers, status);

	}
}
