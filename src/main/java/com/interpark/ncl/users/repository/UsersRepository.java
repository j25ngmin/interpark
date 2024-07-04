package com.interpark.ncl.users.repository;

import com.interpark.ncl.users.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String>, QuerydslPredicateExecutor<UsersEntity>, UsersRepositoryCustom {


}
