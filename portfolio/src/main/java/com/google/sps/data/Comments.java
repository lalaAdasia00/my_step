package com.google.sps.data;

/** An item on a todo list. */
public final class Comments {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final String phone;
  private final String message;
  private final long timestamp;

  public Comments(String firstName, String lastName, String email, String phone, String message, long timestamp) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.message = message;
    this.timestamp = timestamp;
  }
}