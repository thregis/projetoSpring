package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="https://viacep.com.br/ws", name = "viacep")
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    Endereco buscaEnderecoPor(@PathVariable("cep") String cep);
}
