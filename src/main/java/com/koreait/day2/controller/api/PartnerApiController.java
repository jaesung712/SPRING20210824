package com.koreait.day2.controller.api;

import com.koreait.day2.ifs.CrudInterface;
import com.koreait.day2.model.network.Header;
import com.koreait.day2.model.network.request.PartnerApiRequest;
import com.koreait.day2.model.network.response.PartnerApiResponse;
import com.koreait.day2.service.PartnerApiLogicService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Partner")
@RequiredArgsConstructor
public class PartnerApiController implements CrudInterface<PartnerApiRequest ,PartnerApiResponse> {
    private final PartnerApiLogicService partnerApiLogicService;

    @Override
    @PostMapping("")
    public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerApiResponse> read(@PathVariable(name="id") Long id) {
        return partnerApiLogicService.read(id);
    }

    @Override
    @PutMapping // http://127.0.0.1:9090/api/Partner
    public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiLogicService.update(request);
    }
    @Override
    @DeleteMapping("{id}") // http://127.0.0.1:9090/api/Partner/3
    public Header<PartnerApiResponse> delete(@PathVariable(name = "id") Long id) {
        return partnerApiLogicService.delete(id);
    }
}
