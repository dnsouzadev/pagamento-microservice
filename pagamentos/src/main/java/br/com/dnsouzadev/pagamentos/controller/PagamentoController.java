package br.com.dnsouzadev.pagamentos.controller;

import br.com.dnsouzadev.pagamentos.dto.PagamentoDto;
import br.com.dnsouzadev.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<Page<PagamentoDto>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(pagamentoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(pagamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> save(@RequestBody @Valid PagamentoDto pagamentoDto, UriComponentsBuilder uriBuilder) {
        PagamentoDto pagamento = pagamentoService.save(pagamentoDto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();
        return ResponseEntity.created(endereco).body(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> update(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDto pagamentoDto) {
        return ResponseEntity.ok(pagamentoService.update(id, pagamentoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull Long id) {
        pagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
