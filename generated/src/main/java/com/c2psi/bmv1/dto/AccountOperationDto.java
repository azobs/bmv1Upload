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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-11T17:01:56.543198200+01:00[Africa/Douala]")
public class AccountOperationDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("quantityinMvt")
  private Double quantityinMvt;

  @JsonProperty("operation")
  private OperationDto operation;

  @JsonProperty("account")
  private AccountDto account;

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
    return Objects.equals(this.id, accountOperationDto.id) &&
        Objects.equals(this.quantityinMvt, accountOperationDto.quantityinMvt) &&
        Objects.equals(this.operation, accountOperationDto.operation) &&
        Objects.equals(this.account, accountOperationDto.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantityinMvt, operation, account);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountOperationDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

