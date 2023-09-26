package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") //자식클래스
@Getter@Setter
public class Book extends Item{

    private String author; //저자
    private String isbn; //북 넘버
}
