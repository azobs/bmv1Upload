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
 * A cash account operation in the system it concerns only operation on cash, momo or OM
 */
@ApiModel(description = "A cash account operation in the system it concerns only operation on cash, momo or OM")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-26T01:24:15.865861+01:00[Africa/Casablanca]")
public class CashOperationDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("amountinMvt")
  private Double amountinMvt;

  /**
   * Gets or Sets amountSource
   */
  public enum AmountSourceEnum {
    CASH_DESK("CASH_DESK"),
    
    OM_ACCOUNT("OM_ACCOUNT"),
    
    MOMO_ACCOUNT("MOMO_ACCOUNT");

    private String value;

    AmountSourceEnum(String value) {
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
    public static AmountSourceEnum fromValue(String value) {
      for (AmountSourceEnum b : AmountSourceEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("amountSource")
  private AmountSourceEnum amountSource;

  /**
   * Gets or Sets amountDestination
   */
  public enum AmountDestinationEnum {
    CASH_DESK("CASH_DESK"),
    
    OM_ACCOUNT("OM_ACCOUNT"),
    
    MOMO_ACCOUNT("MOMO_ACCOUNT");

    private String value;

    AmountDestinationEnum(String value) {
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
    public static AmountDestinationEnum fromValue(String value) {
      for (AmountDestinationEnum b : AmountDestinationEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("amountDestination")
  private AmountDestinationEnum amountDestination;

  @JsonProperty("operationId")
  private Long operationId;

  @JsonProperty("posConcernedId")
  private Long posConcernedId;

  @JsonProperty("clientConcernedId")
  private Long clientConcernedId;

  @JsonProperty("providerConcernedId")
  private Long providerConcernedId;

  public CashOperationDto id(Long id) {
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

  public CashOperationDto amountinMvt(Double amountinMvt) {
    this.amountinMvt = amountinMvt;
    return this;
  }

  /**
   * Get amountinMvt
   * minimum: 0
   * @return amountinMvt
  */
  @ApiModelProperty(example = "1", value = "")

@DecimalMin(value = "0", inclusive = false) 
  public Double getAmountinMvt() {
    return amountinMvt;
  }

  public void setAmountinMvt(Double amountinMvt) {
    this.amountinMvt = amountinMvt;
  }

  public CashOperationDto amountSource(AmountSourceEnum amountSource) {
    this.amountSource = amountSource;
    return this;
  }

  /**
   * Get amountSource
   * @return amountSource
  */
  @ApiModelProperty(example = "CASH_DESK", value = "")


  public AmountSourceEnum getAmountSource() {
    return amountSource;
  }

  public void setAmountSource(AmountSourceEnum amountSource) {
    this.amountSource = amountSource;
  }

  public CashOperationDto amountDestination(AmountDestinationEnum amountDestination) {
    this.amountDestination = amountDestination;
    return this;
  }

  /**
   * Get amountDestination
   * @return amountDestination
  */
  @ApiModelProperty(example = "MOMO_ACCOUNT", value = "")


  public AmountDestinationEnum getAmountDestination() {
    return amountDestination;
  }

  public void setAmountDestination(AmountDestinationEnum amountDestination) {
    this.amountDestination = amountDestination;
  }

  public CashOperationDto operationId(Long operationId) {
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

  public CashOperationDto posConcernedId(Long posConcernedId) {
    this.posConcernedId = posConcernedId;
    return this;
  }

  /**
   * Get posConcernedId
   * @return posConcernedId
  */
  @ApiModelProperty(value = "")


  public Long getPosConcernedId() {
    return posConcernedId;
  }

  public void setPosConcernedId(Long posConcernedId) {
    this.posConcernedId = posConcernedId;
  }

  public CashOperationDto clientConcernedId(Long clientConcernedId) {
    this.clientConcernedId = clientConcernedId;
    return this;
  }

  /**
   * Get clientConcernedId
   * @return clientConcernedId
  */
  @ApiModelProperty(value = "")


  public Long getClientConcernedId() {
    return clientConcernedId;
  }

  public void setClientConcernedId(Long clientConcernedId) {
    this.clientConcernedId = clientConcernedId;
  }

  public CashOperationDto providerConcernedId(Long providerConcernedId) {
    this.providerConcernedId = providerConcernedId;
    return this;
  }

  /**
   * Get providerConcernedId
   * @return providerConcernedId
  */
  @ApiModelProperty(value = "")


  public Long getProviderConcernedId() {
    return providerConcernedId;
  }

  public void setProviderConcernedId(Long providerConcernedId) {
    this.providerConcernedId = providerConcernedId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CashOperationDto cashOperationDto = (CashOperationDto) o;
    return Objects.equals(this.id, cashOperationDto.id) &&
        Objects.equals(this.amountinMvt, cashOperationDto.amountinMvt) &&
        Objects.equals(this.amountSource, cashOperationDto.amountSource) &&
        Objects.equals(this.amountDestination, cashOperationDto.amountDestination) &&
        Objects.equals(this.operationId, cashOperationDto.operationId) &&
        Objects.equals(this.posConcernedId, cashOperationDto.posConcernedId) &&
        Objects.equals(this.clientConcernedId, cashOperationDto.clientConcernedId) &&
        Objects.equals(this.providerConcernedId, cashOperationDto.providerConcernedId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, amountinMvt, amountSource, amountDestination, operationId, posConcernedId, clientConcernedId, providerConcernedId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CashOperationDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    amountinMvt: ").append(toIndentedString(amountinMvt)).append("\n");
    sb.append("    amountSource: ").append(toIndentedString(amountSource)).append("\n");
    sb.append("    amountDestination: ").append(toIndentedString(amountDestination)).append("\n");
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    posConcernedId: ").append(toIndentedString(posConcernedId)).append("\n");
    sb.append("    clientConcernedId: ").append(toIndentedString(clientConcernedId)).append("\n");
    sb.append("    providerConcernedId: ").append(toIndentedString(providerConcernedId)).append("\n");
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

