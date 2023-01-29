package com.mornaeldernar.api.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mornaeldernar.api.dto.DiagnosticDTO;
import com.mornaeldernar.api.entity.Diagnostic;
import com.mornaeldernar.api.entity.Role;
import com.mornaeldernar.api.security.*;
import com.mornaeldernar.api.service.DiagnosticService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.logging.Filter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DiagnosticController.class)
public class DiagnosticControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DiagnosticService service;

    @InjectMocks
    private DiagnosticController controller;

    private Date fecha = new Date(2023,01,01);

    Diagnostic entity = new Diagnostic(1L,"Cancer",fecha, fecha);
    DiagnosticDTO dto = new DiagnosticDTO(1L,"Cancer");
    String exampleJson = "{\"id\":1,\"name\":\"Cancer\"}";
    static String token;
    @BeforeAll
    static void login() throws Exception {
        token = TokenUtils.createToken("Rafael",
                "jimenez.rafa@gmail.com",
                Role.USER);

    }
    @Test
    void createTest() throws Exception {

        Mockito.when(service.save(Mockito.any(DiagnosticDTO.class))).thenReturn(dto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/diagnostic")
                .header("Authorization","Bearer "+token)
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc
                .perform(requestBuilder)

                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());
//        assertEquals("http://localhost/diagnostic/1",response.getHeader(HttpHeaders.LOCATION));
    }


    @WithMockUser("test")
    @Test
    void findTest() throws Exception{
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(dto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/diagnostic/1")
                .header("Authorization","Bearer "+token)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        JSONAssert.assertEquals(exampleJson, result.getResponse().getContentAsString(),false);
    }

    @WithMockUser("test")
    @Test
    void updateTest() throws Exception {
        doNothing().when(service).update(Mockito.anyLong(),Mockito.any(DiagnosticDTO.class));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/diagnostic/1")
                .header("Authorization","Bearer "+token)
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(service).delete(Mockito.anyLong());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/diagnostic/1")
                .header("Authorization","Bearer "+token);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());

    }
}