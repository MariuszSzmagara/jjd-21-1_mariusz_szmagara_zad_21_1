package pl.javastart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.data.ProductsRepository;
import pl.javastart.model.Category;
import pl.javastart.model.Product;

import java.util.ArrayList;
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
        boolean addedCorrectly = productsRepository.addProductToList(product);
        if (addedCorrectly) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/productsListByCategory")
    public String getProductsListByCategory(@RequestParam(name = "kategoria") String category, Model model) {
        List<Product> newProductsList = new ArrayList<>();
        double price = 0;
        if (isCategoryCorrect(category)) {
            for (Product product : productsRepository.getListOfAllProducts()) {
                if (Category.createFromDescription(category).equals(product.getCategory())) {
                    newProductsList.add(product);
                    price += product.getPrice();
                }
            }
            model.addAttribute("productsList", newProductsList);
        } else {
            for (Product product : productsRepository.getListOfAllProducts()) {
                price += product.getPrice();
            }
            model.addAttribute("productsList", productsRepository.getListOfAllProducts());
        }
        model.addAttribute("price", price);
        return "list";
    }

    private boolean isCategoryCorrect(String category) {
        return category != null && !category.equals("");
    }
}
