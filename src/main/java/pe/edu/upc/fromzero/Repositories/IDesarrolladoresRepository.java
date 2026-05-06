package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Desarrolladores;
import java.util.List;

@Repository
public interface IDesarrolladoresRepository extends JpaRepository<Desarrolladores, Integer> {
    @Query(value = "SELECT\n" +
            "    u.nombre,\n" +
            "    d.habilidades,\n" +
            "    d.experiencia\n" +
            "FROM desarrolladores d\n" +
            "         JOIN usuarios u ON d.id_user = u.id_user\n" +
            "ORDER BY d.experiencia DESC", nativeQuery = true)
    List<Object[]> getQuery2();
}

