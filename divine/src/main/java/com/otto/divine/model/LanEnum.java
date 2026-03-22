package com.otto.divine.model;

public enum LanEnum {
  English("translate to English"),
  Chinese("用中文回答");

  private final String translation;

  LanEnum(String translation) {
    this.translation = translation;
  }

  public String getTranslation() {
    return translation;
  }
}
