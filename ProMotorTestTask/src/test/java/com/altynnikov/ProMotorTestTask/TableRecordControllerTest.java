package com.altynnikov.ProMotorTestTask;

import com.altynnikov.ProMotorTestTask.modes.TableRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TableRecordControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addRecordTest() throws Exception {
        TableRecord tableRecord = new TableRecord("new");
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(post("/record/add/")
                .contentType("application/json")
                .content(mapper.writeValueAsString(tableRecord)))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("{\"id\":12,\"value\":\"new\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void getAllRecordTest() throws Exception {
        mockMvc.perform(get("/record/get/"))
                .andExpect(jsonPath("$.length()")
                        .value(12));
    }

    @Test
    public void getAllOneRecordTest() throws Exception {
        mockMvc.perform(get("/record/get/october"))
                .andExpect(jsonPath("$.length()")
                        .value(1));
    }

    @Test
    public void getAllZeroRecordTest() throws Exception {
        mockMvc.perform(get("/record/get/0"))
                .andExpect(jsonPath("$.length()")
                        .value(0));
    }

    @Test
    public void deleteRecordTest() throws Exception {
        TableRecord tableRecord = new TableRecord(1, "January");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(delete("/record/delete")
                .contentType("application/json")
                .content(mapper.writeValueAsString(tableRecord)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateRecordTest() throws Exception {
        TableRecord tableRecord = new TableRecord(1, "NewJanuary");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/record/update")
                .contentType("application/json")
                .content(mapper.writeValueAsString(tableRecord)))
                .andExpect(status().isOk());
    }
}
