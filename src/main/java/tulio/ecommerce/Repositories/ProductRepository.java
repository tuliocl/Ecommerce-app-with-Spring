package tulio.ecommerce.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tulio.ecommerce.Products.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel,UUID> {

    @Query("SELECT p FROM TB_product p WHERE p.name LIKE %:name%")
    ProductModel findbyname(@Param("name") String name);

    //Optional<ProductModel> findById(UUID id);
    //TO DO
    //@Query("SELECT p FROM TB_product p WHERE p.id = :id")
    //Optional<ProductModel> findByIdCustom(@Param("id") UUID id);

    void deleteById(UUID id);
}