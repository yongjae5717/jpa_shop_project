package jpabook.jpashop.web.orderSimpleApi.service;

import jpabook.jpashop.web.orderSimpleApi.dto.OrderSimpleQueryDto;
import jpabook.jpashop.web.orderSimpleApi.dto.SimpleOrderDto;
import jpabook.jpashop.web.orderSimpleApi.repository.OrderSimpleQueryRepository;
import jpabook.jpashop.common.response.Result;
import jpabook.jpashop.domain.entity.Order;
import jpabook.jpashop.domain.repository.OrderRepository;
import jpabook.jpashop.domain.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class OrderSimpleService {
    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    public Result ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> collect = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    public Result ordersV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> collect = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    public Result ordersV4(){
        List<OrderSimpleQueryDto> orders = orderSimpleQueryRepository.findOrderDtos();

        return new Result(orders.size(), orders);
    }
}
