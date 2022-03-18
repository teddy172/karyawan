package com.binar.grab2.models;
import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "karyawanTraining")
public class KaryawanTraining implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_training")
    Training training;

    @ManyToOne
    @JoinColumn(name = "id_karyawan")
    Karyawan karyawan;

    private String tanggal;
}
