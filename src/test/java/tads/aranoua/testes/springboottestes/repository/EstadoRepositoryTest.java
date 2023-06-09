package tads.aranoua.testes.springboottestes.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.aranoua.testes.springboottestes.model.Cidade;
import tads.aranoua.testes.springboottestes.model.Estado;

@DataJpaTest
public class EstadoRepositoryTest {

    @Autowired
    private EstadoRepository estadoRepository;

    @Test
    public void deveCadastrarEstadoComDadosCompletos(){

        Estado estado = new Estado();
        estado.setNome("Amazonas");
        estado.setIbge("25");

        estadoRepository.save(estado);

        Estado estadoNoBanco = estadoRepository.findOneByIbge(estado.getIbge());

        Assertions.assertEquals(estado.getNome(),estadoNoBanco.getNome());

    }


    @Test
    public void naoDeveCadastrarEstadoComIbgeJaCadastrado(){


        //Pré-condição - Início
        Estado estado1 = new Estado();
        estado1.setNome("Belem");
        estado1.setIbge("25");

        estadoRepository.save(estado1);
        //Pré-condição - Fim


        Estado estado2 = new Estado();
        estado2.setNome("Amazonas");
        estado2.setIbge("25");


        // Se já estiver cadastrado o IBGE lança a exceção de DataIntegrity.
        Assertions.assertThrows( DataIntegrityViolationException.class, () -> estadoRepository.save(estado2) );

    }


}
