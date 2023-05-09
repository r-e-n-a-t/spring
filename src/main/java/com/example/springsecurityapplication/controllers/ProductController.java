package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.repositories.ProductRepository;
import com.example.springsecurityapplication.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private  final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("")
    public String getAllProduct(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "/product/product";
    }

    @GetMapping("/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "/product/infoProduct";
    }

    @PostMapping("/search")
    public String productSearch(@RequestParam("search") String search, @RequestParam("ot") String ot, @RequestParam("do") String Do, @RequestParam(value = "price", required = false, defaultValue = "") String price, @RequestParam(value = "contract", required = false, defaultValue = "")String contract, Model model){
        model.addAttribute("products", productService.getAllProduct());
        if(!search.isEmpty() && (!ot.isEmpty() && !Do.isEmpty()) && !price.isEmpty() && !contract.isEmpty()){
            if(price.equals("sorted_by_ascending_price")) {
                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), getContractId(contract)));
            } else{
                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), getContractId(contract)));
            }
        }else if(!search.isEmpty() && (!ot.isEmpty() && !Do.isEmpty()) && !contract.isEmpty()){
                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), getContractId(contract)));
        }else if (!search.isEmpty() && (!ot.isEmpty() && !Do.isEmpty()) && !price.isEmpty()){
            if(price.equals("sorted_by_ascending_price")) {
                model.addAttribute("search_product", productRepository.findByTitleOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
            }else{
                model.addAttribute("search_product", productRepository.findByTitleOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
            }

        }else if (!search.isEmpty() && !contract.isEmpty() && !price.isEmpty()){
            if(price.equals("sorted_by_ascending_price")) {
                model.addAttribute("search_product", productRepository.findByTitleAndCategoryAsc(search.toLowerCase(), getContractId(contract)));
            } else{
                model.addAttribute("search_product", productRepository.findByTitleAndCategoryDesc(search.toLowerCase(), getContractId(contract)));
            }
        }else if ((!ot.isEmpty() && !Do.isEmpty()) && !contract.isEmpty() && !price.isEmpty()){
            if(price.equals("sorted_by_ascending_price")) {
                model.addAttribute("search_product", productRepository.findByCategoryOrderByPriceAsc(Float.parseFloat(ot), Float.parseFloat(Do), getContractId(contract)));
            } else {
                model.addAttribute("search_product", productRepository.findByCategoryOrderByPriceDesc(Float.parseFloat(ot), Float.parseFloat(Do), getContractId(contract)));
            }
        }else if (!search.isEmpty() && (!ot.isEmpty() && !Do.isEmpty())){
            model.addAttribute("search_product", productRepository.findByTitleOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
        }else if (!search.isEmpty() && !price.isEmpty()){
            if(price.equals("sorted_by_ascending_price")) {
                model.addAttribute("search_product", productRepository.findByTitleAsc(search.toLowerCase()));
            }else{
                model.addAttribute("search_product", productRepository.findByTitleDesc(search.toLowerCase()));
            }
        }else if (!search.isEmpty() && !contract.isEmpty()){
                model.addAttribute("search_product", productRepository.findByTitleAndCategory(search.toLowerCase(), getContractId(contract)));
        }else if ((!ot.isEmpty() && !Do.isEmpty()) && !price.isEmpty()){
            if(price.equals("sorted_by_ascending_price")) {
                model.addAttribute("search_product", productRepository.findByPriceAsc(Float.parseFloat(ot), Float.parseFloat(Do)));
            }else{
                model.addAttribute("search_product", productRepository.findByPriceDesc(Float.parseFloat(ot), Float.parseFloat(Do)));
            }
        }else if ((!ot.isEmpty() && !Do.isEmpty()) && !contract.isEmpty()){
            model.addAttribute("search_product", productRepository.findByCategoryOrderByPrice(Float.parseFloat(ot), Float.parseFloat(Do), getContractId(contract)));
        }else if (!price.isEmpty() && !contract.isEmpty()){
            if(price.equals("sorted_by_ascending_price")) {
                model.addAttribute("search_product", productRepository.findByCategoryAsc(getContractId(contract)));
            } else {
                model.addAttribute("search_product", productRepository.findByCategoryDesc(getContractId(contract)));
            }
        }else if (!ot.isEmpty() && !Do.isEmpty()){
            model.addAttribute("search_product", productRepository.findByPrice(Float.parseFloat(ot), Float.parseFloat(Do)));
        }else if (!contract.isEmpty()){
            model.addAttribute("search_product", productRepository.findByCategory(getContractId(contract)));
        }else{
            model.addAttribute("search_product", productRepository.findByTitleContainingIgnoreCase(search));
        }
        model.addAttribute("value_search", search);
        model.addAttribute("value_price_ot", ot);
        model.addAttribute("value_price_do", Do);
        return "/product/product";
    }
    public int getContractId(String contract){
        if (contract.equals("beans")) {return 1;}
        else if (contract.equals("ground")) {return 2;}
        else {return 3;}
    }
}
