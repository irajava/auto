package iryna.sharan.auto.service;

import iryna.sharan.auto.dto.request.CarRequest;
import iryna.sharan.auto.dto.request.UserRequest;
import iryna.sharan.auto.dto.response.AuthenticationResponse;
import iryna.sharan.auto.dto.response.CarResponse;
import iryna.sharan.auto.entity.Car;
import iryna.sharan.auto.entity.Model;
import iryna.sharan.auto.entity.User;
import iryna.sharan.auto.entity.UserRole;
import iryna.sharan.auto.repository.CarRepository;
import iryna.sharan.auto.repository.UserRepository;
import iryna.sharan.auto.security.JwtTokenTool;
import iryna.sharan.auto.security.JwtUser;
import iryna.sharan.auto.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenTool jwtTokenTool;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private CarRepository carRepository;

    public AuthenticationResponse register(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadCredentialsException("User with username " + request.getUsername() + " already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setUserRole(UserRole.ROLE_USER);
        user.setPassword(encoder.encode(request.getPassword()));

        userRepository.save(user);

        return login(request);
    }

    public AuthenticationResponse login(UserRequest request) {
        String username = request.getUsername();
        User user = findByUsername(username);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        String token = jwtTokenTool.createToken(username, user.getUserRole());
        return new AuthenticationResponse(username, token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return new JwtUser(user.getUsername(), user.getUserRole(), user.getPassword());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not exists"));
    }


    public Set<CarResponse> loadFavoritesCars() {
        final String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return carRepository.findAllByUsersName(username).stream().map(CarResponse::new).collect(Collectors.toSet());
    }



}
