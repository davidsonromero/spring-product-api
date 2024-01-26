package com.davidsonromero.api.products.productapi.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends CrudRepository, PagingAndSortingRepository {
}
