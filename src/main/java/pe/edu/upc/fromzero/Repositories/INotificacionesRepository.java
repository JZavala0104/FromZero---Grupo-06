package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Notificaciones;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface INotificacionesRepository extends JpaRepository<Notificaciones, Integer> {

    @Query(value = "SELECT " +
            "u.nombre AS Usuario, " +
            "n.mensaje AS Mensaje, " +
            "n.fecha AS Fecha " +
            "FROM notificaciones n " +
            "JOIN usuarios u ON n.id_user = u.id_user " +
            "WHERE n.leido = false " +
            "ORDER BY n.fecha DESC", nativeQuery = true)
    List<Object[]> getQuery5();

}
