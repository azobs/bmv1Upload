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
 * Different filter used to filter the userbm result list
 */
@ApiModel(description = "Different filter used to filter the userbm result list")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T16:08:25.464702700+01:00[Africa/Douala]")
public class Filter   {
  @JsonProperty("filterColumn")
  private String filterColumn;

  @JsonProperty("filterValue")
  private String filterValue;

  /**
   * Gets or Sets filterOperator
   */
  public enum FilterOperatorEnum {
    EQUAL("EQUAL"),
    
    LIKE("LIKE"),
    
    GREATER_THAN("GREATER THAN"),
    
    GREATER_OR_EQUAL("GREATER OR EQUAL"),
    
    LOWER_THAN("LOWER THAN"),
    
    LOWER_OR_EQUAL("LOWER OR EQUAL"),
    
    BETWEEN("BETWEEN");

    private String value;

    FilterOperatorEnum(String value) {
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
    public static FilterOperatorEnum fromValue(String value) {
      for (FilterOperatorEnum b : FilterOperatorEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("filterOperator")
  private FilterOperatorEnum filterOperator;

  public Filter filterColumn(String filterColumn) {
    this.filterColumn = filterColumn;
    return this;
  }

  /**
   * Get filterColumn
   * @return filterColumn
  */
  @ApiModelProperty(value = "")


  public String getFilterColumn() {
    return filterColumn;
  }

  public void setFilterColumn(String filterColumn) {
    this.filterColumn = filterColumn;
  }

  public Filter filterValue(String filterValue) {
    this.filterValue = filterValue;
    return this;
  }

  /**
   * Get filterValue
   * @return filterValue
  */
  @ApiModelProperty(value = "")


  public String getFilterValue() {
    return filterValue;
  }

  public void setFilterValue(String filterValue) {
    this.filterValue = filterValue;
  }

  public Filter filterOperator(FilterOperatorEnum filterOperator) {
    this.filterOperator = filterOperator;
    return this;
  }

  /**
   * Get filterOperator
   * @return filterOperator
  */
  @ApiModelProperty(example = "EQUAL", value = "")


  public FilterOperatorEnum getFilterOperator() {
    return filterOperator;
  }

  public void setFilterOperator(FilterOperatorEnum filterOperator) {
    this.filterOperator = filterOperator;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Filter filter = (Filter) o;
    return Objects.equals(this.filterColumn, filter.filterColumn) &&
        Objects.equals(this.filterValue, filter.filterValue) &&
        Objects.equals(this.filterOperator, filter.filterOperator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filterColumn, filterValue, filterOperator);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Filter {\n");
    
    sb.append("    filterColumn: ").append(toIndentedString(filterColumn)).append("\n");
    sb.append("    filterValue: ").append(toIndentedString(filterValue)).append("\n");
    sb.append("    filterOperator: ").append(toIndentedString(filterOperator)).append("\n");
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

