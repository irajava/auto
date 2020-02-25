package iryna.sharan.auto.controller;

import iryna.sharan.auto.dto.request.CarRequest;
import iryna.sharan.auto.dto.request.UserRequest;
import iryna.sharan.auto.dto.response.AuthenticationResponse;
import iryna.sharan.auto.dto.response.CarResponse;
import iryna.sharan.auto.entity.Car;
import iryna.sharan.auto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody UserRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@Valid @RequestBody UserRequest request) {
        return userService.register(request);
    }

    @GetMapping("/checkToken")
    public void checkToken() {
    }

    @PreAuthorize("authentication.principal == #text && hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/test")
    public void test(String text) {
        System.out.println("find cart of " + text);

    }

    @GetMapping
    public Set<CarResponse> loadFavoritesCars() {
        return userService.loadFavoritesCars();
    }


}
