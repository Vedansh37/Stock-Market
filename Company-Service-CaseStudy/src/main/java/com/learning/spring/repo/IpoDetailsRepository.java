package com.learning.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.spring.model.IpoDetail;

@Repository
public interface IpoDetailsRepository extends JpaRepository<IpoDetail, Long>{

}
