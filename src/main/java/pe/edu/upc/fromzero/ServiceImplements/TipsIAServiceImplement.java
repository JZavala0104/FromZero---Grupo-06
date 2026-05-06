package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.TipsIA;
import pe.edu.upc.fromzero.Repositories.ITipsIARepository;
import pe.edu.upc.fromzero.ServiceInterface.ITipsIAService;

import java.util.List;
import java.util.Optional;

@Service
public class TipsIAServiceImplement implements ITipsIAService {
    @Autowired
    private ITipsIARepository tipsIARepository;

    @Override
    public List<TipsIA> GetTipsIA() {
        return tipsIARepository.findAll();
    }

    @Override
    public TipsIA InsertTipsIA(TipsIA tipsIA) {
        return tipsIARepository.save(tipsIA);
    }

    @Override
    public void UpdateTipsIA(TipsIA tipsIA) {
        tipsIARepository.save(tipsIA);
    }

    @Override
    public void DeleteTipsIA(int IdTipsIA) {
        tipsIARepository.deleteById(IdTipsIA);
    }

    @Override
    public Optional<TipsIA> GetTipsIAById(int IdTipsIA) {
        return tipsIARepository.findById(IdTipsIA);
    }
}
