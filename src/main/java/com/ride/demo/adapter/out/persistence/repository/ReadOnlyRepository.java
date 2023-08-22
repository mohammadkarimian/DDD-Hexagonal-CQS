package com.ride.demo.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface ReadOnlyRepository<T, D extends Serializable> extends Repository<T, D>, JpaSpecificationExecutor<T> {

    T findById(D id);

    Iterable<T> findAll();
}