package tech.sollabs.testsupport.snippets;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.restdocs.operation.Operation;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.restdocs.snippet.TemplatedSnippet;

/**
 * @author Cyan
 * @since 3.3
 */
public class CodeDescriptionFieldsSnippet extends TemplatedSnippet {

  private final String index;
  private final String title;
  private final CodeLegend[] codes;

  public CodeDescriptionFieldsSnippet(String index, String title, CodeLegend[] codes) {
    super("code-description", null);
    this.index = index;
    this.title = title;
    this.codes = codes;
  }

  public static Snippet codeDescriptionFieldsSnippet(String index, String title, CodeLegend[] codes) {
    return new CodeDescriptionFieldsSnippet(index, title, codes);
  }

  @Override
  protected Map<String, Object> createModel(Operation operation) {
    Map<String, Object> result = new LinkedHashMap<>();

    result.put("index", index);
    result.put("title", title);
    result.put("codes", codes);

    return result;
  }

}
