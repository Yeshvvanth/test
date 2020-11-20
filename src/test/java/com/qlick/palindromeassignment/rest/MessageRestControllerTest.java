package com.qlick.palindromeassignment.rest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlick.palindromeassignment.entity.Message;
import com.qlick.palindromeassignment.service.MessageService;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageRestController.class)
public class MessageRestControllerTest {
	
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private MessageService service;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    
    @Test
    public void incorrectUri()
      throws Exception {
        
        Message word1 = new Message(1,"alex",false);
     
        List<Message> allMessages = Arrays.asList(word1);
     
        given(service.findAll()).willReturn(allMessages);
     
        mvc.perform(get("/api/message")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isNotFound());
    }
    
    @Test
    public void testGetMessages() throws Exception 
    {
    	Message word1 = new Message(1,"alex",false);
    	Message word2 = new Message(2,"bla",false);
    	
        when(service.findAll()).thenReturn(Arrays.asList(word1, word2));
        
        mvc.perform(get("/api/messages")).andDo(print()).andExpect(status().isOk());
        

    }
    
    @Test
    void whenValidInput_thenReturns200() throws Exception {
    	Message word2 = new Message(2,"bla",false);
      
       mvc.perform(post("/api/messages")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(word2)))
            .andExpect(status().isOk());
    }
    

}


