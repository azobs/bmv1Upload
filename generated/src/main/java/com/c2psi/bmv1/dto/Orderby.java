package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Different sort criteria
 */
@ApiModel(description = "Different sort criteria")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T17:01:56.543198200+01:00[Africa/Douala]")
public class Orderby   {
  @JsonProperty("sortColumn")
  private String sortColumn;

  /**
   * Gets or Sets sortDirection
   */
  public enum SortDirectionEnum {
    ASC("ASC"),
    
    DESC("DESC");

    private String value;

    SortDirectionEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SortDirectionEnum fromValue(String value) {
      for (SortDirectionEnum b : SortDirectionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("sortDirection")
  private SortDirectionEnum sortDirection;

  public Orderby sortColumn(String sortColumn) {
    this.sortColumn = sortColumn;
    return this;
  }

  /**
   * Get sortColumn
   * @return sortColumn
  */
  @ApiModelProperty(value = "")


  public String getSortColumn() {
    return sortColumn;
  }

  public void setSortColumn(String sortColumn) {
    this.sortColumn = sortColumn;
  }

  public Orderby sortDirection(SortDirectionEnum sortDirection) {
    this.sortDirection = sortDirection;
    return this;
  }

  /**
   * Get sortDirection
   * @return sortDirection
  */
  @ApiModelProperty(value = "")


  public SortDirectionEnum getSortDirection() {
    return sortDirection;
  }

  public void setSortDirection(SortDirectionEnum sortDirection) {
    this.sortDirection = sortDirection;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Orderby orderby = (Orderby) o;
    return Objects.equals(this.sortColumn, orderby.sortColumn) &&
        Objects.equals(this.sortDirection, orderby.sortDirection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sortColumn, sortDirection);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Orderby {\n");
    
    sb.append("    sortColumn: ").append(toIndentedString(sortColumn)).append("\n");
    sb.append("    sortDirection: ").append(toIndentedString(sortDirection)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

