package com.ogambientes.checklist.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/*
 * @RestControllerAdvice: É o crachá do SAC. Diz ao Spring: "Se der qualquer
 * erro em qualquer Controller da fábrica, mande para mim antes de devolver pro cliente."
 */
@RestControllerAdvice
public class TratadorDeErros {

    /*
     * @ExceptionHandler: Avisa qual erro específico este método sabe resolver.
     * O erro MethodArgumentNotValidException é exatamente o que o @Valid dispara.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {

        // Pega todos os campos que falharam na validação
        List<FieldError> erros = ex.getFieldErrors();

        // Converte a lista de erros complexos do Java para a nossa listinha limpa
        List<DadosErroValidacao> errosLimpos = erros.stream()
                .map(DadosErroValidacao::new)
                .toList();

        return ResponseEntity.badRequest().body(errosLimpos);
    }

    /*
     * Record: É uma estrutura rápida do Java para criar pequenos objetos de transferência.
     * É a nossa "Ficha de Atendimento" do SAC, contendo só o nome do campo e a mensagem.
     */
    public record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}