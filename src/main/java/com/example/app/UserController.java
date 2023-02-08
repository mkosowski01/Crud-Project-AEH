package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @GetMapping("/test")
    public int test() {
        return 200;
    }

    @GetMapping("")
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") int id) {
        return userRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<User> users) {
        return userRepository.add(users);
    }
    // TODO
    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody User updatedUser) {
        User user = userRepository.getById(id);

        if (user != null) {
            user.setName(updatedUser.getName());
            user.setPassword(updatedUser.getPassword());

            userRepository.update(user);
                return 200;
        } else {
            return 404;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return userRepository.delete(id);
    }
}
