package jpabook.jpashop.web.orderSimpleApi.dto;

import jpabook.jpashop.domain.entity.Address;
import jpabook.jpashop.domain.entity.Order;
import jpabook.jpashop.domain.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();  // Lazy 초기화
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();  // Lazy 초기화
    }
}
