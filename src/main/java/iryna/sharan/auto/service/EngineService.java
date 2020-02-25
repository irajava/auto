package iryna.sharan.auto.service;

import iryna.sharan.auto.dto.request.EngineRequest;
import iryna.sharan.auto.dto.response.EngineResponse;
import iryna.sharan.auto.entity.Engine;
import iryna.sharan.auto.repository.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EngineService {

    @Autowired
    private EngineRepository engineRepository;

    public void save (EngineRequest request){
       final Engine engine=engineRequestToEngine(request, null);
        engineRepository.save(engine);
    }

    public List<EngineResponse> findAll(){
        return engineRepository.findAll()
                .stream()
                .map(e -> new EngineResponse(e))
                .collect(Collectors.toList());

    }
    public Engine findOne(Long id){
        return engineRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Engine with id "+ id+" not found."));
    }
    public EngineResponse findOneWithEngine(Long id){
        return new EngineResponse(findOne(id));
    }

    public void update(EngineRequest request, Long id) {
        Engine engine = engineRequestToEngine(request, findOne(id));
        engineRepository.save(engine);
    }

    public void delete(Long id) {
        engineRepository.delete(findOne(id));
    }

    private Engine engineRequestToEngine(EngineRequest request, Engine engine) {
        if (engine == null) {
            engine = new Engine();
        }
        engine.setVolume(request.getVolume());
        return engine;

    }
}
