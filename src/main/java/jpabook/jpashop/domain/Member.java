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
    private List<Order> orders = new ArrayList<>();
}
