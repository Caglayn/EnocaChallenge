package com.c8n.enocachallenge.repository;

import com.c8n.enocachallenge.repository.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
