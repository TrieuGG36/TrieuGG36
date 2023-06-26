package com.example.smartphones.service.impl;

import com.example.smartphones.dto.respone.product.NewProductRespone;
import com.example.smartphones.dto.respone.product.ProductRespone;
import com.example.smartphones.entity.CategoryEntity;
import com.example.smartphones.entity.ProductEntity;
import com.example.smartphones.entity.ProductPropertyEntity;
import com.example.smartphones.repository.CategoryRepo;
import com.example.smartphones.repository.ProductPropertyRepo;
import com.example.smartphones.repository.ProductRepo;
import com.example.smartphones.service.IProduct;
import com.example.smartphones.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService implements IProduct {

    private final ConvertUtil convertUtil;
    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;

    private final ImageServiceImpl imageService;

    private final RomService romService;

    private final AttributeService attributeService;

    private final ProductPropertyRepo productPropertyRepo;
    @Override
    public List<NewProductRespone> findAll(Pageable pageable) {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByRandomTop20(pageable);
        int i = 0;
        for (ProductEntity product : list) {
            if (i < 10){
                NewProductRespone productRespone = new NewProductRespone();
                productRespone.setId(String.valueOf(product.getId()));
                productRespone.setName(product.getName());
                productRespone.setNote(product.getNote());
                productRespone.setImageKey(product.getImage_key());
                productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
                productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
                productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
                if (productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                    if (productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                        listPro.add(productRespone);
                    }
                }
                i++;
            }
        }
        return listPro;
    }

    @Override
    public ProductRespone findById(Long id) {
        Optional<ProductEntity> productEntity = productRepo.findByIdProduct(id);
        if (productEntity.isPresent()) {
            ProductRespone respone = mapToEntity(productEntity.get());
            respone.setSrcImage(imageService.getAllImageByProduct(respone.getId())); // set list image cho product
            respone.setRomRespones(romService.findByProductId(Long.valueOf(respone.getId()))); // set list Rom cho product
            respone.setAttributeRespone(attributeService.findByProduct(productEntity.get().getId()));

            for (int i = 0; i < respone.getRomRespones().size(); i++) {
                if(respone.getRomRespones().get(i).getProductPropertyRespones().size() == 0){
                    respone.getRomRespones().remove(i);
                }
            }
            return respone;
        }
        log.error("Không tìm thấy product");
        return null;
    }


    @Override
    public List<NewProductRespone> findByCateId(Long id) {
        List<NewProductRespone> listPro = new ArrayList<>();
        Optional<CategoryEntity> categoryEntity = categoryRepo.findById(id);
        List<ProductEntity> list = productRepo.findByCategoryId(categoryEntity.get().getId());
        for (ProductEntity product : list) {
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);
                }
            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByIphone() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByCategoryId(1L);
        for (ProductEntity product : list) {
            //boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);

                }

            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findBySamSung() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByCategoryId(2L);
        for (ProductEntity product : list) {
            //boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);

                }

            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByXiaomi() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByCategoryId(4L);
        for (ProductEntity product : list) {
            //boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);

                }

            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByOppo() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByCategoryId(5L);
        for (ProductEntity product : list) {
            //boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);

                }

            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByNokia() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByCategoryId(3L);
        for (ProductEntity product : list) {
            //boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);

                }

            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findbyTop10() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByDeleteFlagIsFalseAndStatus();
        int i = 0;
        for (ProductEntity product : list) {
            //boolean check = true;
            if(i < 10){
                NewProductRespone productRespone = new NewProductRespone();
                productRespone.setId(String.valueOf(product.getId()));
                productRespone.setName(product.getName());
                productRespone.setNote(product.getNote());
                productRespone.setImageKey(product.getImage_key());
                productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
                productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
                productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
                if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                    if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                        listPro.add(productRespone);

                    }

                }
                i++;
            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByName(String name) {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByName("%" + name + "%");
        for (ProductEntity product : list) {
            boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            int size = productRespone.getRomRespones().size();
            for (int i = 0; i < size; i++) {
                List<ProductPropertyEntity> productPropertyEntities = productPropertyRepo.findByRomId(Long.parseLong(productRespone.getRomRespones().get(i).getId()));
                if(productPropertyEntities == null || productPropertyEntities.size() == 0){
                    check = false;
                    productRespone.getRomRespones().remove(i);
                    size--;
                    continue;
                }else {
                    for (ProductPropertyEntity listProductProperty : productPropertyEntities) {
                        productRespone.setPrice(convertUtil.moneyToStringFormat(listProductProperty.getPrice()));
                        productRespone.setPricePromotion(listProductProperty.getPricePromotion());
                    }
                }
            }
            if(check){
                listPro.add(productRespone);
            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findbyRandom() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByRandom();
        int i = 0;
        for (ProductEntity product : list) {
            //boolean check = true;
            if(i < 10){
                NewProductRespone productRespone = new NewProductRespone();
                productRespone.setId(String.valueOf(product.getId()));
                productRespone.setName(product.getName());
                productRespone.setNote(product.getNote());
                productRespone.setImageKey(product.getImage_key());
                productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
                productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
                productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
                if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                    if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                        listPro.add(productRespone);
                    }
                }
                i++;
            }
        }
        return listPro;
    }
    private ProductRespone mapToEntity(ProductEntity entity) {
        ProductRespone respone = new ProductRespone();
        respone.setId(String.valueOf(entity.getId()));
        respone.setName(entity.getName());
        respone.setNote(entity.getNote());
        respone.setImageKey(entity.getImage_key());
        return respone;
    }
}
