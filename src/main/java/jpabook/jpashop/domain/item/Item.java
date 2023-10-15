package jpabook.jpashop.domain.item;


import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
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
    private int stockQuantity; // 현재고수량

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    /*
    *   비즈니스 로직
    *
    *   stock 증가 => stockQuantity를 setter로 조절
    *   위처럼 하면 OOP(객체지향)적이지 못하다
    */

    // 재고 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }


    // 재고 감소
    public void removeStock(int quantity)  {
        int restStock = this.stockQuantity - quantity;  // restStock : 빠진 재고 수량
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 필요합니다.");
        }
        this.stockQuantity = restStock;
    }
}
