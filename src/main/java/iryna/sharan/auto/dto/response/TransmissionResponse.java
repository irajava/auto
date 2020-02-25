package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import iryna.sharan.auto.entity.Transmission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransmissionResponse {
    private Long id;
    private String type;

    @JsonProperty("transmission")
    private TransmissionResponse transmissionResponse;

    public TransmissionResponse(Transmission transmission){
        id = transmission.getId();
        type = transmission.getType();
    }
}
