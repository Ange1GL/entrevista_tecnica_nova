package com.nova.customer_registration.repository;

import com.nova.customer_registration.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
extends JpaRepository<CustomerEntity, Integer>
{

}
