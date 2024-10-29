package com.teste.demo.infra.controller;

import com.teste.demo.application.dto.PessoaFisicaDTO;
import com.teste.demo.application.usecase.CreatePessoaFisica;
import com.teste.demo.application.usecase.GetPessoaFisica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pessoa-fisica")
public class PessoaFisicaController {

    private final GetPessoaFisica getPessoaFisica;
    private final CreatePessoaFisica createPessoaFisica;

    @Autowired
    public PessoaFisicaController(GetPessoaFisica getPessoaFisica, CreatePessoaFisica createPessoaFisica) {
        this.getPessoaFisica = getPessoaFisica;
        this.createPessoaFisica = createPessoaFisica;
    }

    @GetMapping
    public ResponseEntity<List<PessoaFisicaDTO>> getAll() {
        List<PessoaFisicaDTO> pessoas = this.getPessoaFisica.executeAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{pessoaFisicaId}")
    public ResponseEntity<PessoaFisicaDTO> getAll(@PathVariable Long pessoaFisicaId) {
        try {
            PessoaFisicaDTO pessoas = this.getPessoaFisica.executeById(pessoaFisicaId);
            return ResponseEntity.ok(pessoas);
        } catch (Exception e) {
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage())).build();
        }
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody PessoaFisicaDTO pessoaFisicaInput) {
        Long pessoaFisicaId = this.createPessoaFisica.execute(pessoaFisicaInput);
        return ResponseEntity.ok(pessoaFisicaId);
    }

}
