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
 * A selling unit of product formated in the system
 */
@ApiModel(description = "A selling unit of product formated in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-05T20:37:01.434321300+01:00[Africa/Casablanca]")
public class UnitDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("unitName")
  private String unitName;

  @JsonProperty("unitAbbreviation")
  private String unitAbbreviation;

  @JsonProperty("unitPosId")
  private Long unitPosId;

  public UnitDto id(Long id) {
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

  public UnitDto unitName(String unitName) {
    this.unitName = unitName;
    return this;
  }

  /**
   * Get unitName
   * @return unitName
  */
  @ApiModelProperty(example = "unitName", value = "")


  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public UnitDto unitAbbreviation(String unitAbbreviation) {
    this.unitAbbreviation = unitAbbreviation;
    return this;
  }

  /**
   * Get unitAbbreviation
   * @return unitAbbreviation
  */
  @ApiModelProperty(example = "UN", value = "")


  public String getUnitAbbreviation() {
    return unitAbbreviation;
  }

  public void setUnitAbbreviation(String unitAbbreviation) {
    this.unitAbbreviation = unitAbbreviation;
  }

  public UnitDto unitPosId(Long unitPosId) {
    this.unitPosId = unitPosId;
    return this;
  }

  /**
   * Get unitPosId
   * @return unitPosId
  */
  @ApiModelProperty(value = "")


  public Long getUnitPosId() {
    return unitPosId;
  }

  public void setUnitPosId(Long unitPosId) {
    this.unitPosId = unitPosId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnitDto unitDto = (UnitDto) o;
    return Objects.equals(this.id, unitDto.id) &&
        Objects.equals(this.unitName, unitDto.unitName) &&
        Objects.equals(this.unitAbbreviation, unitDto.unitAbbreviation) &&
        Objects.equals(this.unitPosId, unitDto.unitPosId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, unitName, unitAbbreviation, unitPosId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnitDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    unitName: ").append(toIndentedString(unitName)).append("\n");
    sb.append("    unitAbbreviation: ").append(toIndentedString(unitAbbreviation)).append("\n");
    sb.append("    unitPosId: ").append(toIndentedString(unitPosId)).append("\n");
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

