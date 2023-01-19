package com.example.SpringTdd;

import com.example.SpringTdd.controller.BookingController;
import com.example.SpringTdd.model.BookingModel;
import com.example.SpringTdd.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class) //necessario para rodar o teste como projeto spring no Junit
public class BookingControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookingService bookingService;

    @Before //necessário pra montar a camada web pro get a cada teste
    public void setUp() throws JsonProcessingException {
        BookingController bookingController = new BookingController(bookingService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build(); //mando o BookingController pro build
        objectMapper = objectMapper.registerModule(new JavaTimeModule()); //ajuste pro jackson aceitar DateTime
    }

    @Test
    public void bookingTestGetAll() throws Exception {
        mockMvc.perform(get("/bookings")).andExpect(status().isOk());
    }

    @Test
    public void bookingTestSave() throws Exception {
        LocalDate checkIn = LocalDate.parse("2022-11-10");
        LocalDate checkOut = LocalDate.parse("2022-11-20");
        BookingModel bookingModel = new BookingModel("Victor", checkIn, checkOut, "1");
        mockMvc.perform(post("/bookings").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(bookingModel))).andExpect(status().isCreated());

    }

    @Test
    public void bookingTestUpdate() throws Exception {
        LocalDate checkIn = LocalDate.parse("2022-11-10");
        LocalDate checkOut = LocalDate.parse("2022-12-20");
        BookingModel bookingModel = new BookingModel("Carolyne", checkIn, checkOut, "2");
        mockMvc.perform(put("/bookings/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(bookingModel))).andExpect(status().isOk());

    }

    @Test
    public void bookingTestDelete() throws Exception {
        LocalDate checkIn = LocalDate.parse("2022-11-10");
        LocalDate checkOut = LocalDate.parse("2022-11-20");
        BookingModel bookingModel = new BookingModel("Victor", checkIn, checkOut, "2");
        mockMvc.perform(delete("/bookings/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(bookingModel))).andExpect(status().isOk());

    }

}