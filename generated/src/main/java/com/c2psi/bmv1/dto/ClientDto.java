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
 * A client in the system
 */
@ApiModel(description = "A client in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-16T08:50:41.193143300+01:00[Africa/Douala]")
public class ClientDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("clientName")
  private String clientName;

  @JsonProperty("clientOthername")
  private String clientOthername;

  @JsonProperty("clientCni")
  private String clientCni;

  @JsonProperty("clientBalance")
  private Double clientBalance;

  @JsonProperty("clientAddressId")
  private Long clientAddressId;

  @JsonProperty("clientPosId")
  private Long clientPosId;

  public ClientDto id(Long id) {
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

  public ClientDto clientName(String clientName) {
    this.clientName = clientName;
    return this;
  }

  /**
   * Get clientName
   * @return clientName
  */
  @ApiModelProperty(value = "")


  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public ClientDto clientOthername(String clientOthername) {
    this.clientOthername = clientOthername;
    return this;
  }

  /**
   * Get clientOthername
   * @return clientOthername
  */
  @ApiModelProperty(value = "")


  public String getClientOthername() {
    return clientOthername;
  }

  public void setClientOthername(String clientOthername) {
    this.clientOthername = clientOthername;
  }

  public ClientDto clientCni(String clientCni) {
    this.clientCni = clientCni;
    return this;
  }

  /**
   * Get clientCni
   * @return clientCni
  */
  @ApiModelProperty(value = "")


  public String getClientCni() {
    return clientCni;
  }

  public void setClientCni(String clientCni) {
    this.clientCni = clientCni;
  }

  public ClientDto clientBalance(Double clientBalance) {
    this.clientBalance = clientBalance;
    return this;
  }

  /**
   * Get clientBalance
   * minimum: 0
   * @return clientBalance
  */
  @ApiModelProperty(value = "")

@DecimalMin("0") 
  public Double getClientBalance() {
    return clientBalance;
  }

  public void setClientBalance(Double clientBalance) {
    this.clientBalance = clientBalance;
  }

  public ClientDto clientAddressId(Long clientAddressId) {
    this.clientAddressId = clientAddressId;
    return this;
  }

  /**
   * Get clientAddressId
   * @return clientAddressId
  */
  @ApiModelProperty(value = "")


  public Long getClientAddressId() {
    return clientAddressId;
  }

  public void setClientAddressId(Long clientAddressId) {
    this.clientAddressId = clientAddressId;
  }

  public ClientDto clientPosId(Long clientPosId) {
    this.clientPosId = clientPosId;
    return this;
  }

  /**
   * Get clientPosId
   * @return clientPosId
  */
  @ApiModelProperty(value = "")


  public Long getClientPosId() {
    return clientPosId;
  }

  public void setClientPosId(Long clientPosId) {
    this.clientPosId = clientPosId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientDto clientDto = (ClientDto) o;
    return Objects.equals(this.id, clientDto.id) &&
        Objects.equals(this.clientName, clientDto.clientName) &&
        Objects.equals(this.clientOthername, clientDto.clientOthername) &&
        Objects.equals(this.clientCni, clientDto.clientCni) &&
        Objects.equals(this.clientBalance, clientDto.clientBalance) &&
        Objects.equals(this.clientAddressId, clientDto.clientAddressId) &&
        Objects.equals(this.clientPosId, clientDto.clientPosId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, clientName, clientOthername, clientCni, clientBalance, clientAddressId, clientPosId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClientDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    clientName: ").append(toIndentedString(clientName)).append("\n");
    sb.append("    clientOthername: ").append(toIndentedString(clientOthername)).append("\n");
    sb.append("    clientCni: ").append(toIndentedString(clientCni)).append("\n");
    sb.append("    clientBalance: ").append(toIndentedString(clientBalance)).append("\n");
    sb.append("    clientAddressId: ").append(toIndentedString(clientAddressId)).append("\n");
    sb.append("    clientPosId: ").append(toIndentedString(clientPosId)).append("\n");
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

