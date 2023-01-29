package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.PatientDTO;
import com.mornaeldernar.api.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PatientController.class)
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PatientService service;
    private Date fecha = new Date(1990,0,1,0,0,0);

    PatientDTO dto = new PatientDTO(1L,"Alberto","Vazques",fecha);
    String exampleJson = "{\"id\":1,\"name\":\"Alberto\",\"lastName\":\"Vazques\",\"birthdate\":\"3890-01-01T06:00:00.000+00:00\"}";

    @Test
    void createTest() throws Exception {
        Mockito.when(service.save(Mockito.any(PatientDTO.class))).thenReturn(dto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/patient").accept(MediaType.APPLICATION_JSON).content(exampleJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());
//        assertEquals("http://localhost/patient/1",response.getHeader(HttpHeaders.LOCATION));
    }

    @WithMockUser("test")
    @Test
    void findTest() throws Exception{
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(dto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patient/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        JSONAssert.assertEquals(exampleJson, result.getResponse().getContentAsString(),false);
    }
    @Test
    void updateTest() throws Exception {
        doNothing().when(service).update(Mockito.anyLong(),Mockito.any(PatientDTO.class));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/patient/1").accept(MediaType.APPLICATION_JSON).content(exampleJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(service).delete(Mockito.anyLong());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/patient/1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());

    }
}