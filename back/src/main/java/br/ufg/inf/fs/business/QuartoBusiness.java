package br.ufg.inf.fs.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.entities.Quarto;
import br.ufg.inf.fs.repositories.QuartoRepository;
import br.ufg.inf.fs.exceptions.QuartoException;

@Service
public class QuartoBusiness {

	@Autowired
	private QuartoRepository repository;

	public List<Quarto> findByCategoriaQuartos(Integer cat) {
		return repository.findByCategoriQuartos(cat);
	}

	public List<Quarto> findByNrQuarto(Integer nrQuarto) {
		return repository.findByNrQuarto(nrQuarto);
	}

	public List<Quarto> findAll() {
		return repository.findAll();
	}

	public Quarto findById(Integer id) {
		Optional<Quarto> retorno = repository.findById(id);
		return retorno.get();
	}

	public Quarto insert(Quarto quarto) throws QuartoException {
		this.validaQuarto(quarto);
		return repository.save(quarto);
	}

	public Quarto update(Quarto quarto) throws QuartoException {
		this.validaQuarto(quarto);
		return repository.save(quarto);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	private void validaQuarto(Quarto quarto) throws QuartoException {
		if (quarto.getHotel() == null) {
			throw new QuartoException("0208");
		}
		if (quarto.getQtdLeito() == null) {
			throw new QuartoException("0209");
		}
		if (quarto.getNrQuarto() == null) {
			throw new QuartoException("0210");
		}
		if (quarto.getPrDiaria() == null) {
			throw new QuartoException("0211");
		}
	}

}
