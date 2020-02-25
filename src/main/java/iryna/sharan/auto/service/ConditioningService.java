package iryna.sharan.auto.service;

import iryna.sharan.auto.dto.request.ConditioningRequest;
import iryna.sharan.auto.dto.response.ConditioningResponse;
import iryna.sharan.auto.entity.Conditioning;
import iryna.sharan.auto.repository.CarRepository;
import iryna.sharan.auto.repository.ConditioningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ConditioningService {

    @Autowired
    private ConditioningRepository conditioningRepository;

    public void save (ConditioningRequest request){
        Conditioning conditioning=conditioningRequestToConditioning(request,null);
        conditioningRepository.save(conditioning);
    }

    public List<ConditioningResponse> findAll(){
        return conditioningRepository.findAll()
                .stream()
                .map(c -> new ConditioningResponse(c))
                .collect(Collectors.toList());

    }
    public Conditioning findOne(Long id){
        return conditioningRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Conditioning with id "+ id+" not found."));
    }
    public ConditioningResponse findOneWithConditioning(Long id){
        return new ConditioningResponse(findOne(id));
    }

    public void update(ConditioningRequest request, Long id) {
        Conditioning conditioning = conditioningRequestToConditioning(request, findOne(id));
        conditioningRepository.save(conditioning);
    }

    public void delete(Long id) {
        conditioningRepository.delete(findOne(id));
    }

    private Conditioning conditioningRequestToConditioning(ConditioningRequest request, Conditioning conditioning) {
        if (conditioning == null) {
            conditioning = new Conditioning();
        }
        conditioning.setType(request.getType());
        return conditioning;

    }

}
