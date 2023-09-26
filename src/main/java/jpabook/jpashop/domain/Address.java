package jpabook.jpashop.domain;

import lombok.Getter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Embeddable   //수정된 자료형을 쓰는
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //자바기본생성자 proteced로 선언하여 setter가 없어도 수정가능하게
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}


/*엔티티 설계시 주의점

    1.엔티티에는 가급적이면 Setter를 사용X
        - Setter를 사용하면
        - 수정될 수 있는 범위가 너무 많다
        - 유지보수가 어렵다
        - 리팩토링할때 Setter 제거
    2.

 */