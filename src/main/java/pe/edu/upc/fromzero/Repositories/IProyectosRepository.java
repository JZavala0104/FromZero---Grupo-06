package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Proyectos;
import java.util.List;

@Repository
public interface IProyectosRepository extends JpaRepository<Proyectos, Integer> {

    @Query(value = "SELECT " +
            "p.titulo AS Titulo, " +
            "p.estado AS Estado, " +
            "p.presupuesto AS Presupuesto, " +
            "e.nombre_empresa AS Empresa " +
            "FROM proyectos p " +
            "JOIN empresas e ON p.id_empresa = e.id_empresa " +
            "ORDER BY p.presupuesto DESC", nativeQuery = true)
    List<Object[]> getQuery3();
}

