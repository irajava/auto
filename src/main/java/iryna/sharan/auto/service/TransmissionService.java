package iryna.sharan.auto.service;

import iryna.sharan.auto.dto.request.TransmissionRequest;
import iryna.sharan.auto.dto.response.TransmissionResponse;
import iryna.sharan.auto.entity.Transmission;
import iryna.sharan.auto.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransmissionService {
    @Autowired
    private TransmissionRepository transmissionRepository;

    public void save(TransmissionRequest request) {
        final Transmission transmission = transmissionRequestToTransmission(request, null);
        transmissionRepository.save(transmission);
    }

    public List<TransmissionResponse> findAll() {
        return transmissionRepository.findAll()
                .stream()
                .map(TransmissionResponse::new)
                .collect(Collectors.toList());
    }

    public Transmission findOne(Long id) {
        return transmissionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transmission with id " + id + " not found."));
    }
    public TransmissionResponse findOneWithTransmission(Long id){
        return new TransmissionResponse(findOne(id));
    }


    public void update(TransmissionRequest request, Long id) {
        Transmission transmission = transmissionRequestToTransmission(request, findOne(id));
        transmissionRepository.save(transmission);
    }

    public void delete(Long id) {
        transmissionRepository.delete(findOne(id));
    }

    private Transmission transmissionRequestToTransmission(TransmissionRequest request, Transmission transmission) {
        if (transmission == null) {
            transmission = new Transmission();
        }
        transmission.setType(request.getType());
        return transmission;

    }
}
