package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.fromzero.DTO.*;
import pe.edu.upc.fromzero.ServiceInterface.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/query")
public class QueryController {
    @Autowired
    private IEmpresasService EmpresasService;
    @Autowired
    private IProyectosService ProyectosService;
    @Autowired
    private IDesarrolladoresService DesarrolladoresService;
    @Autowired
    private INotificacionesService NotificacionesService;
    @Autowired
    private IRevisionesService RevisionesService;

    @GetMapping("/Query1")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Gerente', 'Analista', 'Consultor', 'Empresa')")
    public ResponseEntity<?> Query1(){
        List<Object[]> Query1 = EmpresasService.GetQuery1();
        if(Query1.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query1DTO> respuesta = new ArrayList<>();
        for(Object[] fila: Query1){
            Query1DTO dto = new Query1DTO();
            dto.setEmpresa((String) fila[0]);
            dto.setTotal_Proyectos(((Number) fila[1]).intValue());
            dto.setInversion_Total(((Number) fila[2]).doubleValue());
            dto.setTotal_Tareas_Asignadas(((Number) fila[3]).intValue());
            dto.setPresupuesto_Promedio(((Number) fila[4]).doubleValue());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query2")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Empresa', 'Gerente', 'Analista')")
    public ResponseEntity<?> Query2() {
        List<Object[]> Query2 = DesarrolladoresService.GetQuery2();

        if (Query2.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }

        List<Query2DTO> respuesta = new ArrayList<>();

        for (Object[] fila : Query2) {
            Query2DTO dto = new Query2DTO();
            dto.setDesarrollador((String) fila[0]);
            dto.setAños_Exp(fila[1] != null ? ((Number) fila[1]).intValue() : 0);
            dto.setSkills((String) fila[2]);
            dto.setProyectos_Participados(fila[3] != null ? ((Number) fila[3]).intValue() : 0);
            dto.setReputacion_Promedio(fila[4] != null ? ((Number) fila[4]).doubleValue() : 0.0);
            dto.setCantidad_Valoraciones(fila[5] != null ? ((Number) fila[5]).intValue() : 0);
            respuesta.add(dto);
        }

        return ResponseEntity.ok(respuesta);
    }

    @Autowired
    private ITareasService TareasService;

    @GetMapping("/Query3")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Gerente', 'Analista', 'Consultor')")
    public ResponseEntity<?> Query3(){
        List<Object[]> Query3 = ProyectosService.GetQuery3();
        if(Query3.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query3DTO> respuesta = new ArrayList<>();
        for(Object[] fila: Query3){
            Query3DTO dto = new Query3DTO();
            dto.setTitulo((String) fila[0]);
            dto.setEstado((String) fila[1]);
            dto.setPresupuesto(((Number) fila[2]).doubleValue());
            dto.setEmpresa((String) fila[3]);
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query4")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Moderador', 'Soporte', 'Gerente')")
    public ResponseEntity<?> Query4(){
        List<Object[]> Query4 = TareasService.GetQuery4();
        if(Query4.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query4DTO> respuesta = new ArrayList<>();
        for(Object[] fila: Query4){
            Query4DTO dto = new Query4DTO();
            dto.setProyecto((String) fila[0]);
            dto.setTarea((String) fila[1]);
            dto.setEstado((String) fila[2]);
            dto.setFechaLimite((String) fila[3]);
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query5")
    public ResponseEntity<?> Query5() {
        List<Object[]> Query5 = NotificacionesService.GetQuery5();
        if (Query5.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query5DTO> respuesta = new ArrayList<>();
        for (Object[] fila : Query5) {
            Query5DTO dto = new Query5DTO();
            dto.setUsuario((String) fila[0]);
            dto.setMensaje((String) fila[1]);
            dto.setFecha(fila[2].toString());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query6")
    public ResponseEntity<?> Query6() {
        List<Object[]> Query6 = RevisionesService.GetQuery6();
        if (Query6.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query6DTO> respuesta = new ArrayList<>();
        for (Object[] fila : Query6) {
            Query6DTO dto = new Query6DTO();
            dto.setTarea((String) fila[0]);
            dto.setProyecto((String) fila[1]);
            dto.setEstado((String) fila[2]);
            dto.setComentario((String) fila[3]);
            dto.setFecha(fila[4].toString());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

}
