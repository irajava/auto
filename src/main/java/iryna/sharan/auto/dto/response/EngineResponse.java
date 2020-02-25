package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import iryna.sharan.auto.entity.Engine;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineResponse {
    private Long id;
    private Double volume; ;

    @JsonProperty("engine")
    private EngineResponse engineResponse;

    public EngineResponse(Engine engine){
        id = engine.getId();
        volume = engine.getVolume();
    }
}
