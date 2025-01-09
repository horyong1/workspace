package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
    // 동시성 이슈가 고려되어 있지 않음
    // 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
    private static final Map<Long, Item> store = new HashMap<>(); // static 사용
    private static long sequence = 0L; // static 사용
    

    // 상품 저장
    public Item save (Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    
    // 상품 찾기
    // null이 반환될 가능성이 있으므로 Optional로 감싸서 반환하는 것이 좋음
    public Item findById(Long id) {
        return store.get(id); 
    }

    // 상품 전체 찾기
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 상품 수정
    // 정석으로 하려면 updateParam을 다른 객체로 받는 것이 좋음
    // 프로젝트 규모가 작다면 이렇게 해도 무방
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    // 상품 삭제
    public void clearStore() {
        store.clear();
    }
}
