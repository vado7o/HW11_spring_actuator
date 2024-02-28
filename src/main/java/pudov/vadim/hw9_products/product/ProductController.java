package pudov.vadim.hw9_products.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
public interface ProductController {

    // метод для извлечения продукта из списка имеющихся продуктов по id-шнику
    @GetMapping("/{id}")
    ResponseEntity<String> getProduct(@PathVariable("id") Long id);

    // метод для показа всех продуктов из списка
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts();

    // метод для создания и добавления продукта в список продуктов
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestParam String name, @RequestParam String description);

    // метод для удаления продукта из списка продуктов по его id-шнику
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id);
}
