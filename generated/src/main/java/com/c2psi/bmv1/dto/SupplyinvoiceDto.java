package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A Supply invoice in the system
 */
@ApiModel(description = "A Supply invoice in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class SupplyinvoiceDto   {
  @JsonProperty("siCode")
  private String siCode;

  @JsonProperty("siComment")
  private String siComment;

  @JsonProperty("siPicture")
  private String siPicture;

  @JsonProperty("siDeliverydate")
  private String siDeliverydate;

  @JsonProperty("siInvoicingdate")
  private String siInvoicingdate;

  @JsonProperty("siTotalcolis")
  private Double siTotalcolis;

  @JsonProperty("siExpectedamount")
  private Double siExpectedamount;

  @JsonProperty("siPaidamount")
  private Double siPaidamount;

  /**
   * Gets or Sets siPaymentmethod
   */
  public enum SiPaymentmethodEnum {
    CASH("Cash"),
    
    MOMO("Momo"),
    
    OM("Om");

    private String value;

    SiPaymentmethodEnum(String value) {
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
    public static SiPaymentmethodEnum fromValue(String value) {
      for (SiPaymentmethodEnum b : SiPaymentmethodEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("siPaymentmethod")
  private SiPaymentmethodEnum siPaymentmethod;

  @JsonProperty("siPos")
  private PointofsaleDto siPos;

  public SupplyinvoiceDto siCode(String siCode) {
    this.siCode = siCode;
    return this;
  }

  /**
   * Get siCode
   * @return siCode
  */
  @ApiModelProperty(value = "")


  public String getSiCode() {
    return siCode;
  }

  public void setSiCode(String siCode) {
    this.siCode = siCode;
  }

  public SupplyinvoiceDto siComment(String siComment) {
    this.siComment = siComment;
    return this;
  }

  /**
   * Get siComment
   * @return siComment
  */
  @ApiModelProperty(value = "")


  public String getSiComment() {
    return siComment;
  }

  public void setSiComment(String siComment) {
    this.siComment = siComment;
  }

  public SupplyinvoiceDto siPicture(String siPicture) {
    this.siPicture = siPicture;
    return this;
  }

  /**
   * Get siPicture
   * @return siPicture
  */
  @ApiModelProperty(value = "")


  public String getSiPicture() {
    return siPicture;
  }

  public void setSiPicture(String siPicture) {
    this.siPicture = siPicture;
  }

  public SupplyinvoiceDto siDeliverydate(String siDeliverydate) {
    this.siDeliverydate = siDeliverydate;
    return this;
  }

  /**
   * Get siDeliverydate
   * @return siDeliverydate
  */
  @ApiModelProperty(value = "")


  public String getSiDeliverydate() {
    return siDeliverydate;
  }

  public void setSiDeliverydate(String siDeliverydate) {
    this.siDeliverydate = siDeliverydate;
  }

  public SupplyinvoiceDto siInvoicingdate(String siInvoicingdate) {
    this.siInvoicingdate = siInvoicingdate;
    return this;
  }

  /**
   * Get siInvoicingdate
   * @return siInvoicingdate
  */
  @ApiModelProperty(value = "")


  public String getSiInvoicingdate() {
    return siInvoicingdate;
  }

  public void setSiInvoicingdate(String siInvoicingdate) {
    this.siInvoicingdate = siInvoicingdate;
  }

  public SupplyinvoiceDto siTotalcolis(Double siTotalcolis) {
    this.siTotalcolis = siTotalcolis;
    return this;
  }

  /**
   * Get siTotalcolis
   * @return siTotalcolis
  */
  @ApiModelProperty(value = "")


  public Double getSiTotalcolis() {
    return siTotalcolis;
  }

  public void setSiTotalcolis(Double siTotalcolis) {
    this.siTotalcolis = siTotalcolis;
  }

  public SupplyinvoiceDto siExpectedamount(Double siExpectedamount) {
    this.siExpectedamount = siExpectedamount;
    return this;
  }

  /**
   * Get siExpectedamount
   * @return siExpectedamount
  */
  @ApiModelProperty(value = "")


  public Double getSiExpectedamount() {
    return siExpectedamount;
  }

  public void setSiExpectedamount(Double siExpectedamount) {
    this.siExpectedamount = siExpectedamount;
  }

  public SupplyinvoiceDto siPaidamount(Double siPaidamount) {
    this.siPaidamount = siPaidamount;
    return this;
  }

  /**
   * Get siPaidamount
   * @return siPaidamount
  */
  @ApiModelProperty(value = "")


  public Double getSiPaidamount() {
    return siPaidamount;
  }

  public void setSiPaidamount(Double siPaidamount) {
    this.siPaidamount = siPaidamount;
  }

  public SupplyinvoiceDto siPaymentmethod(SiPaymentmethodEnum siPaymentmethod) {
    this.siPaymentmethod = siPaymentmethod;
    return this;
  }

  /**
   * Get siPaymentmethod
   * @return siPaymentmethod
  */
  @ApiModelProperty(value = "")


  public SiPaymentmethodEnum getSiPaymentmethod() {
    return siPaymentmethod;
  }

  public void setSiPaymentmethod(SiPaymentmethodEnum siPaymentmethod) {
    this.siPaymentmethod = siPaymentmethod;
  }

  public SupplyinvoiceDto siPos(PointofsaleDto siPos) {
    this.siPos = siPos;
    return this;
  }

  /**
   * Get siPos
   * @return siPos
  */
  @ApiModelProperty(value = "")

  @Valid

  public PointofsaleDto getSiPos() {
    return siPos;
  }

  public void setSiPos(PointofsaleDto siPos) {
    this.siPos = siPos;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SupplyinvoiceDto supplyinvoiceDto = (SupplyinvoiceDto) o;
    return Objects.equals(this.siCode, supplyinvoiceDto.siCode) &&
        Objects.equals(this.siComment, supplyinvoiceDto.siComment) &&
        Objects.equals(this.siPicture, supplyinvoiceDto.siPicture) &&
        Objects.equals(this.siDeliverydate, supplyinvoiceDto.siDeliverydate) &&
        Objects.equals(this.siInvoicingdate, supplyinvoiceDto.siInvoicingdate) &&
        Objects.equals(this.siTotalcolis, supplyinvoiceDto.siTotalcolis) &&
        Objects.equals(this.siExpectedamount, supplyinvoiceDto.siExpectedamount) &&
        Objects.equals(this.siPaidamount, supplyinvoiceDto.siPaidamount) &&
        Objects.equals(this.siPaymentmethod, supplyinvoiceDto.siPaymentmethod) &&
        Objects.equals(this.siPos, supplyinvoiceDto.siPos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(siCode, siComment, siPicture, siDeliverydate, siInvoicingdate, siTotalcolis, siExpectedamount, siPaidamount, siPaymentmethod, siPos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SupplyinvoiceDto {\n");
    
    sb.append("    siCode: ").append(toIndentedString(siCode)).append("\n");
    sb.append("    siComment: ").append(toIndentedString(siComment)).append("\n");
    sb.append("    siPicture: ").append(toIndentedString(siPicture)).append("\n");
    sb.append("    siDeliverydate: ").append(toIndentedString(siDeliverydate)).append("\n");
    sb.append("    siInvoicingdate: ").append(toIndentedString(siInvoicingdate)).append("\n");
    sb.append("    siTotalcolis: ").append(toIndentedString(siTotalcolis)).append("\n");
    sb.append("    siExpectedamount: ").append(toIndentedString(siExpectedamount)).append("\n");
    sb.append("    siPaidamount: ").append(toIndentedString(siPaidamount)).append("\n");
    sb.append("    siPaymentmethod: ").append(toIndentedString(siPaymentmethod)).append("\n");
    sb.append("    siPos: ").append(toIndentedString(siPos)).append("\n");
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

