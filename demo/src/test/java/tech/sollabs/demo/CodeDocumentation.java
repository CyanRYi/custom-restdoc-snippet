package tech.sollabs.demo;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static tech.sollabs.testsupport.snippets.CodeDescriptionFieldsSnippet.codeDescriptionFieldsSnippet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import tech.sollabs.testsupport.MockMvcSupport;
import tech.sollabs.testsupport.snippets.CodeLegend;

@AutoConfigureRestDocs(uriScheme = "https", uriHost = "doc.sollabs.tech")
@ExtendWith(RestDocumentationExtension.class)
public class CodeDocumentation {

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp(RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcSupport.createDocumentMockMvc(restDocumentation);
  }

  private void printDocument(String index, String title, CodeLegend[] models) throws Exception {
    mockMvc.perform(get(String.format("/legend/%s", index)))
        .andDo(document(String.format("legend/%s", index),
            codeDescriptionFieldsSnippet(index, title, models)));
  }

  @Test
  public void documentateCodes() throws Exception {
    printDocument("os-type", "Mobile Device OS Type", DemoEnumCode.values());
  }
}
