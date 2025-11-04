package academy.digitallab.store.product;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;


@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        // Category categoria = Category.builder().id(1L).build(); // Asegúrate de que esta categoría exista en la BD
        Product producto = Product.builder()
            .name("computer")
            .category(Category.builder().id(1L).build())
            .description("ordenador")
            .stock(Double.parseDouble("10"))
            .price(Double.parseDouble("1240.99"))
            .status("Createded")
            .createAt(new Date())
            .build();
        productRepository.save(producto);

        List<Product> founds= productRepository.findByCategory(producto.getCategory());
        Assertions.assertThat(founds.size()).isEqualTo(2);
    }

}
