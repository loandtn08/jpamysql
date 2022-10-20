package com.example.jpamysql.controller;

import com.example.jpamysql.model.Comment;
import com.example.jpamysql.repository.CommentRepository;
import com.example.jpamysql.service.impl.CommentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import  org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CommentController.class, CommentServiceImpl.class, CommentRepository.class })
@WebMvcTest
public class CommentControllerTest {
    @MockBean
    CommentRepository commentRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateComment() throws Exception {
        Comment comment = new Comment();
        comment.setPostName("post2");
        comment.setComment("comment2");

        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(comment))
        ).andReturn();
    }
    @Test
    public void testGetAllComments() throws Exception{
        List<Comment> comments = new ArrayList<>(
                Arrays.asList(new Comment(1,"Post 1", "Comment 1"))
        );

        Mockito.when(commentRepository.findAll()).thenReturn(comments);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        System.out.println(result.getResponse().getContentAsString());
        Comment[] commentList =  mapFromJson(result.getResponse().getContentAsString(), Comment[].class);
        Assertions.assertTrue(commentList.length > 0);
    }

    @Test
    public void testFindCommentByIdReturnData() throws Exception {
        int id  = 1;
        Comment comment = new Comment(1,"Post 1", "Comment 1");
        Mockito.when(commentRepository.findById(id)).thenReturn(Optional.of(comment));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/comments/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.postName").value(comment.getPostName()))
                .andDo(print());
    }

    @Test
    public void testFindCommentByIdNoReturnData() throws Exception {
        int id = 1;
        Mockito.when(commentRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/comment/{id}",id)).andExpect(
            status().isNotFound()
        );
    }

    protected  <T> T mapFromJson(String json,  Class<T> clazz) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    public String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(obj);
    }
}
