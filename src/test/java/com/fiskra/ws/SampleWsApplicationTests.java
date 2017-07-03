package com.fiskra.ws;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fiskra.ws.model.Bookmark;
import com.fiskra.ws.model.User;
import com.fiskra.ws.repo.BookmarkRepository;
import com.fiskra.ws.repo.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleWsApplication.class)
@WebAppConfiguration
public class SampleWsApplicationTests {

	 private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	            MediaType.APPLICATION_JSON.getSubtype(),
	            Charset.forName("utf8"));

	    private MockMvc mockMvc;
	    
	    private User user;
	    
	    private List<Bookmark> bookmarkList = new ArrayList<>();
	    
	    @Autowired
	    private BookmarkRepository bookmarkRepository;
	    
	    @Autowired
	    private UserRepository userRepository;
	    
	    @Autowired
	    private WebApplicationContext webApplicationContext;
	    
	    private HttpMessageConverter mappingJackson2HttpMessageConverter;
	    
	    
	    @Autowired
	    void setConverters(HttpMessageConverter<?>[] converters) {

	        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
	            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
	            .findAny()
	            .orElse(null);

	        assertNotNull("the JSON message converter must not be null",
	                this.mappingJackson2HttpMessageConverter);
	    }

	    @Before
	    public void setup() throws Exception {
	        this.mockMvc = webAppContextSetup(webApplicationContext).build();

	        this.bookmarkRepository.deleteAllInBatch();
	        this.userRepository.deleteAllInBatch();

	        this.user = userRepository.save(new User("fferide.celik@gmail.com", "feride"));
	        
	        this.bookmarkList.add(bookmarkRepository.save(new Bookmark(user, "http://bookmark.com/1/" , "A description")));
	        this.bookmarkList.add(bookmarkRepository.save(new Bookmark(user, "http://bookmark.com/2/" , "A description")));
	    }

	    @Test
	    public void userNotFound() throws Exception {
	        mockMvc.perform(post("/1/bookmarks/")
	                .content(this.json(new Bookmark()))
	                .contentType(contentType))
	                .andExpect(status().isNotFound());
	    }

	    @Test
	    public void readSingleBookmark() throws Exception {
	        mockMvc.perform(get("/" + 1 + "/bookmarks/"
	                + this.bookmarkList.get(0).getId()))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(contentType))
	                .andExpect(jsonPath("$.id", is(this.bookmarkList.get(0).getId().intValue())))
	                .andExpect(jsonPath("$.uri", is("http://bookmark.com/1/")))
	                .andExpect(jsonPath("$.description", is("A description")));
	    }

	    @Test
	    public void readBookmarks() throws Exception {
	        mockMvc.perform(get("/" + "1/bookmarks"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(contentType))
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[0].id", is(this.bookmarkList.get(0).getId().intValue())))
	                .andExpect(jsonPath("$[0].uri", is("http://bookmark.com/1/")))
	                .andExpect(jsonPath("$[0].description", is("A description")))
	                .andExpect(jsonPath("$[1].id", is(this.bookmarkList.get(1).getId().intValue())))
	                .andExpect(jsonPath("$[1].uri", is("http://bookmark.com/2/")))
	                .andExpect(jsonPath("$[1].description", is("A description")));
	    }


	    protected String json(Object o) throws IOException {
	        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	        this.mappingJackson2HttpMessageConverter.write(
	                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	        return mockHttpOutputMessage.getBodyAsString();
	    }

}
