package iryna.sharan.auto;

import iryna.sharan.auto.entity.*;
import iryna.sharan.auto.repository.*;
import iryna.sharan.auto.tools.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@SpringBootApplication
public class AutoApplication {

//    @Autowired
//    private BodyRepository bodyRepository;
//    @Autowired
//    private CarRepository carRepository;
//    @Autowired
//    private ColourRepository colourRepository;
//    @Autowired
//    private ConditioningRepository conditioningRepository;
//    @Autowired
//    private EngineRepository engineRepository;
//    @Autowired
//    private FuelRepository fuelRepository;
//    @Autowired
//    private MakeRepository makeRepository;
//    @Autowired
//    private ModelRepository modelRepository;
//    @Autowired
//    private TransmissionRepository transmissionRepository;
//
//    @Autowired
//    private Logger logger;
//
//    @PostConstruct
//    public void init() {
//        logger.log("AutoApplication.init: start");
////        final Make vw = Make.builder().name("VW").build();
////        makeRepository.save(vw);
////        makeRepository.findAll().forEach(m-> System.out.println(m.getId()+" "+m.getName()));
////        final Car car = Car.builder().model("Passat").build();
////        carRepository.save(car);
////    carRepository.findAll().forEach(c-> System.out.println(c.getId()+" "+c.getModel()));
//        logger.log("AutoApplication.init: find by Id");
////        long id=1L;
////        final Optional<Make>byId = makeRepository.findById(id);
////        final Make make=byId.orElseThrow(()->
////        new IllegalArgumentException("Make with id "+id+ " not found"));
////        System.out.println(make.getName());
////
////        Body body = new Body();
////        body.setType("Combi");
////        bodyRepository.save(body);
////
////        Colour colour = new Colour();
////        colour.setName("White");
////        colourRepository.save(colour);
////
////        Conditioning conditioning = new Conditioning();
////        conditioning.setType("Automatic climatisation");
////        conditioningRepository.save(conditioning);
////
////        Engine engine = new Engine();
////        engine.setVolume(1.6);
////        engineRepository.save(engine);
////
////        Fuel fuel = new Fuel();
////        fuel.setType("Diesel");
////        fuelRepository.save(fuel);
//////
////        Make make = new Make();
////        make.setName("VW");
////        makeRepository.save(make);
//////
////        Transmission transmission = new Transmission();
////        transmission.setType("Automatic");
////        transmissionRepository.save(transmission);
//
////        final Make make = makeRepository.findById(1L).orElse(null);
//
//
////        Model model = Model.builder()
////                .name("Touran")
////                .make(make)
////                .build();
////        modelRepository.save(model);
//
////        final Body body = bodyRepository.findById(1L).orElse(null);
////        final Colour colour=colourRepository.findById(1L).orElse(null);
////        final Conditioning conditioning= conditioningRepository.findById(1L).orElse(null);
////        final Engine engine= engineRepository.findById(1L).orElse(null);
////        final Fuel fuel=fuelRepository.findById(1L).orElse(null);
////        final Transmission transmission=transmissionRepository.findById(1L).orElse(null);
////        final Model model = modelRepository.findById(1L).orElse(null);
////        logger.log("AutoApplication_1");
////
////        final Car car = Car.builder()
////                .productionDate(LocalDate.of(2008, Month.JUNE, 12))
////                .price(9000)
////                .mileage(150000)
////                .numberSeats(5)
////                .numberDoors(5)
////                .body(body)
////                .colour(colour)
////                .conditioning(conditioning)
////                .engine(engine)
////                .fuel(fuel)
////                .model(model)
////                .transmission(transmission)
////                .build();
////        carRepository.save(car);
//
//////
////        final Car car1=carRepository.findById(1L).orElse(null);
////        System.out.println(car1.getMake().getName());
////        System.out.println(car1.getModel());
////        System.out.println(car1.getPrice());
////
//// List<Car> cars=new ArrayList<>();
////       cars.add(car1);
////       cars.add(car2);
////       cars.add(car3);
//
////        carRepository.findAllByModelLike("%t%").forEach(c-> System.out.println(c.getId()+", "+c.getModel()+", "+c.getPrice()));
//        transmissionRepository.findAll().forEach(t -> System.out.println(t.getId() + "-" + t.getType()));
////        fuelRepository.findAll().forEach(f-> System.out.println(f.getId()+"-"+f.getType()));
//        logger.log("AutoApplication.init: end");
//
//    }

    public static void main(String[] args) {

        SpringApplication.run(AutoApplication.class, args);
    }

}
