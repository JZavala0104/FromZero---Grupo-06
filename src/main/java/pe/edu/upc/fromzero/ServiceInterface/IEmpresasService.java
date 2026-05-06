package pe.edu.upc.fromzero.ServiceInterface;

import pe.edu.upc.fromzero.Entities.Empresas;

import java.util.List;
import java.util.Optional;

public interface IEmpresasService {
    public List<Empresas> GetEmpresa();
    public Empresas InsertEmpresa(Empresas empresa);
    public void UpdateEmpresa(Empresas empresa);
    public void DeleteEmpresa(int IdEmpresa);
    public Optional<Empresas> GetEmpresaById(int IdEmpresa);
    List<Object[]> GetQuery1();
}
