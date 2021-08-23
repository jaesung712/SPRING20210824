package com.koreait.day2.controller.api;

import com.koreait.day2.ifs.CrudInterface;
import com.koreait.day2.model.network.Header;
import com.koreait.day2.model.network.request.ItemApiRequest;
import com.koreait.day2.model.network.response.ItemApiResponse;
import com.koreait.day2.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemApiController implements CrudInterface<ItemApiRequest , ItemApiResponse> {
    private final ItemApiLogicService itemApiLogicService;


    /*{
            "transaction_time":"2021-08-23",
            "resultCode":"OK",
            "description":"OK",
            "data":{
                "name":"삼성플렉스",
                "status":"판매중",
                "title":"삼성 노트북",
                "content":"접혀요",
                "price":"2500000"

            }
        }*/
    @Override
    @PostMapping("") // http://127.0.0.1:9090/api/item (post)
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        System.out.println(request);
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // http://127.0.0.1:9090/api/item/6
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // http://127.0.0.1:9090/api/item/6
    public Header<ItemApiResponse> delete(@PathVariable Long id) {
        return itemApiLogicService.delete(id);
    }
}
