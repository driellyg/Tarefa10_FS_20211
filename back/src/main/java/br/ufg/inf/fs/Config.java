package br.ufg.inf.fs;

import br.ufg.inf.fs.entities.Hospedagem;
import br.ufg.inf.fs.entities.Hospede;
import br.ufg.inf.fs.repositories.HospedagemRepository;
import br.ufg.inf.fs.repositories.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.ufg.inf.fs.entities.Hotel;
import br.ufg.inf.fs.entities.Quarto;
import br.ufg.inf.fs.enums.CategoriaQuarto;
import br.ufg.inf.fs.repositories.HotelRepository;
import br.ufg.inf.fs.repositories.QuartoRepository;

import java.util.Date;

@Configuration
@Profile("dev")
public class Config  implements CommandLineRunner{

	@Autowired
	private HotelRepository hoteRepository;
	
	@Autowired
	private QuartoRepository quartoRepository;

	@Autowired
	private HospedeRepository hospedeRepository;

	@Autowired
	private HospedagemRepository hospedagemRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	
		/*
		 * INSERIR NO MEU BANCO DE DADOS INFORMAÇÕES INICIAIS...
		 */

		Hotel h1 = new Hotel(null, "Copacabana Palace", "Rio de Janeiro", 5);
		Hotel h2 = new Hotel(null, "Oitis Hotel", "Goiania", 3);
		Hotel h3 = new Hotel(null, "Beiramar Hotel", "Rio de Janeiro", 4);
		Hotel h4 = new Hotel(null, "Bourbon Hotel", "Sao Paulo", 5);

		h1 = hoteRepository.save(h1);
		h2 = hoteRepository.save(h2);
		h3 = hoteRepository.save(h3);
		h4 = hoteRepository.save(h4);

		Quarto q1 = quartoRepository.save(new Quarto(null, h1, CategoriaQuarto.APARTAMENTO, 3, 1010, 350.0));
		Quarto q2 = quartoRepository.save(new Quarto(null, h2, CategoriaQuarto.SIMPLES, 1, 7, 150.0));
		Quarto q3 = quartoRepository.save(new Quarto(null, h3, CategoriaQuarto.PADRAO, 2, 306, 200.0));
		Quarto q4 = quartoRepository.save(new Quarto(null, h4, CategoriaQuarto.LUXO, 4, 1313, 650.0));

		Hospede hospede1 = hospedeRepository.save(new Hospede(null, "Claudia", new Date(), "70176543201"));
		Hospede hospede2 = hospedeRepository.save(new Hospede(null, "Isabel", new Date(), "70278674399"));
		Hospede hospede3 = hospedeRepository.save(new Hospede(null, "Thomas", new Date(), "70321432111"));
		Hospede hospede4 = hospedeRepository.save(new Hospede(null, "Marcelo", new Date(), "70456103266"));

		Hospedagem hospedagem1 = hospedagemRepository.save(new Hospedagem(null, q1.getIdQuarto(), hospede1.getIdHospede(), new Date(), new Date()));
		Hospedagem hospedagem2 = hospedagemRepository.save(new Hospedagem(null, q2.getIdQuarto(), hospede2.getIdHospede(), new Date(), new Date()));
		Hospedagem hospedagem3 = hospedagemRepository.save(new Hospedagem(null, q3.getIdQuarto(), hospede3.getIdHospede(), new Date(), new Date()));
		Hospedagem hospedagem4 = hospedagemRepository.save(new Hospedagem(null, q4.getIdQuarto(), hospede4.getIdHospede(), new Date(), new Date()));


		for (Hospedagem h : hospedagemRepository.findAll()) {
			System.out.println(h);
		}

	}

}
