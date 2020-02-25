package iryna.sharan.auto.controller;

import iryna.sharan.auto.dto.request.TransmissionRequest;
import iryna.sharan.auto.dto.response.TransmissionResponse;
import iryna.sharan.auto.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transmission")
public class TransmissionController {
    @Autowired
    private TransmissionService transmissionService;

    @PostMapping
    public void save (@RequestBody TransmissionRequest request){
        transmissionService.save(request);
    }

    @GetMapping
    public List<TransmissionResponse> findAll(){
        return transmissionService.findAll();
    }

    @GetMapping("/{id}")
    public TransmissionResponse findOne(@PathVariable Long id){
        return transmissionService.findOneWithTransmission(id);
    }

    @PutMapping
    public void update (Long id, @RequestBody TransmissionRequest request){
        transmissionService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id){
        transmissionService.delete(id);
    }
}
