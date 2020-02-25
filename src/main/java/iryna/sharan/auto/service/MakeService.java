package iryna.sharan.auto.service;

import iryna.sharan.auto.dto.request.MakeRequest;
import iryna.sharan.auto.dto.response.MakeResponse;
import iryna.sharan.auto.entity.Make;
import iryna.sharan.auto.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MakeService {

    @Autowired
    private MakeRepository makeRepository;
    public void save (MakeRequest request){
        final Make make=makeRequestToMake(request, null);
        makeRepository.save(make);
    }

    public List<MakeResponse> findAll(){
        return makeRepository.findAll()
                .stream()
                .map(e -> new MakeResponse(e))
                .collect(Collectors.toList());
    }

    public Make findOne(Long id){
        return makeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Make with id "+ id+" not found."));
    }
    public MakeResponse findOneWithMake(Long id){
        return new MakeResponse(findOne(id));
    }


    public void update(MakeRequest request, Long id) {
        Make make = makeRequestToMake(request, findOne(id));
        makeRepository.save(make);
    }

    public void delete(Long id) {
        makeRepository.delete(findOne(id));
    }

    private Make makeRequestToMake(MakeRequest request, Make make) {
        if (make == null) {
            make = new Make();
        }
        make.setName(request.getName());
        return make;

    }
}
