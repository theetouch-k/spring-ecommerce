package com.example.urnshop.controller;

import com.example.urnshop.model.Product;
import com.example.urnshop.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody String addNewProduct (@RequestHeader("name") String productName,
                                            @RequestHeader("desc") String productDesc,
                                            @RequestHeader("price") float productPrice) {
        Product n = new Product();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        n.setProductName(productName);
        n.setProductDesc(productDesc);
        n.setPrice(productPrice);
        n.setCreatedAt(timestamp);
        productRepository.save(n);
        return "Product Saved";
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public @ResponseBody Iterable<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // @GetMapping(path = "/{productID}")
    // @PreAuthorize("hasAuthority('product:read')")
    // public Product getProduct(@PathVariable("productID") Integer productID) {
    //     return PRODUCTS.stream()
    //             .filter(product -> productID.equals(productID))
    //             .findFirst()
    //             .orElseThrow(() -> new IllegalStateException("Product" + productID + " doesn't exist"));
    // }

    // //hasRole('ROLE_') hasAnyRole('ROLE_', ...) hasAuthority('permission') hasAnyAuthority('permission')

    // @GetMapping
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // public List<Product> getAllProducts() {
    //     return PRODUCTS;
    // }

    // @PostMapping
    // @PreAuthorize("hasAuthority('product:write')")
    // public void registerNewProduct(@RequestBody Product product) {
    //     System.out.println(product);
    // }

    // @DeleteMapping(path="{productId}")
    // @PreAuthorize("hasAuthority('product:write')")
    // public void deleteProduct(@PathVariable("productId") Integer productID) {
    //     System.out.println(productID);
    // }

    // @PutMapping(path="{productId}")
    // @PreAuthorize("hasAuthority('product:write')")
    // public void updateProduct(@PathVariable("productId") Integer productID, @RequestBody Product product) {
    //     System.out.println(String.format("%s %s", productID, product));
    // }
}
