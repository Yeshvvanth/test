package com.qlick.palindromeassignment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlick.palindromeassignment.dao.MessageDao;
import com.qlick.palindromeassignment.entity.Message;
import com.qlick.palindromeassignment.service.MessageService;

@RunWith(SpringRunner.class)
@SpringBootTest
class PalindromeAssignmentApplicationTests {
	
	@Autowired
	private MessageService messageService;
	
	@MockBean
	private MessageDao messageDao;
	
	private MockMvc mvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private WebApplicationContext context;
	
	
	@Before
	private void setUp()
	{
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
		
	}
	
	
	@Test
	public void getMessagesTest()
	{
		when(messageDao.findAll()).thenReturn(Stream.of(new Message(1,"mom",true), new Message(2,"madam",true)).collect(Collectors.toList()));
		assertEquals(2,messageService.findAll().size());
	}

	@Test
	public void getMessagesByIdTest() {
		
		int id = 1;
		Message word = new Message(1,"mam",true);
		when(messageDao.findById(id)).thenReturn(word);
		assertEquals(1,messageService.findById(id).getId());
	
	}
	
	@Test
	public void saveMessageTest()
	{
		Message word = new Message(909,"Afry",false);
		
		when(messageDao.save(word)).thenReturn(word);

		assertEquals(word,messageService.save(word));
		
	}
	
	@Test
	public void deleteMessageTest()
	{
		
		int id = 20;
		messageService.delete(id);
		verify(messageDao,times(1)).delete(id);
		
	}

}
