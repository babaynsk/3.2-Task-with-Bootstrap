package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.dao.UserDAOlmpl;
import ru.kata.spring.boot_security.demo.model.User;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class MyUserServicelmpl implements MyUserService {
    private UserDAO userDAO;

    @Autowired
    public MyUserServicelmpl(UserDAO userDAO) {
        this.userDAO=userDAO;
    }

    @Transactional
    public List<User> findAll(){
        return userDAO.getAllUsers();
    }

    @Transactional
    public User findById(int id){
        return userDAO.getUserById(id);
    }

    @Transactional
    public void save(User user){
        userDAO.saveUser(user);
    }

    @Transactional
    public void deleteById(int id){
        userDAO.delete(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username);
    }
}
