package iryna.sharan.auto.controller;

import iryna.sharan.auto.dto.request.ConditioningRequest;
import iryna.sharan.auto.dto.response.ConditioningResponse;
import iryna.sharan.auto.service.ConditioningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/conditioning")
public class ConditioningController {

    @Autowired
    private ConditioningService conditioningService;

    @PostMapping
    public void save (@RequestBody ConditioningRequest request){
        conditioningService.save(request);
    }

    @GetMapping
    public List<ConditioningResponse> findAll(){
        return conditioningService.findAll();
    }

    @GetMapping("/{id}")
    public ConditioningResponse findOne(@PathVariable Long id){
        return conditioningService.findOneWithConditioning(id);
    }

    @PutMapping
    public void update (Long id, @RequestBody ConditioningRequest request){
        conditioningService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id){
        conditioningService.delete(id);
    }
}
