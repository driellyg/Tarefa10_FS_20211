package br.ufg.inf.fs.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.HospedeBusiness;
import br.ufg.inf.fs.entities.Hospede;
import br.ufg.inf.fs.exceptions.HospedeException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "hospedes")
public class HospedeCtrl {

  @Autowired
  private HospedeBusiness business;

  @GetMapping
  public ResponseEntity<List<Hospede>> findAll() {
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.OK;
    List<Hospede> list = new ArrayList<Hospede>();
    try {
      list = business.findAll();
      if (list.size() == 0) {
        headers.add("message", Messages.get("0407"));
      }
    } catch (Exception e) {
      status = HttpStatus.BAD_REQUEST;
      headers.add("message", Messages.get("0002"));
    }
    return new ResponseEntity<List<Hospede>>(list, headers, status);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Hospede> findById(@PathVariable Integer id) {
    Hospede retorno = new Hospede();

    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.OK;
    try {
      retorno = business.findById(id);
      if (retorno == null) {
        headers.add("message", Messages.get("0407"));
      }
    } catch (Exception e) {
      status = HttpStatus.BAD_REQUEST;
      headers.add("message", Messages.get("0002"));
    }
    return new ResponseEntity<Hospede>(retorno, headers, status);
  }

  @PostMapping
  public ResponseEntity<Hospede> insert(@RequestBody Hospede hospede) {
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.CREATED;

    try {
      hospede = business.insert(hospede);
      headers.add("message", Messages.get("0401"));
    } catch (HospedeException e) {
      headers.add("message", Messages.get(e.getMessage()));
      status = HttpStatus.BAD_REQUEST;
    } catch (Exception e) {
      headers.add("message", Messages.get("0402"));
      status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<Hospede>(hospede, headers, status);
  }

  @PutMapping
  public ResponseEntity<Hospede> update(@RequestBody Hospede hospede) {

    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.OK;

    try {
      hospede = business.update(hospede);
      headers.add("message", Messages.get("0403"));
    } catch (HospedeException e) {
      headers.add("message", Messages.get(e.getMessage()));
      status = HttpStatus.BAD_REQUEST;
    } catch (Exception e) {
      headers.add("message", Messages.get("0404"));
      status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<Hospede>(hospede, headers, status);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.NO_CONTENT;

    try {
      business.delete(id);
    } catch (Exception e) {
      headers.add("message", Messages.get("0406"));
      status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<Void>(headers, status);
  }
}
