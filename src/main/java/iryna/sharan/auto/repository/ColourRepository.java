package iryna.sharan.auto.repository;

import iryna.sharan.auto.entity.Colour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColourRepository  extends JpaRepository<Colour, Long> {
}
