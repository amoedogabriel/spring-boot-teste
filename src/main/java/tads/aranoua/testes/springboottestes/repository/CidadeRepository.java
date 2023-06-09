package tads.aranoua.testes.springboottestes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.aranoua.testes.springboottestes.model.Cidade;

public interface CidadeRepository extends CrudRepository<Cidade,Long> {

    @Query("select c from Cidade c")
    List<Cidade> list();

    @Query("select c from Cidade c where c.ibge = :parIbge")
    Cidade findOneByIbge(String parIbge);


}
