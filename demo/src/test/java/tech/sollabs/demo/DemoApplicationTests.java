package tech.sollabs.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import tech.sollabs.testsupport.MockMvcSupport;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.sollabs.tech")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class DemoApplicationTests {

  protected MockMvc mockMvc;

  protected WebApplicationContext context;

  @BeforeEach
  public void setUp(RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcSupport.createMockMvc(this.context, restDocumentation);
  }

  @Test
  void contextLoads() {
  }

  @Autowired
  public void setContext(WebApplicationContext context) {
    this.context = context;
  }
}
