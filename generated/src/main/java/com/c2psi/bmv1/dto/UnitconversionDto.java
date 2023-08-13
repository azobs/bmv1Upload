package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.UnitDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-13T03:59:42.033168+01:00[Africa/Douala]")
public class UnitconversionDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("conversionFactor")
  private Double conversionFactor;

  @JsonProperty("unitSource")
  private UnitDto unitSource;

  @JsonProperty("unitDestination")
  private UnitDto unitDestination;

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

  public UnitconversionDto unitSource(UnitDto unitSource) {
    this.unitSource = unitSource;
    return this;
  }

  /**
   * Get unitSource
   * @return unitSource
  */
  @ApiModelProperty(value = "")

  @Valid

  public UnitDto getUnitSource() {
    return unitSource;
  }

  public void setUnitSource(UnitDto unitSource) {
    this.unitSource = unitSource;
  }

  public UnitconversionDto unitDestination(UnitDto unitDestination) {
    this.unitDestination = unitDestination;
    return this;
  }

  /**
   * Get unitDestination
   * @return unitDestination
  */
  @ApiModelProperty(value = "")

  @Valid

  public UnitDto getUnitDestination() {
    return unitDestination;
  }

  public void setUnitDestination(UnitDto unitDestination) {
    this.unitDestination = unitDestination;
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
        Objects.equals(this.unitSource, unitconversionDto.unitSource) &&
        Objects.equals(this.unitDestination, unitconversionDto.unitDestination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, conversionFactor, unitSource, unitDestination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnitconversionDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    conversionFactor: ").append(toIndentedString(conversionFactor)).append("\n");
    sb.append("    unitSource: ").append(toIndentedString(unitSource)).append("\n");
    sb.append("    unitDestination: ").append(toIndentedString(unitDestination)).append("\n");
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

