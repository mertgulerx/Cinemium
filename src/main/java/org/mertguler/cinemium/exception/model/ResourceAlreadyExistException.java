package org.mertguler.cinemium.exception.model;

public class ResourceAlreadyExistException extends RuntimeException {
  String resourceName;
  String field;
  String fieldName;
  Long fieldId;

  public ResourceAlreadyExistException() {
  }

  public ResourceAlreadyExistException(String resourceName, String field, String fieldName) {
    super(String.format("%s already exist with %s: %s", resourceName, field, fieldName));
    this.resourceName = resourceName;
    this.field = field;
    this.fieldName = fieldName;
  }

  public ResourceAlreadyExistException(String resourceName, String field, Long fieldId) {
    super(String.format("%s already exist with  %s: %d", resourceName, field, fieldId));
    this.resourceName = resourceName;
    this.field = field;
    this.fieldId = fieldId;
  }
}
