package com.gurudatta.motars.backend.controller;

import com.gurudatta.motars.backend.model.Admin;
import com.gurudatta.motars.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend access
public class AdminController {
    @Autowired
    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        admin = adminService.addAdmin(admin);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(admin);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> adminList = adminService.getAllAdmins();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminList);
    }
}
