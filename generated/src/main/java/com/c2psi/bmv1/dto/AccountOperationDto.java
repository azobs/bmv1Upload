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
 * An account operation in the system. It concerns only Cover, Damage and Packaging
 */
@ApiModel(description = "An account operation in the system. It concerns only Cover, Damage and Packaging")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-18T07:37:22.558276100+01:00[Africa/Casablanca]")
public class AccountOperationDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("quantityinMvt")
  private Double quantityinMvt;

  @JsonProperty("operationId")
  private Long operationId;

  @JsonProperty("accountId")
  private Long accountId;

  public AccountOperationDto id(Long id) {
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

  public AccountOperationDto quantityinMvt(Double quantityinMvt) {
    this.quantityinMvt = quantityinMvt;
    return this;
  }

  /**
   * Get quantityinMvt
   * minimum: 0
   * @return quantityinMvt
  */
  @ApiModelProperty(example = "1", value = "")

@DecimalMin(value = "0", inclusive = false) 
  public Double getQuantityinMvt() {
    return quantityinMvt;
  }

  public void setQuantityinMvt(Double quantityinMvt) {
    this.quantityinMvt = quantityinMvt;
  }

  public AccountOperationDto operationId(Long operationId) {
    this.operationId = operationId;
    return this;
  }

  /**
   * Get operationId
   * @return operationId
  */
  @ApiModelProperty(value = "")


  public Long getOperationId() {
    return operationId;
  }

  public void setOperationId(Long operationId) {
    this.operationId = operationId;
  }

  public AccountOperationDto accountId(Long accountId) {
    this.accountId = accountId;
    return this;
  }

  /**
   * Get accountId
   * @return accountId
  */
  @ApiModelProperty(value = "")


  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountOperationDto accountOperationDto = (AccountOperationDto) o;
    return Objects.equals(this.id, accountOperationDto.id) &&
        Objects.equals(this.quantityinMvt, accountOperationDto.quantityinMvt) &&
        Objects.equals(this.operationId, accountOperationDto.operationId) &&
        Objects.equals(this.accountId, accountOperationDto.accountId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantityinMvt, operationId, accountId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountOperationDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    quantityinMvt: ").append(toIndentedString(quantityinMvt)).append("\n");
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
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

