package iryna.sharan.auto.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ConditioningRequest {
    @NotBlank
    private String type;

}
