package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: HolyEyE
 * Date: 2013. 12. 3. Time: 오후 9:43
 */
@Service
@Transactional
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public <S extends Item> S saveAndFlush(S var1) {
        return itemRepository.saveAndFlush(var1);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

//    public Item findOne(Long itemId) {
//        return itemRepository.findOne(itemId);
//    }

    public List<Item> saveItem(Book book) {
        return itemRepository.findAll();
    }
}
