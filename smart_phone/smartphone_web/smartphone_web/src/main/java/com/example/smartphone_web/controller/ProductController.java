package com.example.smartphone_web.controller;

import com.example.smartphone_web.dto.respone.attribute.cam.CamRespone;
import com.example.smartphone_web.dto.respone.attribute.chip.ChipRespone;
import com.example.smartphone_web.dto.respone.attribute.os.OsRespone;
import com.example.smartphone_web.dto.respone.attribute.pin.PinRespone;
import com.example.smartphone_web.dto.respone.attribute.ram.RamRespone;
import com.example.smartphone_web.dto.respone.attribute.screen.ScreenReposne;
import com.example.smartphone_web.dto.respone.category.CategoryResponeDto;
import com.example.smartphone_web.dto.respone.color.ColorRespone;
import com.example.smartphone_web.dto.respone.product.ProductResponse;
import com.example.smartphone_web.dto.respone.rom.RomRespone;
import com.example.smartphone_web.dto.respone.supplier.SupplierResponseDTO;
import com.example.smartphone_web.entity.RomEntity;
import com.example.smartphone_web.service.*;
import com.example.smartphone_web.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProduct productService;

    private final ICategoryService categoryService;

    private final ISupplier supplierService;

    private final SessionUtil sessionUtil;

    private final IColor colorService;

    private final IScreen screenService;

    private final IOs osService;
//    private final ILoaiOsService loaiOsService;
    private final IRam ramService;

    private final IPin pinService;

    private final IChip chipService;

    private final ICam camService;

    private final IRom romService;


    @GetMapping()
    public String index(Model model) {

        List<CategoryResponeDto> categoryDTOList = categoryService.getAllCategory();
        List<SupplierResponseDTO> supplierResponseDTOS = supplierService.findAll();
        List<ProductResponse> productResponseList = productService.findAll();
        List<ColorRespone> colorRespones = colorService.findAll();
        List<ScreenReposne> screenReposnes = screenService.findAllScreen();
        List<OsRespone> osRespones = osService.findAll();
        List<PinRespone> pinRespones = pinService.findAll();
        List<RamRespone> ramRespones = ramService.findAll();
        List<CamRespone> camRespones = camService.findAll();
        List<ChipRespone> chipRespones = chipService.findAll();
        List<OsRespone> loaiosRespones = osService.findAll();
//        List<Loai_OsRespone> loaiosRespones = loaiOsService.findAll();

        List<RomRespone> romRespones = romService.findAll();
        model.addAttribute("listRom", romRespones);
        model.addAttribute("listChip", chipRespones);
        model.addAttribute("listCam", camRespones);
        model.addAttribute("listCategory", categoryDTOList);
        model.addAttribute("listSupplier", supplierResponseDTOS);
        model.addAttribute("listProduct", productResponseList);
        model.addAttribute("listColor", colorRespones);
        model.addAttribute("listScreen", screenReposnes);
        model.addAttribute("listOs", osRespones);
        model.addAttribute("listRam", ramRespones);
        model.addAttribute("listPin", pinRespones);
        model.addAttribute("listloaios",loaiosRespones);
        return "/views/product/product";
    }

}
