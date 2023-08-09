package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.dto.Page;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Model used to precise search criteria and sort criteria
 */
@ApiModel(description = "Model used to precise search criteria and sort criteria")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class FilterRequest   {
  @JsonProperty("filters")
  @Valid
  private List<Filter> filters = null;

  /**
   * Gets or Sets logicOperator
   */
  public enum LogicOperatorEnum {
    AND("AND"),
    
    OR("OR");

    private String value;

    LogicOperatorEnum(String value) {
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
    public static LogicOperatorEnum fromValue(String value) {
      for (LogicOperatorEnum b : LogicOperatorEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("logicOperator")
  private LogicOperatorEnum logicOperator;

  @JsonProperty("orderby")
  @Valid
  private List<Orderby> orderby = null;

  @JsonProperty("page")
  private Page page;

  public FilterRequest filters(List<Filter> filters) {
    this.filters = filters;
    return this;
  }

  public FilterRequest addFiltersItem(Filter filtersItem) {
    if (this.filters == null) {
      this.filters = new ArrayList<>();
    }
    this.filters.add(filtersItem);
    return this;
  }

  /**
   * Get filters
   * @return filters
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Filter> getFilters() {
    return filters;
  }

  public void setFilters(List<Filter> filters) {
    this.filters = filters;
  }

  public FilterRequest logicOperator(LogicOperatorEnum logicOperator) {
    this.logicOperator = logicOperator;
    return this;
  }

  /**
   * Get logicOperator
   * @return logicOperator
  */
  @ApiModelProperty(value = "")


  public LogicOperatorEnum getLogicOperator() {
    return logicOperator;
  }

  public void setLogicOperator(LogicOperatorEnum logicOperator) {
    this.logicOperator = logicOperator;
  }

  public FilterRequest orderby(List<Orderby> orderby) {
    this.orderby = orderby;
    return this;
  }

  public FilterRequest addOrderbyItem(Orderby orderbyItem) {
    if (this.orderby == null) {
      this.orderby = new ArrayList<>();
    }
    this.orderby.add(orderbyItem);
    return this;
  }

  /**
   * Get orderby
   * @return orderby
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Orderby> getOrderby() {
    return orderby;
  }

  public void setOrderby(List<Orderby> orderby) {
    this.orderby = orderby;
  }

  public FilterRequest page(Page page) {
    this.page = page;
    return this;
  }

  /**
   * Get page
   * @return page
  */
  @ApiModelProperty(value = "")

  @Valid

  public Page getPage() {
    return page;
  }

  public void setPage(Page page) {
    this.page = page;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FilterRequest filterRequest = (FilterRequest) o;
    return Objects.equals(this.filters, filterRequest.filters) &&
        Objects.equals(this.logicOperator, filterRequest.logicOperator) &&
        Objects.equals(this.orderby, filterRequest.orderby) &&
        Objects.equals(this.page, filterRequest.page);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filters, logicOperator, orderby, page);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FilterRequest {\n");
    
    sb.append("    filters: ").append(toIndentedString(filters)).append("\n");
    sb.append("    logicOperator: ").append(toIndentedString(logicOperator)).append("\n");
    sb.append("    orderby: ").append(toIndentedString(orderby)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
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

