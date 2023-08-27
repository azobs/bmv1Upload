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
 * A Sale in the system
 */
@ApiModel(description = "A Sale in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-27T14:53:37.924409800+01:00[Africa/Casablanca]")
public class SaleDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("saleQuantity")
  private Double saleQuantity;

  @JsonProperty("saleComment")
  private String saleComment;

  @JsonProperty("saleFinalprice")
  private Double saleFinalprice;

  /**
   * Gets or Sets saleType
   */
  public enum SaleTypeEnum {
    DETAILS("Details"),
    
    PERMUTATION("Permutation"),
    
    SEMIWHOLE("Semiwhole"),
    
    WHOLE("Whole");

    private String value;

    SaleTypeEnum(String value) {
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
    public static SaleTypeEnum fromValue(String value) {
      for (SaleTypeEnum b : SaleTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("saleType")
  private SaleTypeEnum saleType;

  @JsonProperty("saleCommandId")
  private Long saleCommandId;

  @JsonProperty("saleArticleId")
  private Long saleArticleId;

  public SaleDto id(Long id) {
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

  public SaleDto saleQuantity(Double saleQuantity) {
    this.saleQuantity = saleQuantity;
    return this;
  }

  /**
   * Get saleQuantity
   * @return saleQuantity
  */
  @ApiModelProperty(value = "")


  public Double getSaleQuantity() {
    return saleQuantity;
  }

  public void setSaleQuantity(Double saleQuantity) {
    this.saleQuantity = saleQuantity;
  }

  public SaleDto saleComment(String saleComment) {
    this.saleComment = saleComment;
    return this;
  }

  /**
   * Get saleComment
   * @return saleComment
  */
  @ApiModelProperty(value = "")


  public String getSaleComment() {
    return saleComment;
  }

  public void setSaleComment(String saleComment) {
    this.saleComment = saleComment;
  }

  public SaleDto saleFinalprice(Double saleFinalprice) {
    this.saleFinalprice = saleFinalprice;
    return this;
  }

  /**
   * Get saleFinalprice
   * @return saleFinalprice
  */
  @ApiModelProperty(value = "")


  public Double getSaleFinalprice() {
    return saleFinalprice;
  }

  public void setSaleFinalprice(Double saleFinalprice) {
    this.saleFinalprice = saleFinalprice;
  }

  public SaleDto saleType(SaleTypeEnum saleType) {
    this.saleType = saleType;
    return this;
  }

  /**
   * Get saleType
   * @return saleType
  */
  @ApiModelProperty(example = "Whole", value = "")


  public SaleTypeEnum getSaleType() {
    return saleType;
  }

  public void setSaleType(SaleTypeEnum saleType) {
    this.saleType = saleType;
  }

  public SaleDto saleCommandId(Long saleCommandId) {
    this.saleCommandId = saleCommandId;
    return this;
  }

  /**
   * Get saleCommandId
   * @return saleCommandId
  */
  @ApiModelProperty(value = "")


  public Long getSaleCommandId() {
    return saleCommandId;
  }

  public void setSaleCommandId(Long saleCommandId) {
    this.saleCommandId = saleCommandId;
  }

  public SaleDto saleArticleId(Long saleArticleId) {
    this.saleArticleId = saleArticleId;
    return this;
  }

  /**
   * Get saleArticleId
   * @return saleArticleId
  */
  @ApiModelProperty(value = "")


  public Long getSaleArticleId() {
    return saleArticleId;
  }

  public void setSaleArticleId(Long saleArticleId) {
    this.saleArticleId = saleArticleId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SaleDto saleDto = (SaleDto) o;
    return Objects.equals(this.id, saleDto.id) &&
        Objects.equals(this.saleQuantity, saleDto.saleQuantity) &&
        Objects.equals(this.saleComment, saleDto.saleComment) &&
        Objects.equals(this.saleFinalprice, saleDto.saleFinalprice) &&
        Objects.equals(this.saleType, saleDto.saleType) &&
        Objects.equals(this.saleCommandId, saleDto.saleCommandId) &&
        Objects.equals(this.saleArticleId, saleDto.saleArticleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, saleQuantity, saleComment, saleFinalprice, saleType, saleCommandId, saleArticleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SaleDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    saleQuantity: ").append(toIndentedString(saleQuantity)).append("\n");
    sb.append("    saleComment: ").append(toIndentedString(saleComment)).append("\n");
    sb.append("    saleFinalprice: ").append(toIndentedString(saleFinalprice)).append("\n");
    sb.append("    saleType: ").append(toIndentedString(saleType)).append("\n");
    sb.append("    saleCommandId: ").append(toIndentedString(saleCommandId)).append("\n");
    sb.append("    saleArticleId: ").append(toIndentedString(saleArticleId)).append("\n");
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

