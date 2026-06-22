package com.carhire.model;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id

    @Column(name="reg_number", nullable = false,updatable = false)
    private  String regNumber;
    @Column(name="brand", nullable = false)
    private  String brand;
    @Column(name="model", nullable = false)
    private  String model;
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private  VehicleCategory category;
    @Column(name = "base_daily_rate", nullable = false)
    private  double baseDailyRate;
    @Column(name = "mileage_since_last_service", nullable = false)
    private int mileageSinceLastService;

    protected Vehicle() {}

    public Vehicle(String regNumber, String brand, String model, VehicleCategory category,
                   double baseDailyRate, int mileageSinceLastService) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.baseDailyRate = baseDailyRate;
        this.mileageSinceLastService = mileageSinceLastService;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public double getBaseDailyRate() {
        return baseDailyRate;
    }

    public int getMileageSinceLastService() {
        return mileageSinceLastService;
    }

    public void updateMileageSinceLastService(int mileage) {
        if (mileage < 0) {
            throw new IllegalArgumentException("Mileage cannot be negative");
        }
        this.mileageSinceLastService = mileage;
    }

    public void resetMileageAfterService() {
        this.mileageSinceLastService = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle other)) return false;
        return regNumber != null && regNumber.equals(other.regNumber);
    }

    @Override
    public int hashCode() {
        return regNumber == null ? 0 : regNumber.hashCode();
    }
}