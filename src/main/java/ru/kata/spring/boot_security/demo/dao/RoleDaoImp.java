package ru.kata.spring.boot_security.demo.dao;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Repository("roleDaoImp")
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findRole(String role) {
        return entityManager.find(Role.class, role);
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role roleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("SELECT r FROM Role r",
                Role.class).getResultList();
    }


}