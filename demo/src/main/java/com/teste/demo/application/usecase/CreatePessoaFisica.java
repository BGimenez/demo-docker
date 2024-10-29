package com.teste.demo.application.usecase;

import com.teste.demo.application.dto.PessoaFisicaDTO;
import com.teste.demo.domain.entity.PessoaFisica;
import com.teste.demo.infra.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePessoaFisica {

    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    public CreatePessoaFisica(PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    public Long execute(PessoaFisicaDTO pessoaFisicaDTO) {
        PessoaFisica pessoaFisica = PessoaFisica.create(pessoaFisicaDTO.name(), pessoaFisicaDTO.cpf());
        pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
        return pessoaFisica.getId();
    }
}
