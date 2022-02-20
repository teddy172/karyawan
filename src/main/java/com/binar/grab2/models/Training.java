package com.binar.grab2.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "training")
public class Training implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tema", nullable = false, length = 45)
    private String tema;

    @Column(name = "namaPengajar", nullable = false, length = 18)
    private String namaPengajar;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
    List<KaryawanTraining> karyawanTrainings;
}
