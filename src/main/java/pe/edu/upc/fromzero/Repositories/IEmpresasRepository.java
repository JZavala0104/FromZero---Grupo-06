package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Empresas;

import java.util.List;

@Repository
public interface IEmpresasRepository extends JpaRepository<Empresas, Integer> {
    @Query(value = "SELECT\n" +
            "    e.nombre_empresa AS \"Empresa\",\n" +
            "    COUNT(DISTINCT p.id_project) AS \"Total_Proyectos\",\n" +
            "    SUM(p.presupuesto) AS \"Inversion_Total\",\n" +
            "    COUNT(t.id_tarea) AS \"Total_Tareas_Asignadas\",\n" +
            "    AVG(p.presupuesto) AS \"Presupuesto_Promedio\"\n" +
            "FROM empresas e\n" +
            "         JOIN proyectos p ON e.id_empresa = p.id_empresa\n" +
            "         LEFT JOIN tareas t ON p.id_project = t.id_proyecto\n" +
            "GROUP BY e.id_empresa, e.nombre_empresa\n" +
            "ORDER BY \"Inversion_Total\" DESC",nativeQuery = true)
    List<Object[]> getQuery1();
}
