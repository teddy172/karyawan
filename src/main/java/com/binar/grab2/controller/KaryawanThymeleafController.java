package com.binar.grab2.controller;

import com.binar.grab2.models.Karyawan;
import com.binar.grab2.repository.KaryawanRepository;
import com.binar.grab2.servis.KaryawanThymeleafService;
import com.binar.grab2.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v2/view/karyawan")
public class KaryawanThymeleafController {
    @Autowired
    KaryawanRepository repoKaryawan;
    @Autowired
    KaryawanThymeleafService service;

    private final int ROW_PER_PAGE = 5;

    //halaman
    @GetMapping(value = {"/","/index"})
    public String index(Model model){
        model.addAttribute("title", "title saya");
        return "index";
    }

    @GetMapping(value = "/list")
    public String getKaryawan(Model model,
                              @RequestParam(value = "page", defaultValue = "1") int pageNumber){
        List<Karyawan> karyawans = service.listKaryawanTymeleaf(pageNumber, ROW_PER_PAGE);
        long count = repoKaryawan.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("karyawans", karyawans);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber -1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "karyawan-list";
    }

    @GetMapping(value = {"/add"})
    public String showAddKaryawan(Model model){
        Karyawan karyawan = new Karyawan();
        model.addAttribute("add", true);
        model.addAttribute("karyawan", karyawan);

        return "karyawan-edit";
    }

    @PostMapping(value = "/add")
    public String addKaryawan(Model model,
                              @ModelAttribute("karyawan")Karyawan karyawan){
        try {
            System.out.println("nilai karyawan"+karyawan.getNama());
            Karyawan newKaryawan = service.saveTymeleaf(karyawan);
            return "redirect:/v2/view/karyawan/" + String.valueOf(newKaryawan.getId());
        }catch (Exception e){
            //log exception first
            // then show error
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            //model
            model.addAttribute("add", true);
            return "karyawan-edit";
        }
    }

    @GetMapping(value = {"/{karyawanId}/edit"})
    public String showEditKaryawan(Model model, @PathVariable long karyawanId) {
        Karyawan karyawan = null;
        try {
            karyawan = service.findByIdTymeleaf(karyawanId);
        }catch (ResourceNotFoundException e){
            model.addAttribute("errorMessage", "Karyawan not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("karyawan", karyawan);
        return "karyawan-edit";
    }

    @PostMapping(value = {"/{karyawanId}/edit"})
    public  String updateKaryawan(Model model,
                                  @PathVariable long karyawanId,
                                  @ModelAttribute("karyawan") Karyawan karyawan){
        try {
            karyawan.setId(karyawanId);
            service.updateTymeleaf(karyawan);
            return "redirect:/v2/view/karyawan/" + String.valueOf(karyawan.getId());
        }catch (Exception e){
            String errorMassage = e.getMessage();
            model.addAttribute("errorMessage", errorMassage);

            model.addAttribute("add", false);
            return "karyawan-edit";
        }
    }

    @GetMapping(value = "/{karyawanId}")
    public String getKaryawanById(Model model, @PathVariable long karyawanId){
        Karyawan karyawan = null;
        try {
            karyawan = service.findByIdTymeleaf(karyawanId);
        }catch (ResourceNotFoundException e){
            model.addAttribute("errorMessage", "karyawan not found");
        }
        model.addAttribute("karyawan", karyawan);
        return "karyawan";
    }

    //hapus
    @GetMapping(value = {"/{karyawanId}/delete"})
    public String showDeleteKaryawanById(
            Model model, @PathVariable long karyawanId){
                Karyawan karyawan = null;
                try {
                    karyawan = service.findByIdTymeleaf(karyawanId);
                }catch (ResourceNotFoundException e){
                    model.addAttribute("errorMessage", "karyawan not found");
                }
                model.addAttribute("allowDelete", true);
                model.addAttribute("karyawan", karyawan);
                return "karyawan";
    }

    @PostMapping(value = {"/{karyawanId}/delete"})
    public String deleteKaryawanById(
            Model model, @PathVariable long karyawanId){
        try {
            service.deleteByIdTymeleaf(karyawanId);
            return "redirect:/v2/view/karyawan/list";
        }catch (ResourceNotFoundException ex){
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "karyawan";
        }
    }
}
