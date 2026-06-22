package com.carhire.model;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id

    @Column(name="id", nullable = false, updatable = false)
    private String id;
    @Column(name="full_name", nullable = false)
    private String fullName;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name="driving_experience_years", nullable = false)
    private int drivingExperienceYears;

    protected Customer() {}

    public Customer(String id, String fullName, int age, int drivingExperienceYears) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.drivingExperienceYears = drivingExperienceYears;
    }

    public String getId() {
        return id;
    }

    public int getDrivingExperienceYears() {
        return drivingExperienceYears;
    }

    public int getAge() {
        return age;
    }

    public String getFullName() {
        return fullName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer other)) return false;
        return id != null && id.equals(other.id);
    }
    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
