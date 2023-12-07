package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;

@Service
public class RoleServicelmpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServicelmpl(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
