package pudov.vadim.hw9_products.product;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsControllerImpl implements ProductController {
    private final ProductService service;
    private final MeterRegistry meterRegistry;

    @Autowired
    public ProductsControllerImpl(ProductService service, MeterRegistry meterRegistry) {
        this.service = service;
        this.meterRegistry = meterRegistry;
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getProduct(Long id) {
        return ResponseEntity.ok(service.getProduct(id).toString());
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        meterRegistry.counter("requests_to_all_tasks").increment();
        return ResponseEntity.ok(service.getProductList());
    }
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestParam String name,
                                                 @RequestParam String description) {
        Product savedProduct = service.createProduct(name, description);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id); }
}
