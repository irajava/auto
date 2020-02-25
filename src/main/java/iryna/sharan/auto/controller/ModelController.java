package iryna.sharan.auto.controller;

import iryna.sharan.auto.dto.request.ModelRequest;
import iryna.sharan.auto.dto.response.ModelResponse;
import iryna.sharan.auto.dto.response.ModelResponseWithMakes;
import iryna.sharan.auto.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @PostMapping
    public void save (@RequestBody ModelRequest request){
        modelService.save(request);
    }

    @GetMapping
    public List<ModelResponse> findAll(){
        return modelService.findAll();
    }

    @GetMapping("/{id}")
    public ModelResponseWithMakes findOne(@PathVariable Long id){
        return modelService.findOneWithMakes(id);
    }

    @GetMapping("/byMake")
    public List<ModelResponseWithMakes>  findAllByMake(Long makeId) {
        return modelService.findAllByMake(makeId);
    }


    @PutMapping
    public void update (Long id, @RequestBody ModelRequest request){
        modelService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id){
        modelService.delete(id);
    }
}
