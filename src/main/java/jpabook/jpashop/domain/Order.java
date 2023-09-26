package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter@Setter
public class Order {

    @Id  //PK
    @GeneratedValue //자동증가
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //N:1
    @JoinColumn(name = "member_id")
    private Member member; //주문회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //cascade:영속성전이(상속)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //fetch = FetchType.LAZY : 지연로딩(선작업이필요한경우사용 orders테이블 먼저 조회)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송정보

    
    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING) //EnumType.STRING : 나열
    private OrderStatus status; //주문상태 [ORDER, CANCLE]


    //연관관계 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
