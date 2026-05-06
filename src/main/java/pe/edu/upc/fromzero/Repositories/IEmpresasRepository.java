package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Empresas;

@Repository
public interface IEmpresasRepository extends JpaRepository<Empresas, Integer> {
}
