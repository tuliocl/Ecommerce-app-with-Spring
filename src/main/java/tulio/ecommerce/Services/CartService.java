package tulio.ecommerce.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import tulio.ecommerce.Models.Cart.CartModel;
import tulio.ecommerce.Models.Products.ProductModel;
import tulio.ecommerce.Repositories.CartRepository;
import tulio.ecommerce.Repositories.ProductRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public HttpStatus AddItemLogic(UUID cartID, UUID itemID)
    {
        Optional<CartModel> cartOpt = cartRepository.findById(cartID);
        Optional<ProductModel> prodOpt = productRepository.findById(itemID);

        if(cartOpt.isEmpty() || prodOpt.isEmpty())
        {
            return HttpStatus.NOT_FOUND;
        }

        CartModel cart = cartOpt.get();

        cart.getItems().add(itemID);
        cartRepository.save(cart);

        return HttpStatus.OK;
    }

    public List<ProductModel> ListCartLogic(UUID id)
    {
        Optional<CartModel> cartOpt = cartRepository.findById(id);

        if (cartOpt.isEmpty()) {
            return Collections.emptyList();
        }

        CartModel cart = cartOpt.get();
        List<UUID> productIds = cart.getItems();
        
        List<ProductModel> products =  new ArrayList<ProductModel>();
        for(UUID item : productIds)
        {
            Optional<ProductModel> prodcutOpt = productRepository.findById(item);
            if(!prodcutOpt.isEmpty())
            {
                ProductModel existingProduct = prodcutOpt.get();
                products.add(existingProduct);
            }
            
        }

        return products;
    }
}
