package iryna.sharan.auto.controller;

import iryna.sharan.auto.dto.request.CarSearchRequest;
import iryna.sharan.auto.dto.request.MakeRequest;
import iryna.sharan.auto.dto.request.ModelRequest;
import iryna.sharan.auto.dto.response.CarResponse;
import iryna.sharan.auto.dto.response.MakeResponse;
import iryna.sharan.auto.service.MakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/make")
public class MakeController {

    @Autowired
    private MakeService makeService;

    @PostMapping
    public void save (@RequestBody MakeRequest request){
        makeService.save(request);

    }
    @GetMapping
    public List<MakeResponse> findAll(){
        return makeService.findAll();
    }

    @GetMapping("/{id}")
    public MakeResponse findOne(@PathVariable Long id){
        return makeService.findOneWithMake(id);
    }

    @PutMapping
    public void update (Long id, @RequestBody MakeRequest request){
        makeService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id){
        makeService.delete(id);
    }


}
