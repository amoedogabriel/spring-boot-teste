package tads.aranoua.testes.springboottestes.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.aranoua.testes.springboottestes.model.Cidade;
import tads.aranoua.testes.springboottestes.model.Estado;

@DataJpaTest
public class CidadeRepositoryTest {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Test
    public void deveCadastrarCidadeComDadosCompletos(){

        Cidade cidade = new Cidade();
        cidade.setNome("Manacapuru");
        cidade.setIbge("1302504");
        cidade.setEstado(estadoRepository.findOneByIbge("13"));


        cidadeRepository.save(cidade);

        Cidade cidadeNoBanco = cidadeRepository.findOneByIbge(cidade.getIbge());

        Assertions.assertEquals(cidade.getNome(),cidadeNoBanco.getNome());

    }


    @Test
    public void naoDeveCadastrarCidadeComIbgeJaCadastrado(){


//        //Pré-condição - Início
//        Cidade cidade1 = new Cidade();
//        cidade1.setNome("Belem");
//        cidade1.setIbge("1302603");
//
//        cidadeRepository.save(cidade1);
//        //Pré-condição - Fim


        Cidade cidade2 = new Cidade();
        cidade2.setNome("Manaus");
        cidade2.setIbge("1302603");

        // Se já estiver cadastrado o IBGE lança a exceção de DataIntegrity.
        Assertions.assertThrows( DataIntegrityViolationException.class, () -> cidadeRepository.save(cidade2) );

    }


}
