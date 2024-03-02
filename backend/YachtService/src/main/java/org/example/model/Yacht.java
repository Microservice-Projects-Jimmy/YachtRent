package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.YachtEntity;

import java.math.BigDecimal;

@Getter
@Setter
public class Yacht {
    private long id;
    private String name;
    private  Long typeId;
    private  BigDecimal price;
    private  String crew;
    private  String description;
    private  Integer sleepingCapacity;
    private  Integer cruiseCapacity;
    private String imagePath;

    public static Yacht toModel(YachtEntity yachtEntity) {
        var yacht = new Yacht();
        yacht.setId(yachtEntity.getId());
        yacht.setName(yachtEntity.getName());
        yacht.setTypeId(yachtEntity.getTypeId());
        yacht.setPrice(yachtEntity.getPrice());
        yacht.setCrew(yachtEntity.getCrew());
        yacht.setDescription(yachtEntity.getDescription());
        yacht.setSleepingCapacity(yachtEntity.getSleepingCapacity());
        yacht.setCruiseCapacity(yachtEntity.getCruiseCapacity());
        yacht.setImagePath(yachtEntity.getImagePath());

        return yacht;
    }

}
