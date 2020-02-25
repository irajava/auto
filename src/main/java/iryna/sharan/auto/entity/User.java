package iryna.sharan.auto.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(nullable = false)
    private UserRole userRole;


    @ManyToMany
    private Set<Car> cars = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Car> cars1 = new ArrayList<>();


    @ManyToMany
    private List<Car> favorites = new ArrayList<>();
 }
