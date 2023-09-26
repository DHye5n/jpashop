package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속
@DiscriminatorColumn(name = "dtype") //부모클래스에 선언 key : dtype(default)
@Getter@Setter
public abstract class Item { //참고만 할 뿐 실제하지 않는다

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity; //재고

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();
}
