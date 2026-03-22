package com.otto.divine.model;

public enum AboutEnum {
  LOVE("Love"),
  CAREER("Career"),
  HEALTH("Health"),
  ALL("總結");

  private final String translation;

  AboutEnum(String translation) {
    this.translation = translation;
  }

  public String getabout() {
    return translation;
  }
}
