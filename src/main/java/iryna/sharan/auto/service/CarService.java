package iryna.sharan.auto.service;

import iryna.sharan.auto.dto.request.CarRequest;
import iryna.sharan.auto.dto.request.CarSearchRequest;
import iryna.sharan.auto.dto.request.PaginationRequest;
import iryna.sharan.auto.dto.response.CarResponse;
import iryna.sharan.auto.dto.response.CarResponseWithModels;
import iryna.sharan.auto.dto.response.PageResponse;
import iryna.sharan.auto.entity.*;
import iryna.sharan.auto.repository.CarRepository;
import iryna.sharan.auto.repository.UserRepository;
import iryna.sharan.auto.specification.CarSpecification;
import iryna.sharan.auto.tools.FileTool;
import iryna.sharan.auto.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BodyService bodyService;
    @Autowired
    private ColourService colourService;
    @Autowired
    private ConditioningService conditioningService;
    @Autowired
    private EngineService engineService;
    @Autowired
    private FuelService fuelService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private TransmissionService transmissionService;
    @Autowired
    private FileTool fileTool;


    public void save(CarRequest request) throws IOException {
        final Car car = carRequestToCar(request, null);
        carRepository.save(car);

    }

    public void update(CarRequest request, Long id) throws IOException {
        Car car = carRequestToCar(request, findOne(id));
        carRepository.save(car);
    }

    public void delete(Long id) {
        carRepository.delete(findOne(id));
    }

    public Car findOne(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car with id " + id + " not found."));
    }

    public List<CarResponse> findAll() {
        return carRepository.findAll()
                .stream()
                .map(c -> new CarResponse(c))
                .collect(Collectors.toList());
    }

    public PageResponse<CarResponse> findAll(CarSearchRequest request) {
        Page<Car> page = carRepository.findAll(new CarSpecification(request), request.getPaginationRequest().mapToPageable());
        return new PageResponse<>(
                page.get().map(CarResponse::new).collect(Collectors.toList()),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public PageResponse<CarResponse> findAll(PaginationRequest pagination) {
        final Page<Car> page = carRepository.findAll(pagination.mapToPageable());
        return new PageResponse<>(
                page.get().map(CarResponse::new).collect(Collectors.toList()),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public Set<CarResponse> loadFavoritesCars() {
        final String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return carRepository.findAllByUsersName(username).stream().map(CarResponse::new).collect(Collectors.toSet());
    }



    public CarResponseWithModels findOneWithModel(Long id) {
        return new CarResponseWithModels(findOne(id));
    }

    private Car carRequestToCar(CarRequest request, Car car) throws IOException {
        if (car == null) {
            car = new Car();
        }


        if (request.getImage() != null) {
            car.setImageName(fileTool.saveFile(request.getImage()));
        }

        car.setProductionDate(Utils.dateToLocalDate(request.getProductionDate()));
        car.setPrice(request.getPrice());
        car.setMileage(request.getMileage());
        car.setNumberSeats(request.getNumberSeats());
        car.setNumberDoors(request.getNumberDoors());
        car.setBody(bodyService.findOne(request.getBodyId()));
        car.setColour(colourService.findOne(request.getColourId()));
        car.setConditioning(conditioningService.findOne(request.getConditioningId()));
        car.setEngine(engineService.findOne(request.getEngineId()));
        car.setFuel(fuelService.findOne(request.getFuelId()));
        car.setModel(modelService.findOne(request.getModelId()));
        car.setTransmission(transmissionService.findOne(request.getTransmissionId()));
        return car;

    }

    public void saveCarForUser(CarRequest request) throws IOException {

        final Car car = new Car();
        if (request.getImage() != null) {
            car.setImageName(fileTool.saveFile(request.getImage()));
        }
        car.setProductionDate(Utils.dateToLocalDate(request.getProductionDate()));
        car.setPrice(request.getPrice());
        car.setMileage(request.getMileage());
        car.setNumberSeats(request.getNumberSeats());
        car.setNumberDoors(request.getNumberDoors());
        car.setBody(bodyService.findOne(request.getBodyId()));
        car.setColour(colourService.findOne(request.getColourId()));
        car.setConditioning(conditioningService.findOne(request.getConditioningId()));
        car.setEngine(engineService.findOne(request.getEngineId()));
        car.setFuel(fuelService.findOne(request.getFuelId()));
        car.setModel(modelService.findOne(request.getModelId()));
        car.setTransmission(transmissionService.findOne(request.getTransmissionId()));

        car.setUser(userService.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        carRepository.save(car);

    }


}