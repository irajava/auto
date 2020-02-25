package iryna.sharan.auto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ")
    private LocalDateTime productionDate;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Integer mileage;
    @Column(nullable = false)
    private Integer numberSeats;
    @Column(nullable = false)
    private Integer numberDoors;

    private String imageName;

    @ManyToOne
    private Body body;
    @ManyToOne
    private Colour colour;
    @ManyToOne
    private Conditioning conditioning;
    @ManyToOne
    private Engine engine;
    @ManyToOne
    private Fuel fuel;
    @ManyToOne
    private Model model;
    @ManyToOne
    private Transmission transmission;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "favorites")
    private List<User> users = new ArrayList<>();

}
