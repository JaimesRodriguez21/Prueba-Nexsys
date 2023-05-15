package com.nexsys.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.nexsys.dao.ProductDAO;
import com.nexsys.model.Product;
import com.nexsys.model.request.ProductRequest;
import com.nexsys.model.response.ProductResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final String BASE_URL = "https://api.escuelajs.co/api/v1";
    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public ProductDAOImpl(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Product> getAllProducts() {
        HttpGet httpGet = new HttpGet(this.BASE_URL + "/products");
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String jsonString = EntityUtils.toString(entity);
            List<ProductResponse> productResponses = objectMapper.readValue(jsonString, new TypeReference<List<ProductResponse>>() {
            });
            List<Product> products = new ArrayList<>();
            for (ProductResponse productResponse : productResponses) {
                Product product = new Product();
                product.setPid(productResponse.getId());
                product.setName(productResponse.getTitle());
                product.setPriceFinal(productResponse.getPrice());
                product.setDescription(productResponse.getDescription());
                products.add(product);
            }
            return products;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public Long createProduct(ProductRequest request) {
        HttpPost httpPost = new HttpPost(BASE_URL + "/products");
        try {
            // Configurar el encabezado de la solicitud
            httpPost.setHeader("Content-Type", "application/json");
            // Crear el objeto JSON con la estructura proporcionada
            request.addImagen(request.getImageUrl());
            Gson gson = new Gson();
            JsonObject jsonBody = new JsonObject();
            jsonBody.addProperty("title", request.getName());
            jsonBody.addProperty("price", request.getPriceFinal());
            jsonBody.addProperty("description", request.getDescription());
            jsonBody.addProperty("categoryId", request.getCategoryId());
            JsonArray imagesArray = new JsonArray();
            imagesArray.add(request.getImageUrl());
            jsonBody.add("images", imagesArray);
            // Establecer el cuerpo de la solicitud
            String requestBodyJson = gson.toJson(jsonBody);
            httpPost.setEntity(new StringEntity(requestBodyJson));
            // Ejecutar la solicitud
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String jsonString = EntityUtils.toString(entity);
            // Convertir la respuesta JSON a un objeto JsonObject utilizando Gson
            JsonObject jsonResponse = JsonParser.parseString(jsonString).getAsJsonObject();
            String productId = jsonResponse.get("id").getAsString();
            return Long.parseLong(productId);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}