package tech.sollabs.demo;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @GetMapping(path = "/test")
  public Map<String, Object> getResource() {
    return Map.of("key1", "value1",
        "key2", "value2",
        "code", DemoEnumCode.IOS);
  }
}
