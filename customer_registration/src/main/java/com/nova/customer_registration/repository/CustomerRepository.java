package com.nova.customer_registration.repository;

import com.nova.customer_registration.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository
extends JpaRepository<CustomerEntity, Integer>
{

    @Query("SELECT c FROM CustomerEntity c where c.name ILIKE %:name%")
    Optional<CustomerEntity> findFirstByNameContainsCustom(
            @Param("name") String name
    );
}
