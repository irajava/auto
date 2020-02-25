package iryna.sharan.auto.controller;

import iryna.sharan.auto.dto.request.BodyRequest;
import iryna.sharan.auto.dto.response.BodyResponse;
import iryna.sharan.auto.entity.Body;
import iryna.sharan.auto.service.BodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/body")
public class BodyController {
    @Autowired
    private BodyService bodyService;

    @PostMapping
    public void save (@RequestBody BodyRequest request){
        bodyService.save(request);

    }
    @GetMapping
    public List<BodyResponse> findAll(){
        return bodyService.findAll();
    }

    @GetMapping("/{id}")
    public BodyResponse findOne(@PathVariable Long id){
        return bodyService.findOneWithBody(id);
    }

    @PutMapping
    public void update (Long id, @RequestBody BodyRequest request){
        bodyService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id){
        bodyService.delete(id);
    }
}
