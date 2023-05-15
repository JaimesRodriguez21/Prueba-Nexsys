package com.nexsys.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexsys.dao.CategoryDAO;
import com.nexsys.model.Category;
import com.nexsys.model.response.CategoryResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private final String BASE_URL = "https://api.escuelajs.co/api/v1";
    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public CategoryDAOImpl(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Category> getAllCategories() {
        HttpGet httpGet = new HttpGet(this.BASE_URL + "/categories");
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String jsonString = EntityUtils.toString(entity);
            List<CategoryResponse> categoryResponses = objectMapper.readValue(jsonString, new TypeReference<List<CategoryResponse>>() {
            });
            List<Category> categories = new ArrayList<>();
            for (CategoryResponse categoryResponse : categoryResponses) {
                Category category = new Category();
                category.setCid(categoryResponse.getId());
                category.setTitle(categoryResponse.getName());
                categories.add(category);
            }
            return categories;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
