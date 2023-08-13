package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.PointofsaleDto;
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
 * A Sale invoice in the system
 */
@ApiModel(description = "A Sale invoice in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-13T03:59:42.033168+01:00[Africa/Douala]")
public class SaleinvoiceDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("siCode")
  private String siCode;

  @JsonProperty("siComment")
  private String siComment;

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

  @JsonProperty("siPos")
  private PointofsaleDto siPos;

  public SaleinvoiceDto id(Long id) {
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

  public SaleinvoiceDto siCode(String siCode) {
    this.siCode = siCode;
    return this;
  }

  /**
   * Get siCode
   * @return siCode
  */
  @ApiModelProperty(example = "0", value = "")


  public String getSiCode() {
    return siCode;
  }

  public void setSiCode(String siCode) {
    this.siCode = siCode;
  }

  public SaleinvoiceDto siComment(String siComment) {
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

  public SaleinvoiceDto siInvoicingdate(OffsetDateTime siInvoicingdate) {
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

  public SaleinvoiceDto siTotalcolis(Double siTotalcolis) {
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

  public SaleinvoiceDto siExpectedamount(Double siExpectedamount) {
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

  public SaleinvoiceDto siPaidamount(Double siPaidamount) {
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

  public SaleinvoiceDto siPaymentmethod(SiPaymentmethodEnum siPaymentmethod) {
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

  public SaleinvoiceDto siPos(PointofsaleDto siPos) {
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
    SaleinvoiceDto saleinvoiceDto = (SaleinvoiceDto) o;
    return Objects.equals(this.id, saleinvoiceDto.id) &&
        Objects.equals(this.siCode, saleinvoiceDto.siCode) &&
        Objects.equals(this.siComment, saleinvoiceDto.siComment) &&
        Objects.equals(this.siInvoicingdate, saleinvoiceDto.siInvoicingdate) &&
        Objects.equals(this.siTotalcolis, saleinvoiceDto.siTotalcolis) &&
        Objects.equals(this.siExpectedamount, saleinvoiceDto.siExpectedamount) &&
        Objects.equals(this.siPaidamount, saleinvoiceDto.siPaidamount) &&
        Objects.equals(this.siPaymentmethod, saleinvoiceDto.siPaymentmethod) &&
        Objects.equals(this.siPos, saleinvoiceDto.siPos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, siCode, siComment, siInvoicingdate, siTotalcolis, siExpectedamount, siPaidamount, siPaymentmethod, siPos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SaleinvoiceDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    siCode: ").append(toIndentedString(siCode)).append("\n");
    sb.append("    siComment: ").append(toIndentedString(siComment)).append("\n");
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

