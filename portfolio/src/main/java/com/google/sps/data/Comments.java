package com.google.sps.data;

/** An item on a todo list. */
public final class Comments {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final String phone;
  private final String message;
  //private final Integer num;

  public Comments(String firstName, String lastName, String email, String phone, String message) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.message = message;
    //this.num = num;
  }
}