package tads.aranoua.testes.springboottestes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tads.aranoua.testes.springboottestes.model.Cidade;
import tads.aranoua.testes.springboottestes.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa,Long> {

    @Query("select p from Pessoa p")
    List<Pessoa> list();

    @Query("select p from Pessoa p where p.cpf = :parCpf")
    Pessoa findOneByCpf(String parCpf);

    @Query("select p from Pessoa p where p.email = :parEmail")
    Pessoa findOneByEmail(String parEmail);

}
