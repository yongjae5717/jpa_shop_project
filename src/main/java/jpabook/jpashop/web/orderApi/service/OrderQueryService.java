package jpabook.jpashop.web.orderApi.service;

import jpabook.jpashop.web.orderApi.dto.OrderDto;
import jpabook.jpashop.web.orderApi.dto.OrderQueryDto;
import jpabook.jpashop.web.orderApi.repository.OrderQueryRepository;
import jpabook.jpashop.common.response.Result;
import jpabook.jpashop.domain.entity.Order;
import jpabook.jpashop.domain.repository.OrderRepository;
import jpabook.jpashop.domain.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    public Result ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> collect = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    public Result ordersV3(){
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> collect = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    public Result ordersV3Page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit){
        List<Order> orders = orderRepository.findAllWithMemberDeliveryV2(offset, limit);

        List<OrderDto> collect = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    public Result ordersV4(){
        List<OrderQueryDto> orderQueryDtos = orderQueryRepository.findOrderQueryDtos();
        return new Result(orderQueryDtos.size(), orderQueryDtos);
    }

    public Result ordersV5(){
        List<OrderQueryDto> allByDtoOptimization = orderQueryRepository.findAllByDtoOptimization();
        return new Result(allByDtoOptimization.size(), allByDtoOptimization);
    }

    public Result ordersV6(){
        List<OrderQueryDto> flats = orderQueryRepository.findFlat();
        return new Result(flats.size(), flats);
    }

}
