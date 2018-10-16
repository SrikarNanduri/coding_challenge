package com.example.codingchallenge.shopping;

import com.example.codingchallenge.util.CartCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductsController.class, secure = false)
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartCalculator cartCalculator;

String exampleJson = "[{\"productId\": \"Dwt5F7KAhi\",\"quantity\": 2},{\"productId\": \"PWWe3w1SDU\",\"quantity\": 2}]";

    @Test
    public void addToCart() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cart")
                .param("userid", "123")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus()); }

    @Test
    public void checkOut() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/checkout/123")
                .accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print()).andReturn();

        System.out.println(result.getResponse().getStatus());

    }

    @Test
    public void getOrder() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "order/user/123")
                .accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getStatus());
    }
}