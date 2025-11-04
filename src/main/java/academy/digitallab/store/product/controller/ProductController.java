package academy.digitallab.store.product.controller;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//indica que implementamos un controlador servicio rest
@RestController
// mapeamos el recurso con su uri (hay que tener en consideración las recomendaciones de las URIs)
@RequestMapping( value = "/products")
//Constructores para hacer inyeccion de dependencias
@AllArgsConstructor
public class ProductController {
   // hacemos uso de inyecion de dependencias por constructor @AllArgsConstructor y final private ProductService ...
   /*
    importamos la interfaz del servicio para hacer uso de el.
            ¿Por qué se inyecta la interfaz y no la implementación?:
    Principio de inversión de dependencias (SOLID): el controlador depende de una abstracción, no de una implementación concreta.
    Facilita el testing: puedes sustituir fácilmente la implementación por un mock o stub.
    Permite múltiples implementaciones: puedes tener varias clases que implementen la misma interfaz y elegir cuál usar.
    Desacoplamiento: el controlador no necesita saber cómo está implementado el servicio, solo qué hace.
    */
   final  private ProductService productService;

    //Metodo GET
    @GetMapping
    //metodo que devuelve una lista de productos
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name="categoryId", required=false) Long categoriaId ){
        List<Product> products = new ArrayList<>();
        if (categoriaId == null){
            products = productService.listAllProduct();
            if (products.isEmpty()){
                // retornamos un 404 - sin elemmentos.
                return ResponseEntity.noContent().build();
            }
        }else{
            products = productService.findByCategory(Category.builder().id(categoriaId).build());
            if (products.isEmpty()){
                // retornamos not found elementos de esa categoria.
                return ResponseEntity.notFound().build();
            }
        }
        // retornamos un 200 y la lista de productos.
        return ResponseEntity.ok(products);
    }

    @GetMapping (value="/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product productCreate = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }


    @PutMapping(value="/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        Product productDB = productService.updateProduct(product);
        if (productDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDB);
    }
}

