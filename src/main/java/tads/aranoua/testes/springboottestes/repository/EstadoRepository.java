package tads.aranoua.testes.springboottestes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.aranoua.testes.springboottestes.model.Cidade;
import tads.aranoua.testes.springboottestes.model.Estado;

public interface EstadoRepository extends CrudRepository<Estado,Long> {

    @Query("select e from Estado e")
    List<Estado> list();

    @Query("select e from Estado e where e.ibge = :parIbge")
    Estado findOneByIbge(String parIbge);

}
