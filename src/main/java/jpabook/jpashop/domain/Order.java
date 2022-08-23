package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
//생성메서드가 있을때 사용
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    //FK (연관관계의 주인)
    @JoinColumn(name = "member_id")
    private Member member;

    //CascadeType.All 으로 인하여 각각 persist를해주는 것을 한줄로 간결하게 구현가능하다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // order를 저장할 때, delivery도 persist를 같이해준다.
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //FK (연관관계의 주인)
    @JoinColumn(name = "delevery_id")
    private Delivery delivery;

    // hibernate 지원해줌 (주문시간)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 (ORDER, CANCEL)

    //==연관관계 편의 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        //order생성
        Order order = new Order();
        //order세팅
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비지니스 로직 추가==//
    //주문 취소
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    //==조회 로직==//
    //전체 주문 가격 조회
    public int getTotalPrice(){
        // int totalPrice = orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
        // 같은 표현
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}