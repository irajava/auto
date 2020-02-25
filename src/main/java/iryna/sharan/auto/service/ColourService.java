package iryna.sharan.auto.service;

import iryna.sharan.auto.dto.request.ColourRequest;
import iryna.sharan.auto.dto.response.BodyResponse;
import iryna.sharan.auto.dto.response.ColourResponse;
import iryna.sharan.auto.entity.Colour;
import iryna.sharan.auto.repository.ColourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColourService {
    @Autowired
    private ColourRepository colourRepository;

    public void save (ColourRequest request){
        Colour colour=colourRequestToColour(request, null);
        colourRepository.save(colour);
    }

    public List<ColourResponse> findAll(){
        return colourRepository.findAll()
                .stream()
                .map(ColourResponse::new)
                .collect(Collectors.toList());
    }

    public Colour findOne(Long id){
        return colourRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Colour with id "+ id+" not found."));
    }
    public ColourResponse findOneWithColour(Long id){
        return new ColourResponse(findOne(id));
    }

    public void update(ColourRequest request, Long id) {
        Colour colour = colourRequestToColour(request, findOne(id));
        colourRepository.save(colour);
    }

    public void delete(Long id) {
       colourRepository.delete(findOne(id));
    }

    private Colour colourRequestToColour(ColourRequest request, Colour colour) {
        if (colour == null) {
            colour = new Colour();
        }
        colour.setName(request.getName());
        return colour;

    }
}
