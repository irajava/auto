package iryna.sharan.auto.controller;

import iryna.sharan.auto.dto.request.ColourRequest;
import iryna.sharan.auto.dto.response.ColourResponse;
import iryna.sharan.auto.service.ColourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/colour")
public class ColourController {

    @Autowired
    private ColourService colourService;

    @PostMapping
    public void save(@RequestBody ColourRequest request){
        colourService.save(request);
    }

    @GetMapping
    public List<ColourResponse> findAll() {
        return colourService.findAll();
    }

    @GetMapping("/{id}")
    public ColourResponse findOne(@PathVariable Long id){
        return colourService.findOneWithColour(id);
    }

    @PutMapping
    public void update (Long id, @RequestBody ColourRequest request){
        colourService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id){
        colourService.delete(id);
    }

}
