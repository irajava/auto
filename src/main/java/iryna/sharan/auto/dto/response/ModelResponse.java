package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import iryna.sharan.auto.entity.Make;
import iryna.sharan.auto.entity.Model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelResponse {
    private Long id;
    private String name;
    private String makeName;

    @JsonProperty("model")
    private ModelResponse modelResponse;

    public ModelResponse(Model model) {
        id = model.getId();
        name = model.getName();
        if (model.getMake() != null) {
            makeName = model.getMake().getName();
        }


    }

}
