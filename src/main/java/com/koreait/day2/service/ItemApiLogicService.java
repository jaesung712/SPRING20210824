package com.koreait.day2.service;

import com.koreait.day2.ifs.CrudInterface;
import com.koreait.day2.model.entity.Item;

import com.koreait.day2.model.enumclass.ItemStatus;
import com.koreait.day2.model.network.Header;
import com.koreait.day2.model.network.request.ItemApiRequest;
import com.koreait.day2.repository.ItemRepository;
import com.koreait.day2.model.network.response.ItemApiResponse;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //서비스로직어노테이션-> 서비스 레이어 , 내부에서 자바 로직을 처리함
@RequiredArgsConstructor
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {
    private final ItemRepository itemRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        ItemApiRequest itemApiRequest =request.getData();

        Item item = Item.builder()
                .id(itemApiRequest.getId())
                .name(itemApiRequest.getName())
                .status(ItemStatus.REGISTERD)
                .title(itemApiRequest.getTitle())
                .content(itemApiRequest.getContent())
                .price(itemApiRequest.getPrice())
                .regDate(itemApiRequest.getRegDate())
                .createBy(itemApiRequest.getCreateBy())
                .updateDate(itemApiRequest.getUpdateDate())
                .updateBy(itemApiRequest.getUpdateBy())
                .build();
        Item newItem = itemRepository.save(item);
        return response(newItem);


    }



    @Override
    public Header<ItemApiResponse> read(Long id) {

        return itemRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        ItemApiRequest itemApiRequest = request.getData();
        Optional<Item> optional = itemRepository.findById(itemApiRequest.getId());
        return optional.map(item -> {
                    item.setId(itemApiRequest.getId());
                    item.setName(itemApiRequest.getName());
                    item.setTitle(itemApiRequest.getTitle());
                    item.setContent(itemApiRequest.getContent());
                    item.setPrice(itemApiRequest.getPrice());
                    return item;
                }).map(item -> itemRepository.save(item))
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        Optional<Item> optional = itemRepository.findById(id);

        return optional.map(item -> {
            itemRepository.delete(item);
            return  Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    private Header<ItemApiResponse> response(Item item) {
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .status(item.getStatus())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .regDate(item.getRegDate())
                .createBy(item.getCreateBy())
                .updateDate(item.getUpdateDate())
                .updateBy(item.getUpdateBy())
                .build();
        return Header.OK(itemApiResponse);
    }
}
