package tulio.ecommerce.Controllers;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tulio.ecommerce.Products.ProductModel;
import tulio.ecommerce.Repositories.ProductRepository;
import tulio.ecommerce.User.UserModel;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<String> AddProduct(@RequestBody ProductModel productModel)
    {
        String name = productModel.getName();
        if(productRepository.findbyname(name) != null)
        {
            return ResponseEntity.badRequest().body("Produto já cadastrado");
        }

        productRepository.save(productModel);
        return ResponseEntity.ok().body("Produto adicionado com sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> GetSingleProduct(@PathVariable(value="id") UUID id)
    {
        return ResponseEntity.ok().body(productRepository.findById(id));
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<ProductModel>> GetAllProducts()
    {
        return ResponseEntity.ok().body(productRepository.findAll());
    }

}