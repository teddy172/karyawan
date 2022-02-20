package com.binar.grab2.models;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Data
@Entity
@Table(name = "detailkaryawan")
public class DetailKaryawan {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NIK", nullable = false, length = 16)
    private String nik;

    @Column(name = "NPWP", nullable = false, length = 16)
    private String npwp;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

}
