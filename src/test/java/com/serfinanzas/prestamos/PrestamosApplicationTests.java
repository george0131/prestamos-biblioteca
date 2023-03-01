package com.serfinanzas.prestamos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serfinanzas.prestamos.rest.domain.BookInput;
import com.serfinanzas.prestamos.rest.domain.LendInput;
import com.serfinanzas.prestamos.rest.domain.LendItemInput;
import com.serfinanzas.prestamos.rest.domain.ReaderInfoInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PrestamosApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void failedCreationLendBookNonExists() throws Exception {

		ReaderInfoInput infoInput = getReaderInfo();

		LendItemInput itemInput = new LendItemInput();
		itemInput.setBook(new BookInput(321456));

		LendInput lendInput = new LendInput();
		lendInput.setReaderInfo(infoInput);
		lendInput.setLendItem(itemInput);
		lendInput.setReturnOn(LocalDate.now().plusDays(6));

		String json = objectMapper.writeValueAsString(lendInput);

		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/lend/create")
						.contentType(MediaType.valueOf("application/json"))
						.content(json)
		).andDo(print())
				.andExpect(status().isNotFound());

	}

	@Test
	void failedCreationLendBookOnLoan() throws Exception {

		ReaderInfoInput infoInput = getReaderInfo();

		LendItemInput itemInput = new LendItemInput();
		itemInput.setBook(new BookInput(1234));

		LendInput lendInput = new LendInput();
		lendInput.setReaderInfo(infoInput);
		lendInput.setLendItem(itemInput);
		lendInput.setReturnOn(LocalDate.now().plusDays(6));

		String json = objectMapper.writeValueAsString(lendInput);

		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/lend/create")
						.contentType(MediaType.valueOf("application/json"))
						.content(json)
		).andDo(print())
				.andExpect(status().is5xxServerError());

	}

	@Test
	void failedCreationLendBookIsPalindrome() throws Exception {

		ReaderInfoInput infoInput = getReaderInfo();

		LendItemInput itemInput = new LendItemInput();
		itemInput.setBook(new BookInput(987656789));

		LendInput lendInput = new LendInput();
		lendInput.setReaderInfo(infoInput);
		lendInput.setLendItem(itemInput);
		lendInput.setReturnOn(LocalDate.now().plusDays(6));

		String json = objectMapper.writeValueAsString(lendInput);

		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/lend/create")
						.contentType(MediaType.valueOf("application/json"))
						.content(json)
		).andDo(print())
				.andExpect(status().is5xxServerError());
	}

	@Test
	void createLendBookWithSumDigitsGreaterThan40() throws Exception {

		ReaderInfoInput infoInput = getReaderInfo();

		LendItemInput itemInput = new LendItemInput();
		itemInput.setBook(new BookInput(987659));

		LendInput lendInput = new LendInput();
		lendInput.setReaderInfo(infoInput);
		lendInput.setLendItem(itemInput);
		lendInput.setReturnOn(LocalDate.now().plusDays(8));

		String json = objectMapper.writeValueAsString(lendInput);

		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/lend/create")
						.contentType(MediaType.valueOf("application/json"))
						.content(json)
		).andDo(print())
				.andExpect(status().isCreated());

	}

	private ReaderInfoInput getReaderInfo() {

		ReaderInfoInput infoInput = new ReaderInfoInput();
		infoInput.setDocumentId(22123456);
		infoInput.setNames("Alfredo Enrique");
		infoInput.setLastNames("Arias Echeverria");
		infoInput.setBirthDate(LocalDate.of(1989, 3, 13));
		infoInput.setCellPhone("3001234433");

		return infoInput;
	}

}
