package com.practice1.conf;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.practice1.entities.Admin;
import com.practice1.entities.Role;
import com.practice1.entities.Customer;
import com.practice1.repository.UserRepository;
import com.practice1.repository.AdminRepository;
import com.practice1.repository.RoleRepository;
import java.util.ArrayList;
@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepo;
    
    @Autowired
    private AdminRepository user;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg) {
        Customer user = new Customer();
        List<Customer> lists = new ArrayList<>(); // Initialize the list

        lists.add(user);
    
        Role role1 = new Role(1, "ROLE_ADMIN", lists);
        roleRepo.save(role1);
        Role role2 = new Role(2, "ROLE_USER", lists);
        roleRepo.save(role2);





        

        // Member account
        if (userRepository.findByUsername("member") == null) {
        	user.setEmail("member@gmail.com");
        	user.setUsername("member");
        	user.setPassword(passwordEncoder.encode("123456"));
        	user.setVaitro(Arrays.asList(roleRepo.findByroleName("ROLE_USER")));
            userRepository.save(user);
        }

        if (userRepository.findByUsername("admin") == null) {
            user.setEmail("admin@gmail.com");
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setVaitro(Arrays.asList(roleRepo.findByroleName("ROLE_ADMIN")));
            userRepository.save(user);
        }
        

        
    }
}
