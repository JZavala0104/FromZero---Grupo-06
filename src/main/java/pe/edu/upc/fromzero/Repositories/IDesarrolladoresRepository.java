package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Desarrolladores;
import java.util.List;

@Repository
public interface IDesarrolladoresRepository extends JpaRepository<Desarrolladores, Integer> {
    @Query(value = "SELECT\n" +
            "    u.nombre AS \"Desarrollador\",\n" +
            "    d.experiencia AS \"Años_Exp\",\n" +
            "    d.habilidades AS \"Skills\",\n" +
            "    COUNT(DISTINCT pd.id_proyecto) AS \"Proyectos_Participados\",\n" +
            "    AVG(v.puntuacion) AS \"Reputacion_Promedio\",\n" +
            "    COUNT(v.id_valoracion) AS \"Cantidad_Valoraciones\"\n" +
            "FROM desarrolladores d\n" +
            "         JOIN usuarios u ON d.id_user = u.id_user\n" +
            "         LEFT JOIN proyecto_desarrollador pd ON d.id_desarrollador = pd.id_desarrollador\n" +
            "         LEFT JOIN proyectos p ON pd.id_proyecto = p.id_project\n" +
            "         LEFT JOIN valoraciones v ON p.id_project = v.id_proyecto -- Según tu DER, valoraciones apunta a proyectos\n" +
            "GROUP BY u.id_user, u.nombre, d.experiencia, d.habilidades\n" +
            "ORDER BY \"Reputacion_Promedio\" DESC, d.experiencia DESC", nativeQuery = true)
    List<Object[]> getQuery2();
}

