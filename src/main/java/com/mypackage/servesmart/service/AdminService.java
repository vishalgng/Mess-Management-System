package com.mypackage.servesmart.service;

import com.mypackage.servesmart.dto.UserDTO;
import com.mypackage.servesmart.model.Role;
import com.mypackage.servesmart.model.User;
import com.mypackage.servesmart.repository.UserRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public String createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return "User with this email already exists!";
        }

        User newUser = new User();
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        newUser.setRole(userDTO.getRole());

        userRepository.save(newUser);
        return "User created successfully!";
    }

    public String updateUser(Long id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());

            userRepository.save(user);
            return "User updated successfully!";
        } else {
            return "User not found!";
        }
    }

    public String deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted successfully!";
        } else {
            return "User not found!";
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveStudentsFromExcel(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<User> students = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                User student = new User();
                student.setName(row.getCell(0).getStringCellValue().trim());
                student.setEmail(row.getCell(1).getStringCellValue().trim());
                student.setPassword(row.getCell(2).getStringCellValue().trim()); // No encoding

                student.setRole(Role.STUDENT); // Assign Student Role
                students.add(student);
            }

            userRepository.saveAll(students); // Batch insert for efficiency
        }
    }

}

