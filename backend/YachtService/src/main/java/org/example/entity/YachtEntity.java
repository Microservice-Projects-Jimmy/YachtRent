package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "yachts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YachtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String name;
    private  Long typeId;
    private  Long creatorId;
    private  BigDecimal price;
    private  String crew;
    private  String description;
    private  Integer sleepingCapacity;
    private  Integer cruiseCapacity;
    private  LocalDateTime createdAt;
    private  LocalDateTime deletedAt;
    private  String imagePath;

}
