package br.com.dnsouzadev.pagamentos.repository;

import br.com.dnsouzadev.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
