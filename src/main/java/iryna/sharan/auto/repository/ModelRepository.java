package iryna.sharan.auto.repository;

import iryna.sharan.auto.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {

    Stream<Model> findAllByMakeId(Long makeId);
}
