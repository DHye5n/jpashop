package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

// 회원등록 폼 객체
@Getter @Setter
public class MemberForm { //DTO DAO와 같다
    
    @NotEmpty(message = "회원이름은 필수입니다.")
    private String name;
    
    private String city;
    private String street;
    private String zipcode;
}
