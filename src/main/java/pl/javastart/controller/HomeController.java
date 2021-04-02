package pl.javastart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.data.ProductsRepository;
import pl.javastart.model.Category;
import pl.javastart.model.Product;

import java.util.List;

@Controller
public class HomeController {
    private final ProductsRepository productsRepository;

    public HomeController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/createNewProduct")
    public String createNewProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "newProductForm";
    }

    @PostMapping("/addNewProduct")
    public String addNewProduct(Product product) {
        productsRepository.addProductToList(product);
        return "success";
    }

    @GetMapping("/productsListByCategory")
    public String getProductsListByCategory(@RequestParam(name = "kategoria", required = false) Category category, Model model) {
        double price;
        if (isCategoryCorrect(category)) {
            List<Product> productsListByCategory = productsRepository.getProductsListByCategory(category);
            price = calculatePriceOfAllProductsInGivenList(productsListByCategory);
            model.addAttribute("productsList", productsListByCategory);
        } else {
            List<Product> allProductsList = productsRepository.getListOfAllProducts();
            model.addAttribute("productsList", allProductsList);
            price = calculatePriceOfAllProductsInGivenList(allProductsList);
        }
        model.addAttribute("price", price);
        model.addAttribute("category", category);
        return "list";
    }

    private boolean isCategoryCorrect(Category givenCategory) {
        return givenCategory != null;
    }

    private double calculatePriceOfAllProductsInGivenList(List<Product> givenProductsList) {
        double priceOfAllProducts = 0;
        for (Product product : givenProductsList) {
            priceOfAllProducts += product.getPrice();
        }
        return priceOfAllProducts;
    }
}
