package academy.digitallab.store.product.service;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    //importamos el repositorio JPA con inyeccion dependencias por constructor.
    private final ProductRepository productRepository;


    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
       product.setStatus("CREATED");
       product.setCreateAt(new Date());
       return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        // buscamos el registro en la BD
        Product productDB = getProduct(product.getId());
        //si no existe registro no podemos actualizar
        if (productDB == null){
            return null;
        }
        //actualizamos el producto recuperado de la BD y actualizamos los campos que nosotros queremos
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        // buscamos el registro en la BD
        Product productDB = getProduct(id);
        //si no existe registro no podemos actualizar
        if (productDB == null){
            return null;
        }
        productDB.setStatus("DELETED");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        // buscamos el registro en la BD
        Product productDB = getProduct(id);
        //si no existe registro no podemos actualizar
        if (productDB == null){
            return null;
        }
        //calculamos el nuevo stock
        Double stock = productDB.getStock() + quantity;
        // actualizamos el objeto del registro
        productDB.setStock(stock);
        // guardamos el objeto del registrto modifuicado en la bd
        return productRepository.save(productDB);
    }
}
