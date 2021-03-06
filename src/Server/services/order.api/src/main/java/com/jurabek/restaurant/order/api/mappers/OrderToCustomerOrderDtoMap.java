package com.jurabek.restaurant.order.api.mappers;

import java.util.stream.Collectors;

import com.jurabek.restaurant.order.api.dtos.CustomerOrderDto;
import com.jurabek.restaurant.order.api.dtos.CustomerOrderItemsDto;
import com.jurabek.restaurant.order.api.models.Order;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * OrderToOrdetDtoMap
 */
public class OrderToCustomerOrderDtoMap extends PropertyMap<Order, CustomerOrderDto> {

    private ModelMapper modelMapper;

    public OrderToCustomerOrderDtoMap(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    protected void configure() {
        using(ctx -> {
            Order order = (Order) ctx.getSource();

            return order.getOrderItems().stream()
                    .map(orderItem -> modelMapper.map(orderItem, CustomerOrderItemsDto.class)).collect(Collectors.toList());
        }).map(source, destination.getOrderItems());
    }
}