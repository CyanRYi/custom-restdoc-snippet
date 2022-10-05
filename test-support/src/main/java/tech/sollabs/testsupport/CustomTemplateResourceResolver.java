package tech.sollabs.testsupport;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.restdocs.templates.StandardTemplateResourceResolver;
import org.springframework.restdocs.templates.TemplateFormat;

/**
 * @author Cyan
 * @since 3.3
 */
public class CustomTemplateResourceResolver extends StandardTemplateResourceResolver {

  private TemplateFormat templateFormat;

  public CustomTemplateResourceResolver(TemplateFormat templateFormat) {
    super(templateFormat);
    this.templateFormat = templateFormat;
  }

  @Override
  public Resource resolveTemplateResource(String name) {
    Resource formatSpecificCustomTemplate = this.getFormatSpecificCustomTemplate(name);
    if (formatSpecificCustomTemplate.exists()) {
      return formatSpecificCustomTemplate;
    } else {
      Resource customTemplate = this.getCustomTemplate(name);
      if (customTemplate.exists()) {
        return customTemplate;
      } else {
        Resource defaultTemplate = this.getDefaultTemplate(name);
        if (defaultTemplate.exists()) {
          return defaultTemplate;
        } else {
          return super.resolveTemplateResource(name);
        }
      }
    }
  }

  private Resource getFormatSpecificCustomTemplate(String name) {
    return new ClassPathResource(String.format("tech/sollabs/testsupport/templates/%s/%s.snippet", this.templateFormat.getId(), name));
  }

  private Resource getDefaultTemplate(String name) {
    return new ClassPathResource(String.format("tech/sollabs/testsupport/templates/%s/default-%s.snippet", this.templateFormat.getId(), name));
  }

  private Resource getCustomTemplate(String name) {
    return new ClassPathResource(String.format("tech/sollabs/testsupport/templates/%s.snippet", name));
  }
}
