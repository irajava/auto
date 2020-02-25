package iryna.sharan.auto.service;

import iryna.sharan.auto.dto.request.ModelRequest;
import iryna.sharan.auto.dto.response.ModelResponse;
import iryna.sharan.auto.dto.response.ModelResponseWithMakes;
import iryna.sharan.auto.entity.Model;

import iryna.sharan.auto.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private MakeService makeService;

    public void save (ModelRequest request){
       final Model model=modelRequestToModel(request, null);
       modelRepository.save(model);
    }

    public List<ModelResponse> findAll(){
        return modelRepository.findAll()
                .stream()
                .map(ModelResponse::new)
                .collect(Collectors.toList());
    }

    public Model findOne(Long id){
        return modelRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Model with id "+ id+" not found."));
    }


    public void update(ModelRequest request, Long id) {
        Model model = modelRequestToModel(request, findOne(id));
        modelRepository.save(model);
    }

    @Transactional
    public List<ModelResponseWithMakes> findAllByMake(Long makeId) {
        return modelRepository.findAllByMakeId(makeId).map(ModelResponseWithMakes::new).collect(Collectors.toList());
    }

    public void delete(Long id) {
        modelRepository.delete(findOne(id));
    }

    public ModelResponseWithMakes findOneWithMakes(Long id){
        return new ModelResponseWithMakes(findOne(id));
    }

    private Model modelRequestToModel(ModelRequest request, Model model) {
        if (model == null) {
            model = new Model();
        }
        model.setName(request.getName());
        model.setMake(makeService.findOne(request.getMakeId()));
        return model;

    }
}
