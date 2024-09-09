package tulio.ecommerce.Repositories;

import java.util.UUID;
import java.util.jar.Attributes.Name;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tulio.ecommerce.Products.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel,Name> {

    @Query("SELECT p FROM TB_product p WHERE p.name LIKE %:name%")
    ProductModel findbyname(@Param("name") String name);

    ProductModel findById(UUID id);
}