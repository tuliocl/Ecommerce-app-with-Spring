package tulio.ecommerce.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tulio.ecommerce.Models.Cart.CartModel;

public interface CartRepository extends JpaRepository<CartModel,UUID>{

    
} 