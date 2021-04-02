package pl.javastart.data;

import org.springframework.stereotype.Repository;
import pl.javastart.model.Category;
import pl.javastart.model.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductsRepository {
    private final List<Product> productsList;

    public ProductsRepository() {
        this.productsList = new ArrayList<>();
        productsList.add(new Product("Czekolada", 3.99, Category.FOOD));
        productsList.add(new Product("Masło", 5.99, Category.FOOD));
        productsList.add(new Product("Lampa", 90.99, Category.HOME));
        productsList.add(new Product("Dywan", 200.05, Category.HOME));
        productsList.add(new Product("Książka", 29.99, Category.OTHERS));
    }

    public List<Product> getListOfAllProducts() {
        return productsList;
    }

    public void addProductToList(Product product) {
        productsList.add(product);
    }

    public List<Product> getProductsListByCategory(Category category) {
        List<Product> productsListByCategory = new ArrayList<>();
        for (Product product : productsList) {
            if (product.getCategory().equals(category)) {
                productsListByCategory.add(product);
            }
        }
        return productsListByCategory;
    }
}
