package com.c8n.enocachallenge.repository;

import com.c8n.enocachallenge.repository.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    List<CustomerOrder> findAllByCreateDateAfter(Date date);
}
