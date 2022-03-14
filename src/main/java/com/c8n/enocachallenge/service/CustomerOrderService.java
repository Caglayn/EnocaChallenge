package com.c8n.enocachallenge.service;

import com.c8n.enocachallenge.dto.request.SaveOrderRequestDto;
import com.c8n.enocachallenge.dto.request.UpdateOrderRequestDto;
import com.c8n.enocachallenge.dto.response.CustomerDetailsWithOrdersResponseDto;
import com.c8n.enocachallenge.dto.response.OrderDetailResponseDto;
import com.c8n.enocachallenge.exception.OrderNotFoundException;
import com.c8n.enocachallenge.repository.CustomerOrderRepository;
import com.c8n.enocachallenge.repository.entity.Customer;
import com.c8n.enocachallenge.repository.entity.CustomerOrder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {
    private final CustomerOrderRepository orderRepository;
    private final CustomerService customerService;

    public CustomerOrderService(CustomerOrderRepository orderRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    public CustomerOrder save(CustomerOrder order){
        return orderRepository.save(order);
    }

    public CustomerOrder findById(long id){
        Optional<CustomerOrder> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()){
            return orderOptional.get();
        }else
            throw new OrderNotFoundException("Order Not Found");
    }

    public List<CustomerOrder> findAll(){
        return orderRepository.findAll();
    }

    public void delete(long id){
        orderRepository.deleteById(id);
    }

    public boolean saveDto(SaveOrderRequestDto dto){
        Customer customer = customerService.findById(dto.getCustomerId());
        CustomerOrder order = CustomerOrder.builder()
                .totalPrice(dto.getTotalPrice())
                .createDate(new Date())
                .customer(customer)
                .build();

        if (this.save(order)!=null)
            return true;
        else
            return false;
    }

    public boolean updateDto(UpdateOrderRequestDto dto){
        Customer customer = customerService.findById(dto.getCustomerId());
        CustomerOrder order = CustomerOrder.builder()
                .id(dto.getId())
                .totalPrice(dto.getTotalPrice())
                .customer(customer)
                .build();

        if (this.save(order)!=null)
            return true;
        else
            return false;
    }

    public OrderDetailResponseDto findOrderAndReturnDetailDto(long id){
        CustomerOrder order = this.findById(id);
        return OrderDetailResponseDto.builder()
                .id(order.getId())
                .createDate(order.getCreateDate())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public List<OrderDetailResponseDto> listAllDto(){
        List<CustomerOrder> orders = this.findAll();
        List<OrderDetailResponseDto> orderDtos = new ArrayList<>();
        for(CustomerOrder order:orders){
            orderDtos.add(OrderDetailResponseDto.builder()
                    .id(order.getId())
                    .createDate(order.getCreateDate())
                    .totalPrice(order.getTotalPrice())
                    .build());
        }

        return orderDtos;
    }

    public List<OrderDetailResponseDto> listOrdersAfterDate(String dateString) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date;

        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }

        List<CustomerOrder> orders = orderRepository.findAllByCreateDateAfter(date);
        List<OrderDetailResponseDto> orderDtos = new ArrayList<>();

        for (CustomerOrder order:orders){
            orderDtos.add(OrderDetailResponseDto.builder()
                    .id(order.getId())
                    .createDate(order.getCreateDate())
                    .totalPrice(order.getTotalPrice())
                    .build());
        }

        return orderDtos;
    }

    public List<CustomerDetailsWithOrdersResponseDto> findCustomerNameLike(String customerName){
        List<Customer> customers = customerService.findAllByNameLike(customerName);
        List<CustomerDetailsWithOrdersResponseDto> dtoList = new ArrayList<>();

        for (Customer customer:customers){
            List<OrderDetailResponseDto> orderDtos = new ArrayList<>();
            if (customer.getCustomerOrders().size()>0){
                for (CustomerOrder order:customer.getCustomerOrders()){
                    orderDtos.add(OrderDetailResponseDto.builder()
                            .id(order.getId())
                            .createDate(order.getCreateDate())
                            .totalPrice(order.getTotalPrice())
                            .build());
                }
            }
            dtoList.add(CustomerDetailsWithOrdersResponseDto.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .age(customer.getAge())
                    .orders(orderDtos)
                    .build());
        }

        return dtoList;
    }
}
