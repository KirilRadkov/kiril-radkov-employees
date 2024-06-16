package com.example.kirilradkovemployees.integration

import com.example.kirilradkovemployees.services.EmployeePair
import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CSVControllerIT extends Specification {

    @Autowired
    private MockMvc mockMvc
    @Autowired
    private WebApplicationContext webApplicationContext;


    def "getUploadPage csv"() {
        when:
        def response = mockMvc.perform(get("/csv"))


        then:
        response.andExpect(status().is2xxSuccessful())
        response.andExpect(view().name("upload"))

    }

    def "getUploadPage uploadAndShowResults"() {
        given:
        MockMultipartFile csvFile = new MockMultipartFile("file", "", "multipart/form-data", this.getClass().getClassLoader()
                .getResourceAsStream("employees.csv").getBytes())
        ;
        when:
        def response = mockMvc.perform(multipart("/uploadAndShowResults")
                .file(csvFile))

        then:
        response.andExpect(status().is2xxSuccessful())
        response.andExpect(view().name("resultPage"))
        response.andExpect(model().attribute("employeePairs", List.of(new EmployeePair(218, 143, 10, 5))))

    }

}
