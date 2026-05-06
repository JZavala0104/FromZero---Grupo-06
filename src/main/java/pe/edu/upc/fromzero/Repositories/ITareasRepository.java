package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Tareas;
import java.util.List;

@Repository
public interface ITareasRepository extends JpaRepository<Tareas, Integer> {
    @Query(value = "SELECT " +
            "p.titulo AS Proyecto, " +
            "t.titulo AS Tarea, " +
            "t.estado AS Estado, " +
            "t.fecha_limite AS FechaLimite " +
            "FROM tareas t " +
            "JOIN proyectos p ON t.id_proyecto = p.id_project " +
            "WHERE t.estado = 'Pendiente'", nativeQuery = true)
    List<Object[]> getQuery4();
}
