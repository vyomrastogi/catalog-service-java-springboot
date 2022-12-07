package com.sivalabs.bookstore.catalog.api;

import com.sivalabs.bookstore.catalog.domain.PagedResult;
import com.sivalabs.bookstore.catalog.domain.Product;
import com.sivalabs.bookstore.catalog.domain.ProductModel;
import com.sivalabs.bookstore.catalog.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public PagedResult<ProductModel> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<ProductModel> getProductByIsbn(@PathVariable String isbn) {
        return productService.getProductByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
