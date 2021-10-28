package br.ufg.inf.fs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufg.inf.fs.entities.Quarto;

public interface QuartoRepository extends JpaRepository<Quarto, Integer> {

  @Query("SELECT q FROM Quarto q WHERE q.categoriaQuarto = :categoriaQuarto")
  public List<Quarto> findByCategoriQuartos(@Param("categoriaQuarto") Integer categoriaQuarto);

  @Query("SELECT q FROM Quarto q WHERE q.nrQuarto = :nrQuarto")
  public List<Quarto> findByNrQuarto(@Param("nrQuarto") Integer nrQuarto);
}
