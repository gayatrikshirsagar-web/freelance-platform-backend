package com.freelance_platform.service;

import com.freelance_platform.entity.Client;
import com.freelance_platform.entity.Student;
import com.freelance_platform.entity.User;
import com.freelance_platform.repository.ClientRepository;
import com.freelance_platform.repository.StudentRepository;
import com.freelance_platform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final ClientRepository clientRepository;

    public UserService(
            UserRepository userRepository,
            StudentRepository studentRepository,
            ClientRepository clientRepository) {

        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.clientRepository = clientRepository;
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET USER BY ID
    public User getUserById(Integer id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: " + id
                        )
                );
    }

    // CREATE USER + STUDENT/CLIENT PROFILE
    public User createUser(User user) {

        // 1. Save user first
        User savedUser = userRepository.save(user);

        // 2. Check selected role
        if (savedUser.getRole() == User.Role.STUDENT) {

            Student student = new Student();

            student.setUser(savedUser);

            studentRepository.save(student);

        } else if (savedUser.getRole() == User.Role.CLIENT) {

            Client client = new Client();

            client.setUser(savedUser);

            clientRepository.save(client);
        }

        // 3. Return saved user
        return savedUser;
    }

    // DELETE USER
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}