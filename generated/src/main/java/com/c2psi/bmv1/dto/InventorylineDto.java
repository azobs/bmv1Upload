package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.ArticleDto;
import com.c2psi.bmv1.dto.InventoryDto;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class InventorylineDto   {
  @JsonProperty("invlineComment")
  private String invlineComment;

  @JsonProperty("realqteinStock")
  private Double realqteinStock;

  @JsonProperty("logicqteinStock")
  private Double logicqteinStock;

  @JsonProperty("inventory")
  private InventoryDto inventory;

  @JsonProperty("invlineArticle")
  private ArticleDto invlineArticle;

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
   * @return realqteinStock
  */
  @ApiModelProperty(value = "")


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
   * @return logicqteinStock
  */
  @ApiModelProperty(value = "")


  public Double getLogicqteinStock() {
    return logicqteinStock;
  }

  public void setLogicqteinStock(Double logicqteinStock) {
    this.logicqteinStock = logicqteinStock;
  }

  public InventorylineDto inventory(InventoryDto inventory) {
    this.inventory = inventory;
    return this;
  }

  /**
   * Get inventory
   * @return inventory
  */
  @ApiModelProperty(value = "")

  @Valid

  public InventoryDto getInventory() {
    return inventory;
  }

  public void setInventory(InventoryDto inventory) {
    this.inventory = inventory;
  }

  public InventorylineDto invlineArticle(ArticleDto invlineArticle) {
    this.invlineArticle = invlineArticle;
    return this;
  }

  /**
   * Get invlineArticle
   * @return invlineArticle
  */
  @ApiModelProperty(value = "")

  @Valid

  public ArticleDto getInvlineArticle() {
    return invlineArticle;
  }

  public void setInvlineArticle(ArticleDto invlineArticle) {
    this.invlineArticle = invlineArticle;
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
    return Objects.equals(this.invlineComment, inventorylineDto.invlineComment) &&
        Objects.equals(this.realqteinStock, inventorylineDto.realqteinStock) &&
        Objects.equals(this.logicqteinStock, inventorylineDto.logicqteinStock) &&
        Objects.equals(this.inventory, inventorylineDto.inventory) &&
        Objects.equals(this.invlineArticle, inventorylineDto.invlineArticle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invlineComment, realqteinStock, logicqteinStock, inventory, invlineArticle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InventorylineDto {\n");
    
    sb.append("    invlineComment: ").append(toIndentedString(invlineComment)).append("\n");
    sb.append("    realqteinStock: ").append(toIndentedString(realqteinStock)).append("\n");
    sb.append("    logicqteinStock: ").append(toIndentedString(logicqteinStock)).append("\n");
    sb.append("    inventory: ").append(toIndentedString(inventory)).append("\n");
    sb.append("    invlineArticle: ").append(toIndentedString(invlineArticle)).append("\n");
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

