package com.hcc.utils;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
        loadAuthorityData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String pw = passwordEncoder.encode("password123");
            User learner1 = new User(LocalDate.now(), "learner1", pw);
            userRepository.save(learner1);
            User reviewer1 = new User(LocalDate.now(), "reviewer1", pw);
            userRepository.save(reviewer1);
        }
    }

    private void loadAuthorityData() {
        if (authorityRepository.count() == 0) {
            Authority learner = new Authority(
                    "ROLE_LEARNER", userRepository.findByUsername("learner1").get());
            authorityRepository.save(learner);
            Authority reviewer = new Authority(
                    "ROLE_REVIEWER", userRepository.findByUsername("reviewer1").get());
            authorityRepository.save(reviewer);
        }
    }
}
