package com.koreait.day2.controller.api;

import com.koreait.day2.ifs.CrudInterface;
import com.koreait.day2.model.network.Header;
import com.koreait.day2.model.network.request.OrderGroupApiRequest;
import com.koreait.day2.model.network.response.OrderGroupApiResponse;
import com.koreait.day2.service.OrderGroupApiLogicService;
import com.koreait.day2.repository.OrderGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordergroup")
@RequiredArgsConstructor
public class OrderGroupApiContoller implements CrudInterface <OrderGroupApiRequest, OrderGroupApiResponse> {
    private final OrderGroupApiLogicService orderGroupApiLogicService;
    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.create(request);
    }


    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable(name="id") Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // http://127.0.0.1:9090/api/item/6
    public Header<OrderGroupApiResponse> delete(@PathVariable Long id) {
        return orderGroupApiLogicService.delete(id);
    }
}
