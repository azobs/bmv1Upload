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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T17:01:56.543198200+01:00[Africa/Douala]")
public class UnitconversionDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("conversionFactor")
  private Double conversionFactor;

  @JsonProperty("unitSourceDto")
  private UnitDto unitSourceDto;

  @JsonProperty("unitDestinationDto")
  private UnitDto unitDestinationDto;

  public UnitconversionDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(readOnly = true, value = "")


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

  public UnitconversionDto unitSourceDto(UnitDto unitSourceDto) {
    this.unitSourceDto = unitSourceDto;
    return this;
  }

  /**
   * Get unitSourceDto
   * @return unitSourceDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public UnitDto getUnitSourceDto() {
    return unitSourceDto;
  }

  public void setUnitSourceDto(UnitDto unitSourceDto) {
    this.unitSourceDto = unitSourceDto;
  }

  public UnitconversionDto unitDestinationDto(UnitDto unitDestinationDto) {
    this.unitDestinationDto = unitDestinationDto;
    return this;
  }

  /**
   * Get unitDestinationDto
   * @return unitDestinationDto
  */
  @ApiModelProperty(value = "")

  @Valid

  public UnitDto getUnitDestinationDto() {
    return unitDestinationDto;
  }

  public void setUnitDestinationDto(UnitDto unitDestinationDto) {
    this.unitDestinationDto = unitDestinationDto;
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
        Objects.equals(this.unitSourceDto, unitconversionDto.unitSourceDto) &&
        Objects.equals(this.unitDestinationDto, unitconversionDto.unitDestinationDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, conversionFactor, unitSourceDto, unitDestinationDto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnitconversionDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    conversionFactor: ").append(toIndentedString(conversionFactor)).append("\n");
    sb.append("    unitSourceDto: ").append(toIndentedString(unitSourceDto)).append("\n");
    sb.append("    unitDestinationDto: ").append(toIndentedString(unitDestinationDto)).append("\n");
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

