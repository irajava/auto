package iryna.sharan.auto.controller;

import iryna.sharan.auto.dto.request.ConditioningRequest;
import iryna.sharan.auto.dto.request.EngineRequest;
import iryna.sharan.auto.dto.response.EngineResponse;
import iryna.sharan.auto.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/engine")
public class EngineController {

    @Autowired
    private EngineService engineService;

    @PostMapping
    public void save (@RequestBody EngineRequest request){
        engineService.save(request);
    }

    @GetMapping
    public List<EngineResponse> findAll(){
        return engineService.findAll();
    }

    @GetMapping("/{id}")
    public EngineResponse findOne(@PathVariable Long id){
        return engineService.findOneWithEngine(id);
    }

    @PutMapping
    public void update (Long id, @RequestBody EngineRequest request){
        engineService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id){
        engineService.delete(id);
    }
}
