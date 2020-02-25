package iryna.sharan.auto.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ModelRequest {
    @NotBlank
    private String name;
    @NotNull
    private Long makeId;


}
