package iryna.sharan.auto.controller;

import iryna.sharan.auto.dto.request.FuelRequest;
import iryna.sharan.auto.dto.response.FuelResponse;
import iryna.sharan.auto.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/fuel")
public class FuelController {

    @Autowired
    private FuelService fuelService;

    @PostMapping
    public void save (@RequestBody FuelRequest request){
        fuelService.save(request);
    }

    @GetMapping
    public List<FuelResponse> findAll(){
        return fuelService.findAll();
    }

    @GetMapping("/{id}")
    public FuelResponse findOne(@PathVariable Long id){
        return fuelService.findOneWithFuel(id);
    }

    @PutMapping
    public void update (Long id, @RequestBody FuelRequest request){
        fuelService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id){
        fuelService.delete(id);
    }
}
