package com.binar.grab2.servis;

import com.binar.grab2.models.Karyawan;
import com.binar.grab2.util.BadResourceException;
import com.binar.grab2.util.ResourceNotFoundException;

import java.util.List;
public interface KaryawanThymeleafService {
    public List<Karyawan> listKaryawanTymeleaf(int pageNumber, int ROW_PER_PAGE );

    public boolean existsByIdTymeleaf(Long id);

    public Karyawan findByIdTymeleaf(Long id) throws ResourceNotFoundException;

    public   Karyawan saveTymeleaf(Karyawan karyawan) throws BadResourceException;

    public void updateTymeleaf(Karyawan karyawan) throws ResourceNotFoundException, BadResourceException;

    public void deleteByIdTymeleaf(Long id) throws ResourceNotFoundException;

}
