package com.nova.customer_update.repository;

import com.nova.customer_update.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
extends JpaRepository<CustomerEntity, Integer>
{

}
