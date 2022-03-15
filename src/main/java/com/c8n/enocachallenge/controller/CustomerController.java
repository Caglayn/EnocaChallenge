package com.c8n.enocachallenge.controller;

import static com.c8n.enocachallenge.constants.RestApiUrls.*;

import com.c8n.enocachallenge.dto.request.SaveCustomerRequestDto;
import com.c8n.enocachallenge.dto.request.UpdateCustomerRequestDto;
import com.c8n.enocachallenge.dto.response.CustomerDetailResponseDto;
import com.c8n.enocachallenge.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(API+VERSION+CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> saveCustomer(@RequestBody SaveCustomerRequestDto dto){
        return ResponseEntity.ok(customerService.saveDto(dto));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteCustomer(@RequestParam long id){
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateCustomer(@RequestBody UpdateCustomerRequestDto dto){
        return ResponseEntity.ok(customerService.updateDto(dto));
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<CustomerDetailResponseDto> findById(@RequestParam long id){
        return ResponseEntity.ok(customerService.findCustomerAndReturnDetailDto(id));
    }

    @GetMapping(LIST)
    public ResponseEntity<List<CustomerDetailResponseDto>> listAllCustomers(){
        return ResponseEntity.ok(customerService.listAllDto());
    }

    @GetMapping(FINDCUSTOMERWITHOUTORDER)
    @Operation(summary = "List all customers who have no order")
    public ResponseEntity<List<CustomerDetailResponseDto>> listCustomersWithoutOrder(){
        return ResponseEntity.ok(customerService.customerWithoutOrder());
    }
}
