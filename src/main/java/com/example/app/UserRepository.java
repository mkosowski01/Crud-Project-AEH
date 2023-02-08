package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAll() {
        return jdbcTemplate.query("SELECT id, name, password FROM user",
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, password FROM user WHERE id=?",
                BeanPropertyRowMapper.newInstance(User.class), id);
    }

    public int add(List<User> users) {
        users.forEach(user -> jdbcTemplate
                .update("INSERT INTO user(name, password) VALUES(?, ?)",
                        user.getName(), user.getPassword()
                ));
        return 200;
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE user SET name=?, password=? WHERE id=?",
                user.getName(), user.getPassword(), user.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM user WHERE id=?", id);
    }
}