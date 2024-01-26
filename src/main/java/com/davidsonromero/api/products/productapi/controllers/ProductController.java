package com.davidsonromero.api.products.productapi.controllers;

import com.davidsonromero.api.products.productapi.models.Product;
import com.davidsonromero.api.products.productapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/api/products", produces={"application/json"})
@Tag(name = "open-api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/")
    @Operation(summary = "Returns all products", method = "GET", description = "Returns all products")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Retrieving Products :(\nPlease Try Again Later")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(path = "/find")
    @Operation(summary = "Returns a product by id", method = "GET", description = "Returns a product by id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Product Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Retrieving Product :(\nPlease Try Again Later")
    public ResponseEntity<Product> getProduct(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping(path = "/findPageable")
    @Operation(summary = "Returns a product by id", method = "GET", description = "Returns products by page. Each page has the size provided by the user")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Retrieving Products :(\nPlease Try Again Later")
    public ResponseEntity<Iterable<Product>> getAllProductsPaged(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getAllProductsPaged(page, size));
    }

    @PostMapping(path = "/create")
    @Operation(summary = "Creates a product", method = "POST", description = "Creates a product")
    @ApiResponse(responseCode = "201", description = "Product Created")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Creating Product :(\nPlease Try Again Later")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.created(null).body(productService.saveProduct(product));
    }

    @PutMapping(path = "/update")
    @Operation(summary = "Updates a product", method = "PUT", description = "Updates a product")
    @ApiResponse(responseCode = "200", description = "Product Updated")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Product Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Updating Product :(\nPlease Try Again Later")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @DeleteMapping(path = "/delete")
    @Operation(summary = "Deletes a product", method = "DELETE", description = "Deletes a product")
    @ApiResponse(responseCode = "200", description = "Product Deleted")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Product Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Deleting Product :(\nPlease Try Again Later")
    public ResponseEntity<Void> deleteProduct(@RequestParam(name = "id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
