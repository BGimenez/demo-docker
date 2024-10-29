package com.teste.demo;

import com.teste.demo.application.dto.PessoaFisicaDTO;
import com.teste.demo.application.exceptions.PessoaFisicaNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPessoaFisicaTest {

    @Mock
    private PessoaFisicaRepository pessoaFisicaRepository;

    @InjectMocks
    private GetPessoaFisica getPessoaFisica;

    @Test
    void shouldReturnPessoaFisica() throws PessoaFisicaNotFoundException {
        // given
        Long pessoaFisicaId = 123L;
        PessoaFisica pessoaFisica = new PessoaFisica(pessoaFisicaId, "João", "123456789");
        when(pessoaFisicaRepository.findById(pessoaFisicaId)).thenReturn(Optional.of(pessoaFisica));

        // when
        PessoaFisicaDTO pessoaFisicaOutput = getPessoaFisica.executeById(pessoaFisicaId);
        // then
        assertEquals("João", pessoaFisicaOutput.name());
        assertEquals("123456789", pessoaFisicaOutput.cpf());
    }

    @Test
    void shouldReturnExceptionWhenPessoaFisicaNotFounded() {
        // given
        Long pessoaFisicaId = 123L;
        when(pessoaFisicaRepository.findById(pessoaFisicaId)).thenReturn(Optional.empty());

        // when
        assertThrows(PessoaFisicaNotFoundException.class, () -> getPessoaFisica.executeById(pessoaFisicaId), "Pessoa Física não encontrada");
    }

}
