package iryna.sharan.auto.repository;

import iryna.sharan.auto.entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelRepository extends JpaRepository<Fuel,Long> {
}
