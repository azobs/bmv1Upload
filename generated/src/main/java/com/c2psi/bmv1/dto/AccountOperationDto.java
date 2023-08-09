package com.c2psi.bmv1.dto;

import java.util.Objects;
import com.c2psi.bmv1.dto.AccountDto;
import com.c2psi.bmv1.dto.OperationDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An account operation in the system
 */
@ApiModel(description = "An account operation in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-08T23:11:35.582723400+01:00[Africa/Douala]")
public class AccountOperationDto   {
  @JsonProperty("quantityinMvt")
  private Double quantityinMvt;

  @JsonProperty("operation")
  private OperationDto operation;

  @JsonProperty("account")
  private AccountDto account;

  public AccountOperationDto quantityinMvt(Double quantityinMvt) {
    this.quantityinMvt = quantityinMvt;
    return this;
  }

  /**
   * Get quantityinMvt
   * @return quantityinMvt
  */
  @ApiModelProperty(value = "")


  public Double getQuantityinMvt() {
    return quantityinMvt;
  }

  public void setQuantityinMvt(Double quantityinMvt) {
    this.quantityinMvt = quantityinMvt;
  }

  public AccountOperationDto operation(OperationDto operation) {
    this.operation = operation;
    return this;
  }

  /**
   * Get operation
   * @return operation
  */
  @ApiModelProperty(value = "")

  @Valid

  public OperationDto getOperation() {
    return operation;
  }

  public void setOperation(OperationDto operation) {
    this.operation = operation;
  }

  public AccountOperationDto account(AccountDto account) {
    this.account = account;
    return this;
  }

  /**
   * Get account
   * @return account
  */
  @ApiModelProperty(value = "")

  @Valid

  public AccountDto getAccount() {
    return account;
  }

  public void setAccount(AccountDto account) {
    this.account = account;
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
    return Objects.equals(this.quantityinMvt, accountOperationDto.quantityinMvt) &&
        Objects.equals(this.operation, accountOperationDto.operation) &&
        Objects.equals(this.account, accountOperationDto.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quantityinMvt, operation, account);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountOperationDto {\n");
    
    sb.append("    quantityinMvt: ").append(toIndentedString(quantityinMvt)).append("\n");
    sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
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

