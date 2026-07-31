package com.example.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products") // Đoạn này RẤT QUAN TRỌNG, phải khớp với Order-Service
public class ProductController {

    // Điểm tiếp nhận request từ Order-Service
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        // Trong thực tế, bạn sẽ truy vấn Database ở đây.
        // Để test nhanh, chúng ta trả về một dữ liệu tĩnh:
        return new Product(id, "Iphone 17", 123456.0);
    }
}