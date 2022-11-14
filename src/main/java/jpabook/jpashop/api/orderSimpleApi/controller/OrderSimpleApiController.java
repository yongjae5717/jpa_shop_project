package jpabook.jpashop.api.orderSimpleApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import jpabook.jpashop.api.orderSimpleApi.dto.OrderSimpleQueryDto;
import jpabook.jpashop.api.orderSimpleApi.dto.SimpleOrderDto;
import jpabook.jpashop.api.orderSimpleApi.repository.OrderSimpleQueryRepository;
import jpabook.jpashop.common.Result;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    // N + 1 문제 발생
    @Operation(description = "간단한 주문 조회 v2 (N+1 문제 발생)")
    @GetMapping("/v2/simple-orders")
    public Result ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> collect = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Operation(description = "간단한 주문 조회 v3 (N+1 문제 해결)")
    @GetMapping("/v3/simple-orders")
    public Result ordersV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> collect = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Operation(description = "간단한 주문 조회 v4")
    @GetMapping("/v4/simple-orders")
    public Result ordersV4(){
        List<OrderSimpleQueryDto> orders = orderSimpleQueryRepository.findOrderDtos();

        return new Result(orders.size(), orders);
    }

}