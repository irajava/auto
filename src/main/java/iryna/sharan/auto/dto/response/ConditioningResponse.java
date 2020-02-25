package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import iryna.sharan.auto.entity.Conditioning;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConditioningResponse {
    private Long id;
    private String type; ;

    @JsonProperty("conditioning")
    private ConditioningResponse conditioningResponse;

    public ConditioningResponse(Conditioning conditioning){
        id = conditioning.getId();
        type = conditioning.getType();
    }
}
