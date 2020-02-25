package iryna.sharan.auto.service;

import iryna.sharan.auto.dto.request.FuelRequest;
import iryna.sharan.auto.dto.response.FuelResponse;
import iryna.sharan.auto.entity.Fuel;
import iryna.sharan.auto.repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelService {
    @Autowired
    private FuelRepository fuelRepository;

    public void save(FuelRequest request) {
        final Fuel fuel = fuelRequestToFuel(request, null);
        fuelRepository.save(fuel);
    }

    public List<FuelResponse> findAll() {
        return fuelRepository.findAll()
                .stream()
                .map(f -> new FuelResponse(f))
                .collect(Collectors.toList());

    }

    public Fuel findOne(Long id) {
        return fuelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Fuel with id " + id + " not found."));
    }
    public FuelResponse findOneWithFuel(Long id){
        return new FuelResponse(findOne(id));
    }


    public void update(FuelRequest request, Long id) {
        Fuel fuel = fuelRequestToFuel(request, findOne(id));
        fuelRepository.save(fuel);
    }

    public void delete(Long id) {
        fuelRepository.delete(findOne(id));
    }

    private Fuel fuelRequestToFuel(FuelRequest request, Fuel fuel) {
        if (fuel == null) {
            fuel = new Fuel();
        }
        fuel.setType(request.getType());
        return fuel;

    }
}
