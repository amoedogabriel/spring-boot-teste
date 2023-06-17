package tads.aranoua.testes.springboottestes.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tads.aranoua.testes.springboottestes.model.Estado;

@SpringBootTest
@AutoConfigureMockMvc
public class EstadoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void deveListarEstadosCadastrados() throws Exception{

        URI uri = new URI("/api/estados");

        RequestBuilder request = MockMvcRequestBuilders.get(uri);

        // Status
        ResultMatcher statusEsperado = MockMvcResultMatchers.status().is(200);

        //Conteúdo (Content)
        String jsonRetorno = "[{\"id\":1,\"ibge\":\"13\",\"nome\":\"AMAZONAS\"},{\"id\":2,\"ibge\":\"15\",\"nome\":\"PARA\"}]";
        ResultMatcher conteudoEsperado = MockMvcResultMatchers.content().json(jsonRetorno);

        mvc.perform(request).andExpect(statusEsperado).andExpect(conteudoEsperado);


    }



}
