package com.c8n.enocachallenge.service;

import com.c8n.enocachallenge.dto.request.SaveCustomerRequestDto;
import com.c8n.enocachallenge.dto.request.UpdateCustomerRequestDto;
import com.c8n.enocachallenge.dto.response.CustomerDetailResponseDto;
import com.c8n.enocachallenge.exception.CustomerNotFoundException;
import com.c8n.enocachallenge.repository.CustomerRepository;
import com.c8n.enocachallenge.repository.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer findById(long id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()){
            return customerOptional.get();
        }else
            throw new CustomerNotFoundException("Customer Not Found");
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public List<Customer> findAllByNameLike(String name){
        return customerRepository.findByNameContainingIgnoreCase(name);
    }

    public void delete(long id){
        customerRepository.deleteById(id);
    }

    public CustomerDetailResponseDto findCustomerAndReturnDetailDto(long id){
        Customer customer = this.findById(id);
        return CustomerDetailResponseDto.builder()
                .id(customer.getId())
                .age(customer.getAge())
                .name(customer.getName())
                .build();
    }

    public boolean saveDto(SaveCustomerRequestDto dto){
        Customer customer = Customer.builder().name(dto.getName()).age(dto.getAge()).build();
        if(save(customer)!=null)
            return true;
        else
            return false;
    }

    public boolean updateDto(UpdateCustomerRequestDto dto){
        Customer customer = Customer.builder().id(dto.getId()).name(dto.getName()).age(dto.getAge()).build();
        if(save(customer)!=null)
            return true;
        else
            return false;
    }

    public List<CustomerDetailResponseDto> listAllDto(){
        List<Customer> customers = this.findAll();
        List<CustomerDetailResponseDto> customerDtos = new ArrayList<>();
        for(Customer customer:customers){
            customerDtos.add(CustomerDetailResponseDto.builder()
                            .id(customer.getId())
                            .age(customer.getAge())
                            .name(customer.getName())
                            .build());
        }
        return customerDtos;
    }

    public List<CustomerDetailResponseDto> customerWithoutOrder(){
        List<Customer> customers = this.findAll();
        List<CustomerDetailResponseDto> resultList = new ArrayList<>();
        for (Customer customer:customers){
            if (customer.getCustomerOrders()==null || customer.getCustomerOrders().size()<1){
                resultList.add(CustomerDetailResponseDto.builder()
                        .id(customer.getId())
                        .age(customer.getAge())
                        .name(customer.getName())
                        .build());
            }
        }
        return resultList;
    }
}
