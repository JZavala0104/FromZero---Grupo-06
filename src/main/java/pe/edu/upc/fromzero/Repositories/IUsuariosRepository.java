package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Usuarios;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios, Integer> {
    public Usuarios findOneByUsername(String username);
    //BUSCAR POR NOMBRE
    @Query("select count(u.Nombre) from Usuarios u where u.Nombre =:username")
    public int buscarUsername(@Param("username") String nombre);
}

