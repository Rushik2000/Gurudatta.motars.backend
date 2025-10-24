package com.gurudatta.motars.backend.service;

import com.gurudatta.motars.backend.model.Admin;

import java.util.List;

public interface AdminService {
    Admin addAdmin(Admin admin);

    List<Admin> getAllAdmins();
}
