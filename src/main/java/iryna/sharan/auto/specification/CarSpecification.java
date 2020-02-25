package iryna.sharan.auto.specification;

import com.fasterxml.jackson.annotation.JsonFormat;
import iryna.sharan.auto.dto.request.CarSearchRequest;
import iryna.sharan.auto.dto.response.ModelResponse;
import iryna.sharan.auto.entity.Car;
import iryna.sharan.auto.entity.Fuel;
import iryna.sharan.auto.entity.Make;
import iryna.sharan.auto.entity.Model;
import iryna.sharan.auto.tools.Utils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class CarSpecification implements Specification<Car> {
    private Long makeId;
    private Long modelId;
    private LocalDateTime productionDateFrom;
    private LocalDateTime productionDateTo;
    private Integer minPrice;
    private Integer maxPrice;
    private Long fuelId;


    public CarSpecification(CarSearchRequest carSearchRequest) {
        makeId = carSearchRequest.getMakeId();
        modelId = carSearchRequest.getModelId();
        productionDateFrom= Utils.dateToLocalDate(carSearchRequest.getProductionDateFrom());
        productionDateTo= Utils.dateToLocalDate(carSearchRequest.getProductionDateTo());
        minPrice=carSearchRequest.getMinPrice();
        maxPrice=carSearchRequest.getMaxPrice();
        fuelId=carSearchRequest.getFuelId();

    }

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<>();
        predicates.add(findByMakeAndModel(root, cb));
        predicates.add(findByModel(root, cb));
        predicates.add(findByDate(root, cb));
        predicates.add(findByPrice(root, cb));
        predicates.add(findByFuel(root, cb));
        return cb.and(predicates.toArray(new Predicate[0]));
    }



    private Predicate findByPrice(Root<Car> root, CriteriaBuilder cb) {
        Predicate predicate;
        if (minPrice != null && maxPrice != null) {
            predicate = cb.between(root.get("price"), minPrice, maxPrice);
        } else if (maxPrice != null) {
            predicate = cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        } else if (minPrice != null) {
            predicate = cb.greaterThanOrEqualTo(root.get("price"), minPrice);
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByDate(Root<Car> root, CriteriaBuilder cb) {
        Predicate predicate;
        if (productionDateFrom != null && productionDateTo != null) {
            predicate = cb.between(root.get("productionDate"), productionDateFrom, productionDateTo);
        } else if (productionDateTo != null) {
            predicate = cb.lessThanOrEqualTo(root.get("productionDate"), productionDateTo);
        } else if (productionDateFrom != null) {
            predicate = cb.greaterThanOrEqualTo(root.get("productionDate"), productionDateFrom);
        } else {
            predicate = cb.conjunction();
        }

        return predicate;
    }

    private Predicate findByMakeAndModel(Root<Car> root, CriteriaBuilder cb) {
        Predicate predicate;
        final Join<Car, Model> modelJoin = root.join("model");
        final Join<Model, Make> makeJoin = modelJoin.join("make");

        if (modelId != null){
            predicate = cb.equal(modelJoin.get("id"), modelId);
        }else if (makeId != null) {
            predicate = cb.equal(makeJoin.get("id"), makeId);
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByModel(Root<Car> root, CriteriaBuilder cb) {
        Predicate predicate;
        if (modelId != null) {
            final Join<Car, Model> modelTable = root.join("model");
            predicate = cb.equal(modelTable.get("id"), modelId);
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }

    private Predicate findByFuel(Root<Car> root, CriteriaBuilder cb) {
        Predicate predicate;
        if (fuelId != null) {
            final Join<Car, Fuel> fuelTable = root.join("fuel");
            predicate = cb.equal(fuelTable.get("id"), fuelId);
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }
}
