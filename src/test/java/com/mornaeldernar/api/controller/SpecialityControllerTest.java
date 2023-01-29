package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.SpecialityDTO;
import com.mornaeldernar.api.entity.Role;
import com.mornaeldernar.api.entity.Speciality;
import com.mornaeldernar.api.security.TokenUtils;
import com.mornaeldernar.api.service.SpecialityService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
@WebMvcTest(value = SpecialityController.class)
public class SpecialityControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SpecialityService service;
    private Date fecha = new Date(2023,01,01);

    Speciality entity = new Speciality(1L,"Dermatology",fecha, fecha);
    SpecialityDTO dto = new SpecialityDTO(1L,"Dermatology");
    String exampleJson = "{\"id\":1,\"name\":\"Dermatology\"}";
    static String token;
    @BeforeAll
    static void login() {
        token = TokenUtils.createToken("Test",
                "jimenez.rafa@gmail.com",
                Role.USER);
        System.out.println(token);
    }
    @Test
    void createTest() throws Exception {
        Mockito.when(service.save(Mockito.any(SpecialityDTO.class))).thenReturn(dto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/speciality").accept(MediaType.APPLICATION_JSON).header("Authorization","Bearer "+token).content(exampleJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());
//        assertEquals("http://localhost/speciality/1",response.getHeader(HttpHeaders.LOCATION));
    }

    @WithMockUser("test")
    @Test
    void findTest() throws Exception{
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(dto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/speciality/1").header("Authorization","Bearer "+token).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        JSONAssert.assertEquals(exampleJson, result.getResponse().getContentAsString(),false);
    }
    @Test
    void updateTest() throws Exception {
        doNothing().when(service).update(Mockito.anyLong(),Mockito.any(SpecialityDTO.class));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/speciality/1").accept(MediaType.APPLICATION_JSON).content(exampleJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(service).delete(Mockito.anyLong());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/speciality/1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(),response.getStatus());

    }
}