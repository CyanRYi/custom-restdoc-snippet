package tech.sollabs.testsupport;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.HashMap;
import java.util.Map;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.mustache.Mustache;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.restdocs.templates.TemplateEngine;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.restdocs.templates.mustache.AsciidoctorTableCellContentLambda;
import org.springframework.restdocs.templates.mustache.MustacheTemplateEngine;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

public class MockMvcSupport {

  public static MockMvc createMockMvc(WebApplicationContext ctx,
      RestDocumentationContextProvider documentation) {
    return MockMvcBuilders.webAppContextSetup(ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true))
        .apply(documentationConfiguration(documentation).templateEngine(createTemplateEngine()))
        .apply(springSecurity())
        .alwaysDo(print())
        .build();
  }

  public static MockMvc createDocumentMockMvc(
      RestDocumentationContextProvider documentation) {
    return MockMvcBuilders.standaloneSetup()
        .addFilters(new CharacterEncodingFilter("UTF-8", true))
        .apply(MockMvcRestDocumentation.documentationConfiguration(documentation)
            .snippets()
            .withDefaults()
            .and()
            .templateEngine(createTemplateEngine()))
        .alwaysDo(MockMvcResultHandlers.print())
        .build();
  }

  // asciidoctor 사용을 전제로 함.
  private static TemplateEngine createTemplateEngine() {
    Map<String, Object> templateContext = new HashMap<>();
    templateContext.put("tableCellContent", new AsciidoctorTableCellContentLambda());

    return new MustacheTemplateEngine(
        new CustomTemplateResourceResolver(TemplateFormats.asciidoctor()),
        Mustache.compiler().escapeHTML(false), templateContext);
  }

  static OperationRequestPreprocessor getDocumentRequest() {
    return preprocessRequest(prettyPrint());
  }

  static OperationResponsePreprocessor getDocumentResponse() {
    return preprocessResponse(prettyPrint());
  }

  public static RestDocumentationResultHandler documentWithFields(String identifier,
      Snippet... snippets) {
    return document(identifier,
        getDocumentRequest(),
        getDocumentResponse(),
        snippets);
  }
}
