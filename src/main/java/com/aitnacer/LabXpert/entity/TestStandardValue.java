package com.aitnacer.LabXpert.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TestStandardValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unite;
    private float minValue;
    private float maxValue;

    @OneToOne(mappedBy = "testStandardValue")
    private Test test;


    @Override
    public String toString() {
        return "TestStandardValue{" +
                "id=" + id +
                ", unite='" + unite + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}
