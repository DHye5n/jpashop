package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity  //테이블과 맵핑
@Getter
@Setter
public class Member {
    @Id  //PK
    @GeneratedValue //자동1씩 증가
    @Column(name = "member_id")
    private Long id;


    private String name;

    @Embedded  //자료형을 직접 생성해서 맵핑
    private Address address;

    @OneToMany(mappedBy = "member")   //1:N 관계
    // order 객체에 있는 member 필드에 의해서 매핑되었다.
    // 연관관계의 비 주인임을 표시. 이로써 Member 객체는 Order와의 관계를 수정할 수 없게 됨
    // (테이블로 생각한다면, FK를 가지고 있는 테이블이 관계를 조정한다고 생각하면 됨.
    private List<Order> orders = new ArrayList<>();
}
