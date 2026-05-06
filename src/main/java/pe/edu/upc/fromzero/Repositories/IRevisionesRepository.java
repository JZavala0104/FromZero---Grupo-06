package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Revisiones;

@Repository
public interface IRevisionesRepository extends JpaRepository<Revisiones, Integer> {
}
