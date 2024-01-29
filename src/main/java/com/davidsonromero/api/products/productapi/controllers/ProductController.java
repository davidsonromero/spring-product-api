package com.davidsonromero.api.products.productapi.controllers;

import com.davidsonromero.api.products.productapi.models.Product;
import com.davidsonromero.api.products.productapi.service.ProductService;
import com.davidsonromero.api.products.productapi.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/api/products", produces={"application/json"})
@Tag(name = "Product API", description = "CRUD for products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/")
    @Operation(summary = "Returns all products", method = "GET", description = "Returns all products.")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
    })
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Retrieving Products :(\nPlease Try Again Later")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(path = "/find")
    @Operation(summary = "Returns a product by id", method = "GET", description = "Returns a product by id.")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    @ApiResponse(responseCode = "404", description = "Product Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Retrieving Product :(\nPlease Try Again Later")
    public ResponseEntity<Product> getProduct(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping(path = "/findPageable")
    @Operation(summary = "Returns a product by id", method = "GET", description = "Returns products by page. Each page has the size provided by the user.")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
    })
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Retrieving Products :(\nPlease Try Again Later")
    public ResponseEntity<Iterable<Product>> getAllProductsPaged(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getAllProductsPaged(page, size));
    }

    @PostMapping(path = "/create")
    @Operation(summary = "Creates a product", method = "POST", description = "Creates a product. ID must be null.")
    @ApiResponse(responseCode = "201", description = "Product Created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Creating Product :(\nPlease Try Again Later")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDTO product) {
        return ResponseEntity.created(null).body(productService.createProduct(product));
    }

    @PutMapping(path = "/update")
    @Operation(summary = "Updates a product", method = "PUT", description = "Updates a product. ID must be provided.")
    @ApiResponse(responseCode = "200", description = "Product Updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Product Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Updating Product :(\nPlease Try Again Later")
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProductDTO product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping(path = "/delete")
    @Operation(summary = "Deletes a product", method = "DELETE", description = "Deletes a product. ID must be provided.")
    @ApiResponse(responseCode = "200", description = "Product Deleted")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Product Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error While Deleting Product :(\nPlease Try Again Later")
    public ResponseEntity<Void> deleteProduct(@RequestParam(name = "id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
