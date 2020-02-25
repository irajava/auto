package iryna.sharan.auto.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class FuelRequest {
    @NotBlank
    private String type;

}
