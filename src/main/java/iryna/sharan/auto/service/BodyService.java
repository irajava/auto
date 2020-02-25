package iryna.sharan.auto.service;

import iryna.sharan.auto.dto.request.BodyRequest;
import iryna.sharan.auto.dto.response.BodyResponse;
import iryna.sharan.auto.entity.Body;
import iryna.sharan.auto.repository.BodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BodyService {

    @Autowired
    private BodyRepository bodyRepository;
    public void save (BodyRequest request){
      final Body body=bodyRequestToBody(request, null);
        bodyRepository.save(body);
    }

    public List<BodyResponse> findAll(){
        return bodyRepository.findAll()
                .stream()
                .map(e -> new BodyResponse(e))
                .collect(Collectors.toList());
    }

    public Body findOne(Long id){
        return bodyRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Body with id "+ id+" not found."));
    }

    public void update(BodyRequest request, Long id) {
        Body body = bodyRequestToBody(request, findOne(id));
        bodyRepository.save(body);
    }

    public void delete(Long id) {
        bodyRepository.delete(findOne(id));
    }

    public BodyResponse findOneWithBody(Long id){
        return new BodyResponse(findOne(id));
    }

    private Body bodyRequestToBody(BodyRequest request, Body body) {
        if (body == null) {
            body = new Body();
        }
        body.setType(request.getType());
        return body;

    }
}
