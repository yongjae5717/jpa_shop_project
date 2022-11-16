package jpabook.jpashop.web.orderSimpleApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jpabook.jpashop.web.orderSimpleApi.service.OrderSimpleService;
import jpabook.jpashop.common.response.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@Tag(name = "OrderSimpleApi")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderSimpleApiController {

    private final OrderSimpleService orderSimpleService;
    // N + 1 문제 발생
    @Operation(description = "간단한 주문 조회 v2 (N+1 문제 발생)")
    @GetMapping("/v2/simple-orders")
    public ResponseEntity<Result> ordersV2(){
        return ResponseEntity.ok(orderSimpleService.ordersV2());
    }

    @Operation(description = "간단한 주문 조회 v3 (N+1 문제 해결)")
    @GetMapping("/v3/simple-orders")
    public ResponseEntity<Result> ordersV3(){
        return ResponseEntity.ok(orderSimpleService.ordersV3());
    }

    @Operation(description = "간단한 주문 조회 v4")
    @GetMapping("/v4/simple-orders")
    public ResponseEntity<Result> ordersV4(){
        return ResponseEntity.ok(orderSimpleService.ordersV4());
    }

}