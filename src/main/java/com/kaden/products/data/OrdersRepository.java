package com.kaden.products.data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<OrderEntity, Integer> {
    // Spring Data automatically provides implementations for:
    // - findById(Integer id)
    // - findAll()
    // - save(OrderEntity entity)
    // - deleteById(Integer id)
    // - and more...
    
    // You can add custom query methods here if needed, for example:
    // List<OrderEntity> findByOrderNumber(String orderNumber);
}
