package tads.aranoua.testes.springboottestes.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.aranoua.testes.springboottestes.model.Cidade;
import tads.aranoua.testes.springboottestes.model.Pessoa;

@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Test
    public void deveSalvarPessoaComDadosCompletos(){

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Pessoa 01");
        pessoa.setCpf("11111111111");
        pessoa.setEmail("pessoa01@email.com");

        //Busco a cidade no banco de dados pelo IBGE.
        Cidade cidade = cidadeRepository.findOneByIbge("1302603");
        pessoa.setCidade(cidade);

        pessoaRepository.save(pessoa);

        Pessoa pessoaNoBD = pessoaRepository.findOneByCpf("11111111111");

        //Verificação
        Assertions.assertEquals(pessoa.getEmail(), pessoaNoBD.getEmail());

    }

    @Test
    public void naoDeveSalvarPessoaComOMesmoCpf(){

//        Precondição 01: Pessoa com o CPF específico deve estar inserida no banco de dados.

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Pessoa 01");
        pessoa1.setCpf("11111111111");
        pessoa1.setEmail("pessoa01@email.com");
        //Busco a cidade no banco de dados pelo IBGE.
        Cidade cidade1 = cidadeRepository.findOneByIbge("1302603");
        pessoa1.setCidade(cidade1);

        pessoaRepository.save(pessoa1);

//      Fim da Precondição

//      Início das ações do cenário:

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Pessoa 02");
        // Tem que ter o mesmo CPF para gerar o erro de integridade no teste.
        pessoa2.setCpf("11111111111");
        pessoa2.setEmail("pessoa02@email.com");
        //Busco a cidade no banco de dados pelo IBGE.
        Cidade cidade2 = cidadeRepository.findOneByIbge("1301852");
        pessoa2.setCidade(cidade2);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> pessoaRepository.save(pessoa2)
        );
//        Fim das ações do Cenário

    }


}
