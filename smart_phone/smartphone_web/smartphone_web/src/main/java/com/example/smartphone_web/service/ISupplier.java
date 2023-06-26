package com.example.smartphone_web.service;

import com.example.smartphone_web.dto.request.supplier.EditSupplierDto;
import com.example.smartphone_web.dto.request.supplier.SupplierRequestDTO;
import com.example.smartphone_web.dto.respone.supplier.SupplierResponseDTO;

import java.util.List;

public interface ISupplier {

    List<SupplierResponseDTO> findAllByDeleteFlagFalse();

    List<SupplierResponseDTO> findAll();


    Integer findByEmail(String email);
    Integer findByPhone(String phone);

    SupplierResponseDTO findById(Long id);


    SupplierRequestDTO addSupplier(SupplierRequestDTO requestDTO);

    EditSupplierDto updateSupplier(EditSupplierDto requestDTO);

    boolean deleteSupplier(Long id);

    boolean changeStatus(Long id, String status);




}
