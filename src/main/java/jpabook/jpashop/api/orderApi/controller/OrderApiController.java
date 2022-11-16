package jpabook.jpashop.api.orderApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jpabook.jpashop.api.orderApi.service.OrderQueryService;
import jpabook.jpashop.common.Result;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "OrderApi")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderApiController {

    private final OrderQueryService orderQueryService;
    @Operation(description = "주문 내역 조회 v2 (엔티티를 DTO 변환)")
    @GetMapping("/v2/orders")
    public Result ordersV2(){
        return orderQueryService.ordersV2();
    }

    @Operation(description = "주문 내역 조회 v3 (패치조인으로 최적화)")
    @GetMapping("/v3/orders")
    public Result ordersV3(){
        return orderQueryService.ordersV3();
    }

    /**
     *
     * 엔티티를 조회해서 DTO로 변환 페이징 고려
     * xToOne관계만 우선 모두 페치 조인으로 최적화
     * 컬렉션 관계는 hibernate.default_batch_fetch_size, @BatchSize로 최적화
     * default_batch_fetch_size의 크기는 적당한 사이즈를 골라야하는데 100~1000 사이를 선택하는 것을 권장한다.
     * 1000으로 설정하는 것이 성능상 가장 좋지만, 결국 DB든 애플리케이션이든 순간 부하를 어디까지 견딜 수 있는지 결정하면 된다.
     */
    @Operation(description = "주문 내역 조회 v3.1 (페이징문제 해결) -> default_batch_fetch_size 설정")
    @GetMapping("/v3.1/orders")
    public Result ordersV3Page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit){
        return orderQueryService.ordersV3Page(offset, limit);
    }

    @Operation(description = "주문 내역 조회 v4 (JPA에서 DTO 직접 조회) -> 컬렉션 조회 시 N+1 문제 발생")
    @GetMapping("/v4/orders")
    public Result ordersV4(){
        return orderQueryService.ordersV4();
    }

    @Operation(description = "주문 내역 조회 v5 (컬렉션 조회 최적화) -> N+1 문제 해결")
    @GetMapping("/v5/orders")
    public Result ordersV5(){
        return orderQueryService.ordersV5();
    }

    @Operation(description = "주문 내역 조회 v6 (플랫 데이터 최적화)")
    @GetMapping("/v6/orders")
    public Result ordersV6(){
        return orderQueryService.ordersV6();
    }
}