package org.mertguler.cinemium.exception;

public class ResourceAlreadyExistException extends RuntimeException {
  String resourceName;
  String field;
  String fieldName;
  Number fieldId;

  Integer rowIndex;
  Integer columnIndex;

  public ResourceAlreadyExistException() {
  }

  public ResourceAlreadyExistException(String resourceName, String field, String fieldName) {
    super(String.format("%s already exist with %s: %s", resourceName, field, fieldName));
    this.resourceName = resourceName;
    this.field = field;
    this.fieldName = fieldName;
  }

  public ResourceAlreadyExistException(String resourceName, String field, Number fieldId) {
    super(String.format("%s already exist with  %s: %d", resourceName, field, fieldId));
    this.resourceName = resourceName;
    this.field = field;
    this.fieldId = fieldId;
  }

  /**
   * Only for Seats
   * @param resourceName
   * @param rowIndex
   * @param columnIndex
   */
  public ResourceAlreadyExistException(String resourceName, Integer rowIndex, Integer columnIndex) {
    super(String.format("%s already exist with Row Index: %d and Column Index: %d", resourceName, rowIndex, columnIndex));
    this.resourceName = resourceName;
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
  }
}
