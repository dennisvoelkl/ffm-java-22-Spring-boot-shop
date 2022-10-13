package com.example.ffmjava22springbootshop.IntegrationTest;

import com.example.ffmjava22springbootshop.ordersystem.shop.ServiceUtils;
import com.example.ffmjava22springbootshop.ordersystem.shop.ShopService;
import com.example.ffmjava22springbootshop.ordersystem.shop.order.OrderRepo;
import com.example.ffmjava22springbootshop.ordersystem.shop.product.Product;
import com.example.ffmjava22springbootshop.ordersystem.shop.product.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    // Wandelt zwischen Datentypen um
    // zb Array List zu JSON
    @Autowired
    private ObjectMapper objectMapper;

    @DirtiesContext
    @Test
    void getProductBananaFromProduct() throws Exception{
        //GIVEN

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/2"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                    {"id":"2",
                                     "name":"Banane"}
                                    """));
    }

    @DirtiesContext
    @Test
    void getAllProducts() throws Exception{
        //GIVEN
        String expected = objectMapper.writeValueAsString(List.of(new Product("1", "Apfel"),
                                                                    new Product("2", "Banane"),
                                                                    new Product("3", "Zitrone"),
                                                                    new Product("4", "Mandarine")));
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    /*
    // Funktioniert noch nicht ganz wegen UUID
    @DirtiesContext
    @Test
    void addProduct5Cherry_expectProduct() throws Exception{
        //GIVEN
        OrderRepo orderRepo = mock(OrderRepo.class);
        ProductRepo productRepo = mock(ProductRepo.class);
        ServiceUtils serviceUtils = mock(ServiceUtils.class);
        ShopService shopService = new ShopService(productRepo, orderRepo, serviceUtils);

        //String uuid = shopService.addProduct();
        when(serviceUtils.generateUUID()).thenReturn("5");
        //
        String contentExpect = objectMapper.writeValueAsString(new Product(uuid, "Cherry"));
        String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(contentExpect))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("Content" + content);
        System.out.println("Liste" + productRepo.listProducts());

        //Product prouct = objectMapper.readValue(content, Product.class);

        String expect = objectMapper.writeValueAsString(new Product(uuid, "Cherry"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/"+uuid))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json(expect));
    }
*/

}

