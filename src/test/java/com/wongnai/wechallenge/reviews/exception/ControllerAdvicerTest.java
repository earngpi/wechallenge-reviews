package com.wongnai.wechallenge.reviews.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wongnai.wechallenge.reviews.model.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerAdvicerTest {

    private static String errResponse = "{\"errorMessage\":\"%s\"}";

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testHandleMethodArgumentTypeMismatchException() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/reviews/5o"))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(MethodArgumentTypeMismatchException.class, mvcResult.getResolvedException().getClass());
        assertEquals(String.format(errResponse, "URL has invalid format - 'reviews/5o'"), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testHandleMissingServletRequestParameterException() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/reviews?qurry=doughnut"))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(MissingServletRequestParameterException.class, mvcResult.getResolvedException().getClass());
        assertEquals(String.format(errResponse, "URL is missing a parameter 'query'"), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testHandleHttpClientErrorException() throws Exception {
        Review updatedReview = new Review();
        updatedReview.setReviewId(131);
        updatedReview.setReview("Review ID in URL 130 does not match Review ID in request body 131");

        MvcResult mvcResult = mockMvc.perform(put("/reviews/130")
                .content(new ObjectMapper().writeValueAsString(updatedReview))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(HttpClientErrorException.class, mvcResult.getResolvedException().getClass());
        assertEquals(String.format(errResponse, "Review IDs in request URL and request body do not match."), mvcResult.getResponse().getContentAsString());
    }

}
