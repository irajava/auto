package iryna.sharan.auto.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CarRequest {
    @NotBlank
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;
    @Positive
    private Integer price;
    @Positive
    private Integer mileage;
    @Positive
    private Integer numberSeats;
    @Positive
    private Integer numberDoors;
    private Long bodyId;
    private Long colourId;
    private Long conditioningId;
    private Long engineId;
    private Long fuelId;
    private Long modelId;
    private Long transmissionId;

    @NotBlank
    private String image;

}
