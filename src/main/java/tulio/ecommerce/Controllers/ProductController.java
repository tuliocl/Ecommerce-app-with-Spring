package tulio.ecommerce.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tulio.ecommerce.Products.ProductModel;
import tulio.ecommerce.Repositories.ProductRepository;

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
    
    @GetMapping("/listAll")
    public ResponseEntity<List<ProductModel>> GetAllProducts()
    {
        return ResponseEntity.ok().body(productRepository.findAll());
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<Optional<ProductModel>> GetProductInfo(@PathVariable(value="id") UUID id)
    {
        return ResponseEntity.ok().body(productRepository.findById(id));
    }

    @PutMapping("/{id}/att")
    public ResponseEntity<String> AttProductInfo(@PathVariable(value="id") UUID id, @RequestBody ProductModel productModel)
    {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product == null)
        {
            return ResponseEntity.badRequest().body("Produto não encontrado");
        }

        ProductModel existingProduct = product.get();

        existingProduct.setName(productModel.getName());
        existingProduct.setInventory(productModel.getInventory());
        existingProduct.setTotalSold(productModel.getTotalSold());
        existingProduct.setPrice(productModel.getPrice());

        productRepository.save(existingProduct);

        return ResponseEntity.ok().body("Produto atualizado");
    }

    @DeleteMapping("/{id}/del")
    public ResponseEntity<String> DelProduct(@PathVariable(value="id") UUID id)
    {
        var product = productRepository.findById(id);
        if(product == null)
        {
            return ResponseEntity.badRequest().body("Produto não encontrado");
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok().body("Produto Removido");
    }
}