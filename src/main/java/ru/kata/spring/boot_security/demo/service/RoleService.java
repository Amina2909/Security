package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    @Transactional(readOnly = true)
    List<Role> getAllRoles();
}
