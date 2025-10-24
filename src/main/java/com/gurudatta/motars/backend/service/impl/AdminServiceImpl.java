package com.gurudatta.motars.backend.service.impl;

import com.gurudatta.motars.backend.model.Admin;
import com.gurudatta.motars.backend.repository.AdminRepository;
import com.gurudatta.motars.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @Override
    public Admin addAdmin(Admin admin) {
        if (admin.getAid() == null || admin.getAid().equals("")) admin.setAid(UUID.randomUUID().toString());
        return adminRepository.insert(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
