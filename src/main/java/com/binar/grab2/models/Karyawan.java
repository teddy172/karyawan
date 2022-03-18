package com.binar.grab2.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "karyawan")
public class Karyawan extends AbstractDate implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false, length = 45)
    private String nama;

    @Column(name = "jk")
    private String jk;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "status")
    private String status;

    //relasi
    @OneToMany(mappedBy = "karyawan")
    List<KaryawanTraining> karyawanTrainings;

    @OneToMany(mappedBy = "karyawan")
    List<Rekening> rekenings;

    @OneToOne(mappedBy = "karyawan")
    private DetailKaryawan detailKaryawan;

//    @OneToOne(mappedBy = "karyawan")
//    private DetailKaryawan detailKaryawan;

}