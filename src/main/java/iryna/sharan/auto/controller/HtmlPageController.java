package iryna.sharan.auto.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlPageController {

    @GetMapping("/cars")
    public String car(){ return "cars.html"; }
    @GetMapping("/bodies")
    public String body(){
        return "bodies.html";
    }
    @GetMapping("/colours")
    public String colour(){
        return "colours.html";
    }
    @GetMapping("/conditionings")
    public String conditioning(){
        return "conditionings.html";
    }
    @GetMapping("/engines")
    public String engine(){ return "engines.html"; }
    @GetMapping("/models")
    public String model(){
        return "models.html";
    }
    @GetMapping("/makes")
    public String make(){
        return "makes.html";
    }
    @GetMapping("/fuels")
    public String fuel(){
        return "fuels.html";
    }
    @GetMapping("/transmissions")
    public String transmission(){
        return "transmissions.html";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/index")
    public String index(){ return "index.html"; }
    @GetMapping("/item")
    public String item (){ return "car.html"; }
    @GetMapping("/indexUser")
    public String indexUser(){ return "indexUser.html"; }
    @GetMapping("/addCar")
    public String addCar(){ return "addCar.html"; }
    @GetMapping("/login")
    public String login(){ return "login.html"; }
    @GetMapping("/register")
    public String register(){ return "register.html"; }

}
