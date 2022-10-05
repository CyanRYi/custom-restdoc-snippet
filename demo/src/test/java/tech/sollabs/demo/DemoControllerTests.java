package tech.sollabs.demo;

import static org.hamcrest.core.Is.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class DemoControllerTests extends DemoApplicationTests {

  @Test
  public void getResource_success() throws Exception {
    // When
    ResultActions result = mockMvc.perform(
        get("/test")
            .accept(MediaType.APPLICATION_JSON));

    // Then
    result.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.key1", is("value1")))
        .andExpect(jsonPath("$.key2", is("value2")))
        .andExpect(jsonPath("$.code", is("IOS")));

    // Given

    // Documentation
    result.andDo(document("demo",
        responseFields(
            fieldWithPath("key1").type(STRING).description("First Response Key"),
            fieldWithPath("key2").type(STRING).description("Second Response Key"),
            fieldWithPath("code").type(STRING).description("<<os-type,Device Type Code>>"))));
  }
}
