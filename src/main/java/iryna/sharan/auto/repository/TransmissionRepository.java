package iryna.sharan.auto.repository;

import iryna.sharan.auto.entity.Car;
import iryna.sharan.auto.entity.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransmissionRepository extends JpaRepository<Transmission,Long>{

}
