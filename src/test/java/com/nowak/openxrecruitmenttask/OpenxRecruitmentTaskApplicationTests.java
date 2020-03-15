package com.nowak.openxrecruitmenttask;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowak.openxrecruitmenttask.controllers.Controller;
import com.nowak.openxrecruitmenttask.dtos.UserInfo;
import com.nowak.openxrecruitmenttask.dtos.users.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@WebMvcTest(Controller.class)
class OpenxRecruitmentTaskApplicationTests {

    String BASE_API_URL = "https://jsonplaceholder.typicode.com";
    final String USERS = "/users";
    String POSTS = "/posts";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void shouldReturnUserList() {
        ResponseEntity<List<User>> response = restTemplate.exchange(BASE_API_URL + USERS, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        assertEquals(200, response.getStatusCode());
        assertEquals(false, Objects.requireNonNull(response.getBody()).isEmpty());
        assertEquals(true, response.hasBody());
    }

    @Test
    public void shouldReturnUserInfoList() {
        ResponseEntity<List<UserInfo>> response = restTemplate.exchange(BASE_API_URL + POSTS, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserInfo>>() {
        });
        assertEquals(200, response.getStatusCode());
        assertEquals(false, Objects.requireNonNull(response.getBody()).isEmpty());
        assertEquals(true, response.hasBody());
    }

    @Test
    public void testgetCountUsersPosts() throws Exception {
        String uri = "/count";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").exists())
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        int contentLength = mvcResult.getResponse().getContentLength();
        assertTrue(contentLength > 0);
    }

    @Test
    public void testcheckIfPostsAreUnique() throws Exception {
        String uri = "/unique";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.summary").exists())
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        int contentLength = mvcResult.getResponse().getContentLength();
        assertTrue(contentLength > 0);
    }

    @Test
    public void testgetListOfUsersAndNeighbour() throws Exception {
        String uri = "/nearest";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nearest").exists())
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        int contentLength = mvcResult.getResponse().getContentLength();
        assertTrue(contentLength > 0);
    }
    @Test
    public void testmergeTwoApis() throws Exception {
        String uri = "/summary";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.summary").exists())
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        int contentLength = mvcResult.getResponse().getContentLength();
        assertTrue(contentLength > 0);
    }

}
