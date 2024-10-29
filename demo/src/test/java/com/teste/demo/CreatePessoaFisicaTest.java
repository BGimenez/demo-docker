package com.teste.demo;

import com.teste.demo.application.dto.PessoaFisicaDTO;
import com.teste.demo.application.exceptions.PessoaFisicaNotFoundException;
import com.teste.demo.application.usecase.CreatePessoaFisica;
import com.teste.demo.application.usecase.GetPessoaFisica;
import com.teste.demo.domain.entity.PessoaFisica;
import com.teste.demo.infra.repository.PessoaFisicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePessoaFisicaTest {

    @Mock
    private PessoaFisicaRepository pessoaFisicaRepository;
    @InjectMocks
    private CreatePessoaFisica createPessoaFisica;

    private GetPessoaFisica getPessoaFisica;

    @BeforeEach
    void setUp() {
        getPessoaFisica = new GetPessoaFisica(pessoaFisicaRepository);
    }

    @Test
    void shouldCreatePessoaFisica() throws PessoaFisicaNotFoundException {
        // Given
        PessoaFisica pessoaFisica = new PessoaFisica(123L, "João", "12345678900");
        when(pessoaFisicaRepository.save(any(PessoaFisica.class))).thenReturn(pessoaFisica);
        when(pessoaFisicaRepository.findById(anyLong())).thenReturn(Optional.of(pessoaFisica));
        PessoaFisicaDTO pessoaFisicaInput = new PessoaFisicaDTO("João", "12345678900");
        // When
        Long pessoaFisicaId = createPessoaFisica.execute(pessoaFisicaInput);
        // Then
        PessoaFisicaDTO pessoaFisicaOutput = getPessoaFisica.executeById(pessoaFisicaId);
        assertEquals("João", pessoaFisicaOutput.name());
        assertEquals("12345678900", pessoaFisicaOutput.cpf());
    }
}
