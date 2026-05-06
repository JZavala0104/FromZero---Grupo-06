package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.fromzero.DTO.Query1DTO;
import pe.edu.upc.fromzero.DTO.Query2DTO;
import pe.edu.upc.fromzero.ServiceInterface.IDesarrolladoresService;
import pe.edu.upc.fromzero.ServiceInterface.IProyectosService;
import pe.edu.upc.fromzero.DTO.Query3DTO;
import pe.edu.upc.fromzero.DTO.Query4DTO;
import pe.edu.upc.fromzero.ServiceInterface.ITareasService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/query")
public class QueryController {
    @Autowired
    private IProyectosService ProyectosService;
    @Autowired
    private IDesarrolladoresService DesarrolladoresService;

    @GetMapping("/Query1")
    public ResponseEntity<?> Query1(){
        List<Object[]> Query1 = ProyectosService.GetQuery1();
        if(Query1.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query1DTO> respuesta = new ArrayList<>();
        for(Object[] fila: Query1){
            Query1DTO dto = new Query1DTO();
            dto.setProyecto((String) fila[0]);
            dto.setCliente((String) fila[1]);
            dto.setDesarrollador((String) fila[2]);
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/Query2")
    public ResponseEntity<?> Query2(){
        List<Object[]> Query2 = DesarrolladoresService.GetQuery2();
        if(Query2.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query2DTO> respuesta = new ArrayList<>();
        for(Object[] fila: Query2){
            Query2DTO dto = new Query2DTO();
            dto.setNombre((String) fila[0]);
            dto.setHabilidades((String) fila[1]);
            dto.setExperiencia(((Number) fila[2]).intValue());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @Autowired
    private ITareasService TareasService;

    @GetMapping("/Query3")
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
}
