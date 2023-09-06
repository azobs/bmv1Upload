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
 * An Inventory line in the system
 */
@ApiModel(description = "An Inventory line in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-05T20:37:01.434321300+01:00[Africa/Casablanca]")
public class InventorylineDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("invlineComment")
  private String invlineComment;

  @JsonProperty("realqteinStock")
  private Double realqteinStock;

  @JsonProperty("logicqteinStock")
  private Double logicqteinStock;

  @JsonProperty("inventoryId")
  private Long inventoryId;

  @JsonProperty("invlineArticleId")
  private Long invlineArticleId;

  public InventorylineDto id(Long id) {
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

  public InventorylineDto invlineComment(String invlineComment) {
    this.invlineComment = invlineComment;
    return this;
  }

  /**
   * Get invlineComment
   * @return invlineComment
  */
  @ApiModelProperty(value = "")


  public String getInvlineComment() {
    return invlineComment;
  }

  public void setInvlineComment(String invlineComment) {
    this.invlineComment = invlineComment;
  }

  public InventorylineDto realqteinStock(Double realqteinStock) {
    this.realqteinStock = realqteinStock;
    return this;
  }

  /**
   * Get realqteinStock
   * minimum: 0
   * @return realqteinStock
  */
  @ApiModelProperty(example = "0", value = "")

@DecimalMin("0") 
  public Double getRealqteinStock() {
    return realqteinStock;
  }

  public void setRealqteinStock(Double realqteinStock) {
    this.realqteinStock = realqteinStock;
  }

  public InventorylineDto logicqteinStock(Double logicqteinStock) {
    this.logicqteinStock = logicqteinStock;
    return this;
  }

  /**
   * Get logicqteinStock
   * minimum: 0
   * @return logicqteinStock
  */
  @ApiModelProperty(example = "0", value = "")

@DecimalMin("0") 
  public Double getLogicqteinStock() {
    return logicqteinStock;
  }

  public void setLogicqteinStock(Double logicqteinStock) {
    this.logicqteinStock = logicqteinStock;
  }

  public InventorylineDto inventoryId(Long inventoryId) {
    this.inventoryId = inventoryId;
    return this;
  }

  /**
   * Get inventoryId
   * @return inventoryId
  */
  @ApiModelProperty(value = "")


  public Long getInventoryId() {
    return inventoryId;
  }

  public void setInventoryId(Long inventoryId) {
    this.inventoryId = inventoryId;
  }

  public InventorylineDto invlineArticleId(Long invlineArticleId) {
    this.invlineArticleId = invlineArticleId;
    return this;
  }

  /**
   * Get invlineArticleId
   * @return invlineArticleId
  */
  @ApiModelProperty(value = "")


  public Long getInvlineArticleId() {
    return invlineArticleId;
  }

  public void setInvlineArticleId(Long invlineArticleId) {
    this.invlineArticleId = invlineArticleId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InventorylineDto inventorylineDto = (InventorylineDto) o;
    return Objects.equals(this.id, inventorylineDto.id) &&
        Objects.equals(this.invlineComment, inventorylineDto.invlineComment) &&
        Objects.equals(this.realqteinStock, inventorylineDto.realqteinStock) &&
        Objects.equals(this.logicqteinStock, inventorylineDto.logicqteinStock) &&
        Objects.equals(this.inventoryId, inventorylineDto.inventoryId) &&
        Objects.equals(this.invlineArticleId, inventorylineDto.invlineArticleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, invlineComment, realqteinStock, logicqteinStock, inventoryId, invlineArticleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InventorylineDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    invlineComment: ").append(toIndentedString(invlineComment)).append("\n");
    sb.append("    realqteinStock: ").append(toIndentedString(realqteinStock)).append("\n");
    sb.append("    logicqteinStock: ").append(toIndentedString(logicqteinStock)).append("\n");
    sb.append("    inventoryId: ").append(toIndentedString(inventoryId)).append("\n");
    sb.append("    invlineArticleId: ").append(toIndentedString(invlineArticleId)).append("\n");
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

