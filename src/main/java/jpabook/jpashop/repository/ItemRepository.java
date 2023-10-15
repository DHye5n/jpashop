package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 저장소
@RequiredArgsConstructor // 생성자 주입
public class ItemRepository {

    @PersistenceContext // 영속성
    private final EntityManager em;


    // 값이 없으면 create , 있으면 update
    public void save(Item item) {
        if(item.getId() == null) {
            // 영속성 컨텍스트에 저장
            em.persist(item);
        } else {
            // 준영속 상태의 entity를 영속 상태로 변경
            em.merge(item);
        }
    }


    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }


    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
