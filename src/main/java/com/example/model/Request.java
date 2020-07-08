package com.example.model;

import java.util.Objects;

public class Request {
  String foo;

  public String getFoo() {
    return foo;
  }

  public void setFoo(String foo) {
    this.foo = foo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Request request = (Request) o;
    return Objects.equals(foo, request.foo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(foo);
  }
}
