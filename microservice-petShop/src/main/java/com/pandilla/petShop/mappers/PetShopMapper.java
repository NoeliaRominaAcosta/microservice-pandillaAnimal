package com.pandilla.petShop.mappers;

import com.pandilla.petShop.dto.ProductDTO;
import com.pandilla.petShop.dto.SaleDTO;
import com.pandilla.petShop.dto.SaleDetailDTO;
import com.pandilla.petShop.models.Product;
import com.pandilla.petShop.models.Sale;
import com.pandilla.petShop.models.SaleDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PetShopMapper {
    ProductDTO productToProductDTO(Product product);
    Product productDTOtoProduct(ProductDTO productDTO);
    void updateProductFromDTO(ProductDTO productDTO, @MappingTarget Product product);

    SaleDetailDTO saleDetailToDetailDTO(SaleDetail saleDetail);
    SaleDetail saleDetailDTOtoDetail(SaleDetailDTO saleDetailDTO);
    void updateSaleDetailFromDTO(SaleDetailDTO saleDetailDTO, @MappingTarget SaleDetail saleDetail);

    SaleDTO saleToSaleDTO(Sale sale);
    Sale saleDTOtoSale(SaleDTO saleDTO);
    void updateSaleFromDTO(SaleDTO saleDTO, @MappingTarget Sale sale);
}
