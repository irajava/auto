package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import iryna.sharan.auto.entity.Make;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakeResponse {
    private Long id;
    private String name;

    @JsonProperty("make")
    private MakeResponse makeResponse;

    public MakeResponse(Make make){
        id= make.getId();
        name=make.getName();
    }
}
