package red.sukun1899.springboot.sample.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import red.sukun1899.springboot.sample.SpringbootWebSampleApplication;
import red.sukun1899.springboot.sample.service.CustomerService;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author su-kun1899
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringbootWebSampleApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class CustomerControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    public void whenGetCustomers_thenStatus200() throws Exception {
        mvc.perform(get("/customers"))
                .andExpect(status().isOk());
    }
}