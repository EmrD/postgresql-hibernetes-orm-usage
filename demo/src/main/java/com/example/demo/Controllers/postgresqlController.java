package com.example.demo.Controllers;
import com.example.demo.BaseContructors.UserRepository;
import com.example.demo.Models.testtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/postgresqlController")
@Service
class PostgresqlControllers {
    final UserRepository userRepository;

    @Autowired
    public PostgresqlControllers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users/create")
    public String createUser(@RequestBody testtable user) {
        try
        {
            userRepository.save(user);
            return "User created successfully.";
        } catch (Exception e) {
            return "An error occurred while creating the user." + e;
        }
    }

    @GetMapping("/users/get")
    public String getUser(@RequestParam String user)
    {
        try {
            testtable foundUser = new testtable();
            foundUser.setName(userRepository.findById(user).get().getName());
            foundUser.setEmail(userRepository.findById(user).get().getEmail());
            foundUser.setAge(userRepository.findById(user).get().getAge());

            return "User fetched successfully with name: " + foundUser.getName() + " email: " + foundUser.getEmail() + " age: " + foundUser.getAge();
        } catch (Exception e) {
            return "An error occurred while fetching the user.";
        }
    }

    @DeleteMapping("/users/delete")
    public String deleteUser(@RequestParam String user)
    {
        try {
            userRepository.deleteById(user);
            return "User " + user + " deleted successfully.";
        } catch (Exception e) {
            return "An error occurred while deleting the user.";
        }
    }

    @PutMapping("/users/update")
    public String updateUser(@RequestBody testtable user)
    {
        try {
            userRepository.save(user);
            return "User updated successfully.";
        } catch (Exception e) {
            return "An error occurred while updating the user.";
        }
    }
}