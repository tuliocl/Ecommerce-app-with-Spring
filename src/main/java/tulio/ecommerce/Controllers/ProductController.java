package tulio.ecommerce.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tulio.ecommerce.Models.Products.ProductModel;
import tulio.ecommerce.Repositories.ProductRepository;
import tulio.ecommerce.Services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @GetMapping("/listAll")
    public ResponseEntity<List<ProductModel>> GetAllProducts()
    {
        return ResponseEntity.ok().body(productService.listAllLogic());
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> AddProduct(@RequestBody ProductModel productModel)
    {
        HttpStatus response = productService.addProductLogic(productModel);

        if(response == HttpStatus.BAD_REQUEST)
        {
            return ResponseEntity.badRequest().body("Produto já cadastrado");
        }

        return ResponseEntity.ok().body("Produto adicionado com sucesso");
    }
    

    @GetMapping("/{id}/info")
    public ResponseEntity<Optional<ProductModel>> GetProductInfo(@PathVariable(value="id") UUID id)
    {
        return ResponseEntity.ok().body(productService.productInfoLogic(id));
    }

    @PutMapping("/{id}/att")
    public ResponseEntity<String> AttProductInfo(@PathVariable(value="id") UUID id, @RequestBody ProductModel productModel)
    {
        HttpStatus response = productService.productAttLogic(id, productModel);

        if(response == HttpStatus.BAD_REQUEST)
        {
            return ResponseEntity.badRequest().body("Produto não encontrado");
        }

        return ResponseEntity.ok().body("Produto atualizado");
    }

    @DeleteMapping("/{id}/del")
    public ResponseEntity<String> DelProduct(@PathVariable(value="id") UUID id)
    {
        HttpStatus response = productService.deleteProductLogic(id);
        if(response == HttpStatus.BAD_REQUEST)
        {
            return ResponseEntity.badRequest().body("Produto não encontrado");
        }
        return ResponseEntity.ok().body("Produto Removido");
    }
}