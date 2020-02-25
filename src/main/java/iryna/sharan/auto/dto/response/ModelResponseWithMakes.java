package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import iryna.sharan.auto.entity.Model;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ModelResponseWithMakes {

        private Long id;
        private String name;

        @JsonProperty("make")
        private MakeResponse makeResponse;

        public ModelResponseWithMakes(Model model){
            id = model.getId();
            name = model.getName();
            if (model.getMake() != null) {
                makeResponse = new MakeResponse(model.getMake());
            }

        }

    }

