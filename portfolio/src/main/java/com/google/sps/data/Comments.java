package com.google.sps.data;

/** An item on a todo list. */
public final class Comments {

  private final String text;
  private final String text2;
  private final String email;
  private final String phone;
  private final String message;

  public Comments(String text, String text2, String email, String phone, String message) {
    this.text = text;
    this.text2 = text2;
    this.email = email;
    this.phone = phone;
    this.message = message;
  }
}