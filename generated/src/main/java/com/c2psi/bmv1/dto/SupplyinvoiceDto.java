package com.c2psi.bmv1.dto;

import java.util.Objects;
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
 * A Supply invoice in the system
 */
@ApiModel(description = "A Supply invoice in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-05T20:37:01.434321300+01:00[Africa/Casablanca]")
public class SupplyinvoiceDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("siCode")
  private String siCode;

  @JsonProperty("siComment")
  private String siComment;

  @JsonProperty("siPicture")
  private String siPicture;

  @JsonProperty("siDeliverydate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime siDeliverydate;

  @JsonProperty("siInvoicingdate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime siInvoicingdate;

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

  @JsonProperty("siPosId")
  private Long siPosId;

  @JsonProperty("siProviderId")
  private Long siProviderId;

  public SupplyinvoiceDto id(Long id) {
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

  public SupplyinvoiceDto siCode(String siCode) {
    this.siCode = siCode;
    return this;
  }

  /**
   * Get siCode
   * @return siCode
  */
  @ApiModelProperty(example = "SuI0000", value = "")


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

  public SupplyinvoiceDto siDeliverydate(OffsetDateTime siDeliverydate) {
    this.siDeliverydate = siDeliverydate;
    return this;
  }

  /**
   * Get siDeliverydate
   * @return siDeliverydate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getSiDeliverydate() {
    return siDeliverydate;
  }

  public void setSiDeliverydate(OffsetDateTime siDeliverydate) {
    this.siDeliverydate = siDeliverydate;
  }

  public SupplyinvoiceDto siInvoicingdate(OffsetDateTime siInvoicingdate) {
    this.siInvoicingdate = siInvoicingdate;
    return this;
  }

  /**
   * Get siInvoicingdate
   * @return siInvoicingdate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getSiInvoicingdate() {
    return siInvoicingdate;
  }

  public void setSiInvoicingdate(OffsetDateTime siInvoicingdate) {
    this.siInvoicingdate = siInvoicingdate;
  }

  public SupplyinvoiceDto siTotalcolis(Double siTotalcolis) {
    this.siTotalcolis = siTotalcolis;
    return this;
  }

  /**
   * Get siTotalcolis
   * minimum: 1
   * @return siTotalcolis
  */
  @ApiModelProperty(example = "1", value = "")

@DecimalMin("1") 
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
   * minimum: 0
   * @return siExpectedamount
  */
  @ApiModelProperty(value = "")

@DecimalMin(value = "0", inclusive = false) 
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
   * minimum: 0
   * @return siPaidamount
  */
  @ApiModelProperty(value = "")

@DecimalMin("0") 
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
  @ApiModelProperty(example = "Cash", value = "")


  public SiPaymentmethodEnum getSiPaymentmethod() {
    return siPaymentmethod;
  }

  public void setSiPaymentmethod(SiPaymentmethodEnum siPaymentmethod) {
    this.siPaymentmethod = siPaymentmethod;
  }

  public SupplyinvoiceDto siPosId(Long siPosId) {
    this.siPosId = siPosId;
    return this;
  }

  /**
   * Get siPosId
   * @return siPosId
  */
  @ApiModelProperty(value = "")


  public Long getSiPosId() {
    return siPosId;
  }

  public void setSiPosId(Long siPosId) {
    this.siPosId = siPosId;
  }

  public SupplyinvoiceDto siProviderId(Long siProviderId) {
    this.siProviderId = siProviderId;
    return this;
  }

  /**
   * Get siProviderId
   * @return siProviderId
  */
  @ApiModelProperty(value = "")


  public Long getSiProviderId() {
    return siProviderId;
  }

  public void setSiProviderId(Long siProviderId) {
    this.siProviderId = siProviderId;
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
    return Objects.equals(this.id, supplyinvoiceDto.id) &&
        Objects.equals(this.siCode, supplyinvoiceDto.siCode) &&
        Objects.equals(this.siComment, supplyinvoiceDto.siComment) &&
        Objects.equals(this.siPicture, supplyinvoiceDto.siPicture) &&
        Objects.equals(this.siDeliverydate, supplyinvoiceDto.siDeliverydate) &&
        Objects.equals(this.siInvoicingdate, supplyinvoiceDto.siInvoicingdate) &&
        Objects.equals(this.siTotalcolis, supplyinvoiceDto.siTotalcolis) &&
        Objects.equals(this.siExpectedamount, supplyinvoiceDto.siExpectedamount) &&
        Objects.equals(this.siPaidamount, supplyinvoiceDto.siPaidamount) &&
        Objects.equals(this.siPaymentmethod, supplyinvoiceDto.siPaymentmethod) &&
        Objects.equals(this.siPosId, supplyinvoiceDto.siPosId) &&
        Objects.equals(this.siProviderId, supplyinvoiceDto.siProviderId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, siCode, siComment, siPicture, siDeliverydate, siInvoicingdate, siTotalcolis, siExpectedamount, siPaidamount, siPaymentmethod, siPosId, siProviderId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SupplyinvoiceDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    siCode: ").append(toIndentedString(siCode)).append("\n");
    sb.append("    siComment: ").append(toIndentedString(siComment)).append("\n");
    sb.append("    siPicture: ").append(toIndentedString(siPicture)).append("\n");
    sb.append("    siDeliverydate: ").append(toIndentedString(siDeliverydate)).append("\n");
    sb.append("    siInvoicingdate: ").append(toIndentedString(siInvoicingdate)).append("\n");
    sb.append("    siTotalcolis: ").append(toIndentedString(siTotalcolis)).append("\n");
    sb.append("    siExpectedamount: ").append(toIndentedString(siExpectedamount)).append("\n");
    sb.append("    siPaidamount: ").append(toIndentedString(siPaidamount)).append("\n");
    sb.append("    siPaymentmethod: ").append(toIndentedString(siPaymentmethod)).append("\n");
    sb.append("    siPosId: ").append(toIndentedString(siPosId)).append("\n");
    sb.append("    siProviderId: ").append(toIndentedString(siProviderId)).append("\n");
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

