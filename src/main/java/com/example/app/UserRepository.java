package com.example.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

//    @EventListener(ApplicationReadyEvent.class)
//    public void createDb() {
//        jdbcTemplate.execute("DROP TABLE IF EXISTS user");
//        jdbcTemplate.execute("CREATE TABLE user (id int, name varchar(45), password varchar(45)");
//        log.info("Create table");
//    }

    public List<User> getAll() {
        return jdbcTemplate.query("SELECT id, name, password FROM user",
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT id, name, password FROM user WHERE id=?",
                BeanPropertyRowMapper.newInstance(User.class), id);
    }

    public int add(List<User> users) {
        users.forEach(user -> jdbcTemplate
                .update("INSERT INTO user(name, password) VALUES(?, ?)",
                        user.getName(), user.getPassword()));
        return 200;
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE user SET name=?, password=? WHERE id=?",
                user.getName(), user.getPassword(), user.getId());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM user WHERE id=?", id);
    }
}