package com.interpark.ncl.users.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UsersRepositoryImpl extends QuerydslRepositorySupport implements UsersRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public UsersRepositoryImpl() {
        super(UsersRepositoryImpl.class);
    }

    @Override
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

}
