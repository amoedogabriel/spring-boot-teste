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
import tads.aranoua.testes.springboottestes.model.Estado;
import tads.aranoua.testes.springboottestes.model.Pessoa;
import tads.aranoua.testes.springboottestes.repository.EstadoRepository;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {


    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> list(){
        List<Estado> estados = estadoRepository.list();
        return estados;
    }


    @ResponseBody
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado find(@PathVariable("id") Long id){

        return estadoRepository.findById(id).get();

    }

    @ResponseBody
    @GetMapping(value = "/ibge/{ibge}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado findByIBGE(@PathVariable("ibge") String ibge){

        return estadoRepository.findOneByIbge(ibge);

    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estado> create(@RequestBody Estado estado, UriComponentsBuilder uriComponentsBuilder){

       Estado estadoRetorno = estadoRepository.save(estado);

//       status de retorno: 201 (ok)
//        path: uri da entidade
//        body: conteúdo do objeto em json

        URI uri = uriComponentsBuilder.path("/api/estados/{id}")
                                       .buildAndExpand(estadoRetorno.getId()).toUri();

       return ResponseEntity.created(uri).body(estadoRetorno);

    }






}
