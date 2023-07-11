package com.janak.orderService.service;

import com.janak.orderService.dto.OrderLineItemsDTO;
import com.janak.orderService.dto.OrderRequest;
import com.janak.orderService.model.Order;
import com.janak.orderService.model.OrderLineItems;
import com.janak.orderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderItems = orderRequest.getOrderLineItemsDTO().stream()
                .map(this::mapToDTO)
                .toList();
        order.setOrderLineItems(orderItems);
        orderRepository.save(order);

    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        return orderLineItems;
    }
}
