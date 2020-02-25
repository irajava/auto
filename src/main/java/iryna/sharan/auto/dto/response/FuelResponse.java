package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import iryna.sharan.auto.entity.Fuel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuelResponse {
    private Long id;
    private String type; ;

    @JsonProperty("fuel")
    private FuelResponse fuelResponse;

    public FuelResponse(Fuel fuel){
        id = fuel.getId();
        type = fuel.getType();
    }
}

