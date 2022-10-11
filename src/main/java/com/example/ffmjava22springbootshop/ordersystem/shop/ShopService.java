package com.example.ffmjava22springbootshop.ordersystem.shop;
import com.example.ffmjava22springbootshop.ordersystem.shop.order.Order;
import com.example.ffmjava22springbootshop.ordersystem.shop.order.OrderRepo;
import com.example.ffmjava22springbootshop.ordersystem.shop.product.Product;
import com.example.ffmjava22springbootshop.ordersystem.shop.product.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ShopService(
            ProductRepo productRepo,
            OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Product getProduct(String id) {
        return productRepo.getProduct(id);
    }

    public List<Product> listProducts() {
        return productRepo.listProducts();
    }

    public Order addOrder(String orderId, List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product product = productRepo.getProduct(productId);
            products.add(product);
        }

        Order order = new Order(orderId, products);
        orderRepo.addOrder(order);
        return order;
    }

    public Order getOrder(String orderId) {
        return orderRepo.getOrder(orderId);
    }

    public List<Order> listOrders() {
        return orderRepo.listOrders();
    }

    public void removeOrder(String id) {
        orderRepo.removeOrder(id);
    }

    public void addProduct(Product product) {
        String id = UUID.randomUUID().toString();
        productRepo.addProduct(id, product);
    }

}
