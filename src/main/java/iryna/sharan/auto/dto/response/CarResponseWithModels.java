package iryna.sharan.auto.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import iryna.sharan.auto.entity.Car;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CarResponseWithModels {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ")
    private LocalDateTime productionDate;
    private Integer price;
    private Integer mileage;
    private Integer numberSeats;
    private Integer numberDoors;


    @JsonProperty("body")
    private BodyResponse bodyResponse;
    @JsonProperty("colour")
    private ColourResponse colourResponse;
    @JsonProperty("conditioning")
    private ConditioningResponse conditioningResponse;
    @JsonProperty("engine")
    private EngineResponse engineResponse;
    @JsonProperty("fuel")
    private FuelResponse fuelResponse;
    @JsonProperty("make")
    private MakeResponse makeResponse;
    @JsonProperty("model")
    private ModelResponse modelResponse;
    @JsonProperty("transmission")
    private TransmissionResponse transmissionResponse;

    public CarResponseWithModels (Car car){
        id = car.getId();
        productionDate = car.getProductionDate();
        price = car.getPrice();
        mileage = car.getMileage();
        numberSeats = car.getNumberSeats();
        numberDoors = car.getNumberDoors();

        if (car.getModel().getMake() != null) {
            makeResponse = new MakeResponse(car.getModel().getMake());
        }
        if (car.getModel() != null) {
            modelResponse = new ModelResponse(car.getModel());
        }

        if (car.getBody() != null) {
            bodyResponse = new BodyResponse(car.getBody());
        }
        if (car.getColour() != null) {
            colourResponse = new ColourResponse(car.getColour());
        }
        if (car.getConditioning() != null) {
            conditioningResponse = new ConditioningResponse(car.getConditioning());
        }
        if (car.getEngine () != null) {
            engineResponse = new EngineResponse(car.getEngine());
        }
        if (car.getFuel() != null) {
            fuelResponse = new FuelResponse(car.getFuel());
        }
        if (car.getTransmission() != null) {
            transmissionResponse = new TransmissionResponse(car.getTransmission());
        }

    }

}
