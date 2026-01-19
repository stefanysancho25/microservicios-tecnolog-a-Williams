package microsvwillms.msvc_ordendepedidos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import microsvwillms.msvc_ordendepedidos.model.entity.OrdenPedido;

public interface OrdenPedidoRepository extends JpaRepository<OrdenPedido, Long> {

    boolean existsByCodigo(String codigo);

    Optional<OrdenPedido> findByCodigo(String codigo);
}