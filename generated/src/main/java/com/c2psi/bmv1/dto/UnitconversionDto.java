package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A conversion rule used to convert one unit in another
 */
@ApiModel(description = "A conversion rule used to convert one unit in another")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-27T14:53:37.924409800+01:00[Africa/Casablanca]")
public class UnitconversionDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("conversionFactor")
  private Double conversionFactor;

  @JsonProperty("unitSourceId")
  private Long unitSourceId;

  @JsonProperty("unitDestinationId")
  private Long unitDestinationId;

  public UnitconversionDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UnitconversionDto conversionFactor(Double conversionFactor) {
    this.conversionFactor = conversionFactor;
    return this;
  }

  /**
   * Get conversionFactor
   * @return conversionFactor
  */
  @ApiModelProperty(value = "")


  public Double getConversionFactor() {
    return conversionFactor;
  }

  public void setConversionFactor(Double conversionFactor) {
    this.conversionFactor = conversionFactor;
  }

  public UnitconversionDto unitSourceId(Long unitSourceId) {
    this.unitSourceId = unitSourceId;
    return this;
  }

  /**
   * Get unitSourceId
   * @return unitSourceId
  */
  @ApiModelProperty(value = "")


  public Long getUnitSourceId() {
    return unitSourceId;
  }

  public void setUnitSourceId(Long unitSourceId) {
    this.unitSourceId = unitSourceId;
  }

  public UnitconversionDto unitDestinationId(Long unitDestinationId) {
    this.unitDestinationId = unitDestinationId;
    return this;
  }

  /**
   * Get unitDestinationId
   * @return unitDestinationId
  */
  @ApiModelProperty(value = "")


  public Long getUnitDestinationId() {
    return unitDestinationId;
  }

  public void setUnitDestinationId(Long unitDestinationId) {
    this.unitDestinationId = unitDestinationId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnitconversionDto unitconversionDto = (UnitconversionDto) o;
    return Objects.equals(this.id, unitconversionDto.id) &&
        Objects.equals(this.conversionFactor, unitconversionDto.conversionFactor) &&
        Objects.equals(this.unitSourceId, unitconversionDto.unitSourceId) &&
        Objects.equals(this.unitDestinationId, unitconversionDto.unitDestinationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, conversionFactor, unitSourceId, unitDestinationId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnitconversionDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    conversionFactor: ").append(toIndentedString(conversionFactor)).append("\n");
    sb.append("    unitSourceId: ").append(toIndentedString(unitSourceId)).append("\n");
    sb.append("    unitDestinationId: ").append(toIndentedString(unitDestinationId)).append("\n");
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

