package com.mypackage.servesmart.controller;

import com.mypackage.servesmart.dto.UserDTO;
import com.mypackage.servesmart.model.User;
import com.mypackage.servesmart.service.AdminService;
import com.mypackage.servesmart.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/users")
    public String createUser(@RequestBody UserDTO userDTO) {
        return adminService.createUser(userDTO);
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return adminService.updateUser(id, userDTO);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return adminService.deleteUser(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @PostMapping("/upload-students")
    public ResponseEntity<String> uploadStudents(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid Excel file.");
        }
        try {
            adminService.saveStudentsFromExcel(file);
            return ResponseEntity.ok("Students uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file: " + e.getMessage());
        }
    }

}