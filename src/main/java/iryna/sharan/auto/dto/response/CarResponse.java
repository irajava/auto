package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import iryna.sharan.auto.entity.Car;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CarResponse {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime productionDate;
    private Integer price;
    private Integer mileage;
    private Integer numberSeats;
    private Integer numberDoors;
    private String bodyType;
    private String colourName;
    private String conditioningType;
    private Double engineVolume;
    private String fuelType;
    private ModelResponse model;
    private String transmissionType;
    private String imageName;

    public CarResponse(Car car) {
        id = car.getId();
        productionDate = car.getProductionDate();
        price = car.getPrice();
        mileage = car.getMileage();
        numberSeats = car.getNumberSeats();
        numberDoors = car.getNumberDoors();
        imageName = car.getImageName();

        if (car.getBody() != null) {
            bodyType = car.getBody().getType();
        }
        if (car.getColour() != null) {
            colourName = car.getColour().getName();
        }
        if (car.getConditioning() != null) {
            conditioningType = car.getConditioning().getType();
        }
        if (car.getEngine() != null) {
            engineVolume = car.getEngine().getVolume();
        }
        if (car.getFuel() != null) {
            fuelType = car.getFuel().getType();
        }
        if (car.getModel() != null) {
            model=new ModelResponse(car.getModel());

        }
        if (car.getTransmission() != null) {
            transmissionType = car.getTransmission().getType();
        }
    }
}
