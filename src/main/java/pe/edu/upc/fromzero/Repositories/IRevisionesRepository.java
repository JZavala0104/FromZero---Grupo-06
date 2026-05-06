package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Revisiones;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface IRevisionesRepository extends JpaRepository<Revisiones, Integer> {
    @Query(value = "SELECT " +
            "t.titulo AS Tarea, " +
            "p.titulo AS Proyecto, " +
            "r.estado AS Estado, " +
            "r.comentar AS Comentario, " +
            "r.fecha AS Fecha " +
            "FROM revisiones r " +
            "JOIN tareas t ON r.id_tarea = t.id_tarea " +
            "JOIN proyectos p ON t.id_proyecto = p.id_project " +
            "ORDER BY r.fecha DESC", nativeQuery = true)
    List<Object[]> getQuery6();
}
