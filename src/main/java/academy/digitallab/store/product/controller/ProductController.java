package academy.digitallab.store.product.controller;

import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//indica que implementamos un controlador servicio rest
@RestController
// mapeamos el recurso con su uri (hay que tener en consideración las recomendaciones de las URIs)
@RequestMapping( value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //Metodo GET
    @GetMapping
    //metodo que devuelve una lista de productos
    public ResponseEntity<List<Product>> listProduct(){
        List<Product> products = productService.listAllProduct();
        if (products.isEmpty()){
            // retornamos un 404 - sin elemmentos.
            return ResponseEntity.noContent().build();
        }
        // retornamos un 200 y la lista de prodcutos.
        return ResponseEntity.ok(products);
    }

}

/*
    importamos la interfaz del servicio para hacer uso de el.
        ¿Por qué se inyecta la interfaz y no la implementación?:
            Principio de inversión de dependencias (SOLID): el controlador depende de una abstracción, no de una implementación concreta.
            Facilita el testing: puedes sustituir fácilmente la implementación por un mock o stub.
            Permite múltiples implementaciones: puedes tener varias clases que implementen la misma interfaz y elegir cuál usar.
            Desacoplamiento: el controlador no necesita saber cómo está implementado el servicio, solo qué hace.
   */
//importamos por autowired, luego probaré por constructur