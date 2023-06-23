package tads.aranoua.testes.springboottestes.dto;

import tads.aranoua.testes.springboottestes.model.Cidade;
import tads.aranoua.testes.springboottestes.repository.CidadeRepository;
import tads.aranoua.testes.springboottestes.repository.EstadoRepository;

public class CidadeInputDto {

    private String ibge;
    private String nome;
    private String estado;

    public Cidade build(EstadoRepository estadoRepository){

        Cidade cidade = new Cidade();
        cidade.setIbge(this.ibge);
        cidade.setNome(this.nome);
        cidade.setEstado(estadoRepository.findByNomeIgnoreCase(this.estado).get(0));

        return cidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
