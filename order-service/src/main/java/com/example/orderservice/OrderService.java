package com.example.orderservice;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public OrderService(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder){
        this.discoveryClient = discoveryClient;
        this.restClient = restClientBuilder.build();
    }

    public Product getProductFromProductService(String productId){
        //buoc A: hoi eureka lay danh sach cac may chu dg chay ProducService
        List<ServiceInstance> instances = discoveryClient.getInstances("product-service");
        // buoc B: Xu li loi
        if(instances == null || instances.isEmpty()){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"Product Service hien o kha dung");
        }
        //buoc C:Lay instance dau tien trong danh sach
        ServiceInstance productInstance = instances.get(0);
        //buoc D:Lay URL goc tu instance
        String baseUrl = productInstance.getUri().toString();
        //buoc E:Noi chuoi de tao ra API URL hoan chinh
        String tagertUrl = baseUrl + "/api/v1/products "+ productId;
        //buoc F: thuc hien goi APi lay san pham va duc vao khuan
        return restClient.get()
                .uri(tagertUrl)
                .retrieve()
                .body(Product.class);
    }
}
