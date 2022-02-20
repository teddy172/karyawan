package com.binar.grab2.models;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Data
@Entity
@Table(name = "rekening")
public class Rekening implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama",nullable = false, length = 16)
    private String nama;

    @Column(name = "jenis", nullable = false)
    private String jenis;

    @Column(name = "nomer", nullable = false, length = 50)
    private String nomer;

    @ManyToOne
    @JoinColumn(name = "id_karyawan")
    Karyawan karyawan;
}
