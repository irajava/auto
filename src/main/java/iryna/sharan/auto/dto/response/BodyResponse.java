package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import iryna.sharan.auto.entity.Body;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyResponse {
    private Long id;
    private String type;

    @JsonProperty("body")
    private BodyResponse bodyResponse;

        public BodyResponse(Body body){
        id = body.getId();
        type = body.getType();
    }
}
