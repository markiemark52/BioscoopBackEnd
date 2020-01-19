package com.maahi.bioscoop.controllers;

import com.maahi.bioscoop.HibernateUtil;
import com.maahi.bioscoop.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController() { }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return ResponseEntity.ok(user);
        } catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User loginUser;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            loginUser = session.createQuery("from User u where u.email = :email", User.class)
                    .setParameter("email", user.getEmail())
                    .getSingleResult();

            if (passwordEncoder.matches(user.getPassword(), loginUser.getPassword()))
                return ResponseEntity.ok(loginUser);
            else
                return ResponseEntity.notFound().build();
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/logout")
    public boolean logout(@RequestBody String token) {
        return true;
    }
}
