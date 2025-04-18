package com.pandilla.petShop.repository;

import com.pandilla.petShop.models.Product;
import com.pandilla.petShop.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
}
