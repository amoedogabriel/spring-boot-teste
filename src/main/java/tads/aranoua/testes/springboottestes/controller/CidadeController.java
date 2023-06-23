package tads.aranoua.testes.springboottestes.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import tads.aranoua.testes.springboottestes.dto.CidadeInputDto;
import tads.aranoua.testes.springboottestes.model.Cidade;
import tads.aranoua.testes.springboottestes.model.Estado;
import tads.aranoua.testes.springboottestes.repository.CidadeRepository;
import tads.aranoua.testes.springboottestes.repository.EstadoRepository;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {


    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Cidade> list(){
        List<Cidade> cidades = cidadeRepository.list();
        return cidades;
    }


    @ResponseBody
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade find(@PathVariable("id") Long id){

        return cidadeRepository.findById(id).get();

    }

    @ResponseBody
    @GetMapping(value = "/ibge/{ibge}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade findByIBGE(@PathVariable("ibge") String ibge){

        return cidadeRepository.findOneByIbge(ibge);

    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cidade> create(@RequestBody CidadeInputDto cidadeInputDto,
                                         UriComponentsBuilder uriComponentsBuilder){

        Cidade cidade = cidadeInputDto.build(estadoRepository);

       Cidade cidadeRetorno = cidadeRepository.save(cidade);

//       status de retorno: 201 (ok)
//        path: uri da entidade
//        body: conteúdo do objeto em json

        URI uri = uriComponentsBuilder.path("/api/cidades/{id}")
                                       .buildAndExpand(cidadeRetorno.getId()).toUri();

       return ResponseEntity.created(uri).body(cidadeRetorno);

    }






}
