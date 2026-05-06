package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Proyectos;
import java.util.List;

@Repository
public interface IProyectosRepository extends JpaRepository<Proyectos, Integer> {
    @Query(value = "SELECT\n" +
            "    p.titulo AS Proyecto,\n" +
            "    e.nombre_empresa AS Cliente,\n" +
            "    u.nombre AS Desarrollador\n" +
            "FROM proyectos p\n" +
            "         JOIN empresas e ON p.id_empresa = e.id_empresa\n" +
            "         JOIN proyecto_desarrollador pd ON p.id_project = pd.id_proyecto\n" +
            "         JOIN desarrolladores d ON pd.id_desarrollador = d.id_desarrollador\n" +
            "         JOIN usuarios u ON d.id_user = u.id_user",nativeQuery = true)
    List<Object[]> getQuery1();

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

