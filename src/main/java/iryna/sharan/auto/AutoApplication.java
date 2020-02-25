package iryna.sharan.auto;

import iryna.sharan.auto.entity.*;
import iryna.sharan.auto.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@SpringBootApplication
public class AutoApplication {



    public static void main(String[] args) {

        SpringApplication.run(AutoApplication.class, args);
    }

}
