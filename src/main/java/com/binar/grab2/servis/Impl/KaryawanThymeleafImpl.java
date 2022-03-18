package com.binar.grab2.servis.Impl;

import com.binar.grab2.models.Karyawan;
import com.binar.grab2.repository.KaryawanRepository;
import com.binar.grab2.servis.KaryawanThymeleafService;
import com.binar.grab2.util.BadResourceException;
import com.binar.grab2.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional//opsi
public class KaryawanThymeleafImpl implements KaryawanThymeleafService {
//ioc data injection
    @Autowired
    KaryawanRepository repo;

    @Override
    public List<Karyawan> listKaryawanTymeleaf(int pageNumber, int ROW_PER_PAGE) {
        List<Karyawan> karyawans = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, ROW_PER_PAGE,
                Sort.by("id").ascending());
        repo.findAll(sortedByIdAsc).forEach(karyawans::add);
        return  karyawans;

    }

    @Override
    public boolean existsByIdTymeleaf(Long id) {
        return repo.existsById(id);
    }

    @Override
    public Karyawan findByIdTymeleaf(Long id) throws ResourceNotFoundException {
        Karyawan karyawan = repo.findById(id).orElse(null);
        if (karyawan==null) {
            throw new ResourceNotFoundException("Cannot find karyawan with id: " + id);
        }
        else return karyawan;
    }

    @Override
    public Karyawan saveTymeleaf(Karyawan karyawan) throws BadResourceException {
        return repo.save(karyawan);
    }

    @Override
    public void updateTymeleaf(Karyawan karyawan) throws ResourceNotFoundException, BadResourceException {
        if (!existsByIdTymeleaf(karyawan.getId())) {
            throw new ResourceNotFoundException("Cannot find Karyawan with id: " + karyawan.getId());
        }
        repo.save(karyawan);
    }

    @Override
    public void deleteByIdTymeleaf(Long id) throws ResourceNotFoundException {
        if (!existsByIdTymeleaf(id)) {
            throw new ResourceNotFoundException("Cannot find karyawan with id: " + id);
        }
        else {
            Karyawan a = repo.getbyID(id);
            a.setDeleted_date(new Date());
            repo.save(a);
        }
    }
}
