package com.example.todo.integration;


import com.example.todo.controller.TaskController;
import com.example.todo.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskTest {

    @Autowired
    private TaskController taskController;

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void ContextLoader() {
//        assertThat(taskController).isNotNull();
        assertNotNull(taskController);
    }

    @Test
    public void CreateTaskWithCorrectValuesTest() {
        Task t = new Task("demo1", "demo1 description", "pending");
        try {
            this.mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(asJsonString(t))).andDo(print()).andExpect(status().isCreated()).
                    andExpect(content().string(containsString("demo1 description")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

