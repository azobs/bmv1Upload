package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.ArticleDto;
import com.c2psi.bmv1.dto.SupplyinvoiceDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An arrival in the system
 */
@ApiModel(description = "An arrival in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-12T06:34:45.513039400+01:00[Africa/Douala]")
public class ArrivalDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("deliveryQuantity")
  private Double deliveryQuantity;

  @JsonProperty("arrivalDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime arrivalDate;

  @JsonProperty("arrivalUnitprice")
  private Double arrivalUnitprice;

  /**
   * Gets or Sets arrivalType
   */
  public enum ArrivalTypeEnum {
    STANDARD("Standard"),
    
    DIVERS("Divers");

    private String value;

    ArrivalTypeEnum(String value) {
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
    public static ArrivalTypeEnum fromValue(String value) {
      for (ArrivalTypeEnum b : ArrivalTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("arrivalType")
  private ArrivalTypeEnum arrivalType;

  /**
   * Gets or Sets arrivalNature
   */
  public enum ArrivalNatureEnum {
    CASH("Cash"),
    
    COVER("Cover"),
    
    DAMAGE("Damage");

    private String value;

    ArrivalNatureEnum(String value) {
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
    public static ArrivalNatureEnum fromValue(String value) {
      for (ArrivalNatureEnum b : ArrivalNatureEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("arrivalNature")
  private ArrivalNatureEnum arrivalNature;

  @JsonProperty("arrivalArticle")
  private ArticleDto arrivalArticle;

  @JsonProperty("arrivalInvoice")
  private SupplyinvoiceDto arrivalInvoice;

  public ArrivalDto id(Long id) {
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

  public ArrivalDto deliveryQuantity(Double deliveryQuantity) {
    this.deliveryQuantity = deliveryQuantity;
    return this;
  }

  /**
   * Get deliveryQuantity
   * minimum: 0
   * @return deliveryQuantity
  */
  @ApiModelProperty(example = "1", value = "")

@DecimalMin(value = "0", inclusive = false) 
  public Double getDeliveryQuantity() {
    return deliveryQuantity;
  }

  public void setDeliveryQuantity(Double deliveryQuantity) {
    this.deliveryQuantity = deliveryQuantity;
  }

  public ArrivalDto arrivalDate(OffsetDateTime arrivalDate) {
    this.arrivalDate = arrivalDate;
    return this;
  }

  /**
   * Get arrivalDate
   * @return arrivalDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(OffsetDateTime arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public ArrivalDto arrivalUnitprice(Double arrivalUnitprice) {
    this.arrivalUnitprice = arrivalUnitprice;
    return this;
  }

  /**
   * Get arrivalUnitprice
   * minimum: 0
   * @return arrivalUnitprice
  */
  @ApiModelProperty(example = "1", value = "")

@DecimalMin(value = "0", inclusive = false) 
  public Double getArrivalUnitprice() {
    return arrivalUnitprice;
  }

  public void setArrivalUnitprice(Double arrivalUnitprice) {
    this.arrivalUnitprice = arrivalUnitprice;
  }

  public ArrivalDto arrivalType(ArrivalTypeEnum arrivalType) {
    this.arrivalType = arrivalType;
    return this;
  }

  /**
   * Get arrivalType
   * @return arrivalType
  */
  @ApiModelProperty(example = "Standard", value = "")


  public ArrivalTypeEnum getArrivalType() {
    return arrivalType;
  }

  public void setArrivalType(ArrivalTypeEnum arrivalType) {
    this.arrivalType = arrivalType;
  }

  public ArrivalDto arrivalNature(ArrivalNatureEnum arrivalNature) {
    this.arrivalNature = arrivalNature;
    return this;
  }

  /**
   * Get arrivalNature
   * @return arrivalNature
  */
  @ApiModelProperty(example = "Cash", value = "")


  public ArrivalNatureEnum getArrivalNature() {
    return arrivalNature;
  }

  public void setArrivalNature(ArrivalNatureEnum arrivalNature) {
    this.arrivalNature = arrivalNature;
  }

  public ArrivalDto arrivalArticle(ArticleDto arrivalArticle) {
    this.arrivalArticle = arrivalArticle;
    return this;
  }

  /**
   * Get arrivalArticle
   * @return arrivalArticle
  */
  @ApiModelProperty(value = "")

  @Valid

  public ArticleDto getArrivalArticle() {
    return arrivalArticle;
  }

  public void setArrivalArticle(ArticleDto arrivalArticle) {
    this.arrivalArticle = arrivalArticle;
  }

  public ArrivalDto arrivalInvoice(SupplyinvoiceDto arrivalInvoice) {
    this.arrivalInvoice = arrivalInvoice;
    return this;
  }

  /**
   * Get arrivalInvoice
   * @return arrivalInvoice
  */
  @ApiModelProperty(value = "")

  @Valid

  public SupplyinvoiceDto getArrivalInvoice() {
    return arrivalInvoice;
  }

  public void setArrivalInvoice(SupplyinvoiceDto arrivalInvoice) {
    this.arrivalInvoice = arrivalInvoice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArrivalDto arrivalDto = (ArrivalDto) o;
    return Objects.equals(this.id, arrivalDto.id) &&
        Objects.equals(this.deliveryQuantity, arrivalDto.deliveryQuantity) &&
        Objects.equals(this.arrivalDate, arrivalDto.arrivalDate) &&
        Objects.equals(this.arrivalUnitprice, arrivalDto.arrivalUnitprice) &&
        Objects.equals(this.arrivalType, arrivalDto.arrivalType) &&
        Objects.equals(this.arrivalNature, arrivalDto.arrivalNature) &&
        Objects.equals(this.arrivalArticle, arrivalDto.arrivalArticle) &&
        Objects.equals(this.arrivalInvoice, arrivalDto.arrivalInvoice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, deliveryQuantity, arrivalDate, arrivalUnitprice, arrivalType, arrivalNature, arrivalArticle, arrivalInvoice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArrivalDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    deliveryQuantity: ").append(toIndentedString(deliveryQuantity)).append("\n");
    sb.append("    arrivalDate: ").append(toIndentedString(arrivalDate)).append("\n");
    sb.append("    arrivalUnitprice: ").append(toIndentedString(arrivalUnitprice)).append("\n");
    sb.append("    arrivalType: ").append(toIndentedString(arrivalType)).append("\n");
    sb.append("    arrivalNature: ").append(toIndentedString(arrivalNature)).append("\n");
    sb.append("    arrivalArticle: ").append(toIndentedString(arrivalArticle)).append("\n");
    sb.append("    arrivalInvoice: ").append(toIndentedString(arrivalInvoice)).append("\n");
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

