package pudov.vadim.hw9_products.product;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private Long idCount = 1L;

    // создаём список из трёх продуктов для имитации наличия продуктов в магазине
    private final List<Product> productList = new ArrayList<>(List.of(
            new Product(idCount++, "Стиральный порошок Х-чистин", "Порошок для удаления пятен"),
            new Product(idCount++, "Зуная паста Зуб-даю", "Паста для отбеливания зубов"),
            new Product(idCount++, "Ноутбук Игрэм", "Игровой ноутбук с мощной видеокартой")
    ));

    // также специально создаём так называемый "Нулевой" продукт для его выбрасывания при запросе НЕСУЩЕСТВУЮЩЕГО продукта
    Product nullProduct = new Product(null, "Такой продукт отсутствует в списке!", "!");


    // метод для создания продукта. при этом id формируется автоматически
    public Product createProduct(String name, String description) {
        Product product = new Product(idCount++, name, description);
        productList.add(product);
        return product;
    }
    // метод для извлечения продукта из списка по id продукта
    public Product getProduct(Long id) {
        return productList.stream().filter(product ->
                Objects.equals(product.getId(), id)).findFirst().orElse(nullProduct);
    }
    // метод для извлечения всего списка продуктов
    public List<Product> getProductList() {
        return productList;
    }

    // метод для удаления продукта из списка по id продукта
    public void deleteProduct(Long id) {
        productList.forEach(product -> {if(Objects.equals(product.getId(), id)) productList.remove(product);});
    }
}
