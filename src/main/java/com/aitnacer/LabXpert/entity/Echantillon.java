package com.aitnacer.LabXpert.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "echantillons")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Echantillon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateDeReception;
    private String echantillonCode;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Utilisateur utilisateur;
    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;
    @Column(name = "is_assigned", nullable = false)
    private boolean assigned;

    @CreationTimestamp
    @Column(name = "_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "echantillons_updated_at")
    private LocalDateTime updatedAt;
    @OneToMany
    private List<ReactifAnalyse> reactifAnalyses = new ArrayList<>();




}
