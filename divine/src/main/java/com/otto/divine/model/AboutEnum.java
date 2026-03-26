package com.otto.divine.model;

public enum AboutEnum {
  LOVE("Love"),
  CAREER("Career"),
  HEALTH("Health"),
  CUSTOM("Custom"),
  ALL("總結");

  private String translation;

  AboutEnum(String translation) {
    this.translation = translation;
  }

  public String getabout() {
    return translation;
  }

  public void setabout(String translation) {
    this.translation = translation;
  }
}
