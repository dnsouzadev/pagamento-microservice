package br.com.dnsouzadev.pagamentos.service;

import br.com.dnsouzadev.pagamentos.dto.PagamentoDto;
import br.com.dnsouzadev.pagamentos.model.Pagamento;
import br.com.dnsouzadev.pagamentos.model.Status;
import br.com.dnsouzadev.pagamentos.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(pagamento -> modelMapper.map(pagamento, PagamentoDto.class));
    }

    public PagamentoDto findById(Long id) {
        return modelMapper.map(repository.findById(id).orElseThrow(), PagamentoDto.class);
    }

    public PagamentoDto save(PagamentoDto pagamentoDto) {
        Pagamento pagamento = modelMapper.map(pagamentoDto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto update(Long id, PagamentoDto pagamentoDto) {
        Pagamento pagamento = repository.findById(id).orElseThrow();
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
