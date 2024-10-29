package com.teste.demo.application.usecase;

import com.teste.demo.application.dto.PessoaFisicaDTO;
import com.teste.demo.application.exceptions.PessoaFisicaNotFoundException;
import com.teste.demo.domain.entity.PessoaFisica;
import com.teste.demo.infra.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetPessoaFisica {

    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    public GetPessoaFisica(PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    public List<PessoaFisicaDTO> executeAll() {
        return this.pessoaFisicaRepository.findAll()
                .stream().map(pf -> new PessoaFisicaDTO(pf.getName(), pf.getCpf()))
                .toList();
    }

    public PessoaFisicaDTO executeById(Long pessoaFisicaId) throws PessoaFisicaNotFoundException {
        Optional<PessoaFisica> pessoaFisicaOpt = this.pessoaFisicaRepository.findById(pessoaFisicaId);
        return pessoaFisicaOpt
                .map(pf -> new PessoaFisicaDTO(pf.getName(), pf.getCpf()))
                .orElseThrow(() -> new PessoaFisicaNotFoundException("Pessoa física não encontrada!"));
    }
}
