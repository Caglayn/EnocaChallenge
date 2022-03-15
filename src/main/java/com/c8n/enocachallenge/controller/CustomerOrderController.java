package com.c8n.enocachallenge.controller;

import static com.c8n.enocachallenge.constants.RestApiUrls.*;


import com.c8n.enocachallenge.dto.request.SaveOrderRequestDto;
import com.c8n.enocachallenge.dto.request.UpdateOrderRequestDto;
import com.c8n.enocachallenge.dto.response.CustomerDetailsWithOrdersResponseDto;
import com.c8n.enocachallenge.dto.response.OrderDetailResponseDto;
import com.c8n.enocachallenge.service.CustomerOrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(API + VERSION + ORDER)
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> saveOrder(@RequestBody SaveOrderRequestDto dto) {
        return ResponseEntity.ok(customerOrderService.saveDto(dto));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteOrder(@RequestParam long id) {
        customerOrderService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateOrder(@RequestBody UpdateOrderRequestDto dto) {
        return ResponseEntity.ok(customerOrderService.updateDto(dto));
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<OrderDetailResponseDto> findById(@RequestParam long id) {
        return ResponseEntity.ok(customerOrderService.findOrderAndReturnDetailDto(id));
    }

    @GetMapping(LIST)
    public ResponseEntity<List<OrderDetailResponseDto>> listAllOrders() {
        return ResponseEntity.ok(customerOrderService.listAllDto());
    }

    @GetMapping(FINDORDERAFTERDATE)
    @Operation(summary = "Enter date format as 'dd-MM-yyyy' for query")
    public ResponseEntity<List<OrderDetailResponseDto>> listOrdersAfterInputDate(@RequestParam String date){
        return ResponseEntity.ok(customerOrderService.listOrdersAfterDate(date));
    }

    @GetMapping(FINDCUSTOMERNAMELIKE)
    @Operation(summary = "Enter all or part of the customer name.")
    public ResponseEntity<List<CustomerDetailsWithOrdersResponseDto>> findCustomerNameLike(@RequestParam String customerName){
        return ResponseEntity.ok(customerOrderService.findCustomerNameLike(customerName));
    }
}
