package com.aitnacer.LabXpert.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_analyse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "typeAnalyse", fetch = FetchType.LAZY)
    private List<TestAnalyse> testAnalyses;


}
