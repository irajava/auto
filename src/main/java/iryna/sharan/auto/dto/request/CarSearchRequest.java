package iryna.sharan.auto.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CarSearchRequest {
    private Long makeId;
    private Long modelId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDateTo;
    private Integer minPrice;
    private Integer maxPrice;
    private Long fuelId;

    @JsonProperty("pagination")
    private PaginationRequest paginationRequest;
}
