package com.example.orderservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderService {

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public OrderService(DiscoveryClient discoveryClient, @Qualifier("loadBalancedRestClientBuilder") RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        this.restClient = restClientBuilder.build();
    }

    public Product getProductFromProductService(String productId){
        String targetUrl = "http://product-service/api/v1/products/" + productId;
        return restClient.get()
                .uri(targetUrl)
                .retrieve()
                .body(Product.class);
    }
}
