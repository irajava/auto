package iryna.sharan.auto.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class PaginationRequest {

    @Positive
    @NotNull
    private Integer size;
    @PositiveOrZero
    @NotNull
    private Integer page;
    @NotBlank
    private String field;
    @NotNull
    private Sort.Direction direction;

    public Pageable mapToPageable(){
        return PageRequest.of(page, size, direction, field);
    }
}
