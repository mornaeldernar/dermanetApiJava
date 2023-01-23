package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.DoctorDTO;
import com.mornaeldernar.api.dto.SpecialityDTO;
import com.mornaeldernar.api.entity.Doctor;
import com.mornaeldernar.api.service.DoctorService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DoctorController.class)
class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DoctorService service;
    private Date fecha = new Date(2023,01,01);

    DoctorDTO dto = new DoctorDTO(1L,"Juan","Perez",new SpecialityDTO(1L,"Dermatology"));
    String exampleJson = "{\"id\":1,\"name\":\"Juan\",\"lastName\":\"Perez\"}";

    @Test
    void createTest() throws Exception {
        Mockito.when(service.save(Mockito.any(DoctorDTO.class))).thenReturn(dto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/doctor").accept(MediaType.APPLICATION_JSON).content(exampleJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        assertEquals("http://localhost/doctor/1",response.getHeader(HttpHeaders.LOCATION));
    }
    @Test
    void findTest() throws Exception{
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(dto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctor/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        JSONAssert.assertEquals(exampleJson, result.getResponse().getContentAsString(),false);
    }
    @Test
    void updateTest() throws Exception {
        doNothing().when(service).update(Mockito.anyLong(),Mockito.any(DoctorDTO.class));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/doctor/1").accept(MediaType.APPLICATION_JSON).content(exampleJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(),response.getStatus());
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(service).delete(Mockito.anyLong());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/doctor/1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(),response.getStatus());

    }
}