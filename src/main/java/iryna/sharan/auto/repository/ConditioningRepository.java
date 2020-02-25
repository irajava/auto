package iryna.sharan.auto.repository;

import iryna.sharan.auto.entity.Conditioning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditioningRepository extends JpaRepository<Conditioning, Long> {
}
