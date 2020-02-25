package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import iryna.sharan.auto.entity.Colour;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColourResponse {
    private Long id;
    private String name;

    @JsonProperty("colour")
    private ColourResponse colourResponse;

    public ColourResponse(Colour colour) {
        id = colour.getId();
        name = colour.getName();
    }
}
