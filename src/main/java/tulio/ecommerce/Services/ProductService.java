package tulio.ecommerce.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import tulio.ecommerce.Models.Products.ProductModel;
import tulio.ecommerce.Repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductModel> listAllLogic()
    {
        return productRepository.findAll();
    }

    public HttpStatus addProductLogic(ProductModel productModel)
    {
        String name = productModel.getName();
        if(productRepository.findbyname(name) != null)
        {
            return HttpStatus.BAD_REQUEST;
        }

        productRepository.save(productModel);
        
        return HttpStatus.OK;
    }

    public Optional<ProductModel> productInfoLogic(UUID id)
    {
        return productRepository.findById(id);
    }

    public HttpStatus productAttLogic(UUID id, ProductModel productModel)
    {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty())
        {
            return HttpStatus.BAD_REQUEST;
        }

        ProductModel existingProduct = product.get();

        existingProduct.setName(productModel.getName());
        existingProduct.setInventory(productModel.getInventory());
        existingProduct.setTotalSold(productModel.getTotalSold());
        existingProduct.setPrice(productModel.getPrice());

        productRepository.save(existingProduct);

        return HttpStatus.OK;
    }

    public HttpStatus deleteProductLogic(UUID id)
    {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty())
        {
            return HttpStatus.BAD_REQUEST;
        }
        productRepository.deleteById(id);
        return HttpStatus.OK;
    }
}