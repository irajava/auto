package iryna.sharan.auto.repository;

import iryna.sharan.auto.entity.Car;
import iryna.sharan.auto.entity.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    @Query("select distinct c from Car c join c.users u where u.username = :username")
    Set<Car> findAllByUsersName(@Param("username") String username);

}
