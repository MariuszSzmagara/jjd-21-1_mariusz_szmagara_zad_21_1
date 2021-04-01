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
    public String getProductsListByCategory(@RequestParam(name = "kategoria") String category, Model model) {
        List<Product> allProductsList = productsRepository.getListOfAllProducts();
        List<Product> productsListByCategory;
        double price;
        if (isCategoryCorrect(category)) {
            productsListByCategory = productsRepository.getProductsListByCategory(category);
            price = productsRepository.calculatePriceOfAllProductsInGivenList(productsListByCategory);
            model.addAttribute("productsList", productsListByCategory);
        } else {
            model.addAttribute("productsList", allProductsList);
            price = productsRepository.calculatePriceOfAllProductsInGivenList(allProductsList);
        }
        model.addAttribute("price", price);
        return "list";
    }

    private boolean isCategoryCorrect(String category) {
        return Category.FOOD.name().equals(category)
                || Category.HOME.name().equals(category)
                || Category.OTHERS.name().equals(category);
    }
}
