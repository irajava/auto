package iryna.sharan.auto.controller;

import iryna.sharan.auto.dto.request.CarRequest;
import iryna.sharan.auto.dto.request.CarSearchRequest;
import iryna.sharan.auto.dto.request.PaginationRequest;
import iryna.sharan.auto.dto.response.CarResponse;
import iryna.sharan.auto.dto.response.CarResponseWithModels;
import iryna.sharan.auto.dto.response.PageResponse;
import iryna.sharan.auto.entity.Car;
import iryna.sharan.auto.repository.UserRepository;
import iryna.sharan.auto.service.CarService;
import iryna.sharan.auto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public void save(@RequestBody CarRequest request) throws IOException {
        carService.save(request);
    }

    @PostMapping("/addCar")
    public void saveCar(@RequestBody CarRequest request) throws IOException {
        carService.saveCarForUser(request);
    }

    @GetMapping
    public List<CarResponse> findAll() {
        return carService.findAll();
    }

    @GetMapping("/favorites")
    public Set<CarResponse> loadFavoritesCars() {
        return carService.loadFavoritesCars();
    }


    @GetMapping("/{id}")
    public CarResponseWithModels findOne(@PathVariable Long id) {
        return carService.findOneWithModel(id);
    }

    @GetMapping("/byId")
    public CarResponse findOneCar(Long id) {
        return new CarResponse(carService.findOne(id));
    }

    @PutMapping
    public void update(Long id, @RequestBody CarRequest request) throws IOException {
        carService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        carService.delete(id);
    }

    @GetMapping("/page")
    public PageResponse<CarResponse> findAll(CarSearchRequest request) {
        return carService.findAll(request);
    }


}
