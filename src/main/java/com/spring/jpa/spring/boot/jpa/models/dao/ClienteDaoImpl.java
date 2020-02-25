package com.spring.jpa.spring.boot.jpa.models.dao;

import com.spring.jpa.spring.boot.jpa.models.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import java.util.List;


@Repository
public class ClienteDaoImpl implements IClienteDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        if (cliente.getId() != null && cliente.getId() > 0) {
            entityManager.merge(cliente);
        } else {
            entityManager.persist(cliente);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) throws IllegalArgumentException {
        try {
            return entityManager.find(Cliente.class, id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getCause());
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) throws IllegalArgumentException, TransactionRequiredException {
        try {
            entityManager.remove(findOne(id));
        } catch (IllegalArgumentException e){
            System.out.println("CAUSA 1: ".concat(e.getMessage()));
        } catch (TransactionRequiredException e){
            System.out.println("CAUSA 2: ".concat(e.getMessage()));
        } catch (Exception e){
            System.out.println("CAUSA 3: ".concat(e.getMessage()));
        }
    }
}
