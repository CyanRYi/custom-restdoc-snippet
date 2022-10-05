package tech.sollabs.demo;

import tech.sollabs.testsupport.snippets.CodeLegend;

public enum DemoEnumCode implements CodeLegend {

  ANDROID(1, "Android"),
  IOS(2, "iOS");

  private final int code;
  private final String description;

  DemoEnumCode(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }


  @Override
  public String getCodeName() {
    return name();
  }

  @Override
  public String getDescription() {
    return description;
  }
}
