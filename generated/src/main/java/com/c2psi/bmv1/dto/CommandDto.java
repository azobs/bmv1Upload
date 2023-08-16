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
 * A Command in the system
 */
@ApiModel(description = "A Command in the system")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-16T08:50:41.193143300+01:00[Africa/Douala]")
public class CommandDto   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("cmdCode")
  private String cmdCode;

  @JsonProperty("cmdDate")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime cmdDate;

  @JsonProperty("cmdComment")
  private String cmdComment;

  /**
   * Gets or Sets cmdState
   */
  public enum CmdStateEnum {
    INEDITING("InEditing"),
    
    EDITED("Edited"),
    
    INDELIVERY("InDelivery"),
    
    DELIVERY("Delivery");

    private String value;

    CmdStateEnum(String value) {
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
    public static CmdStateEnum fromValue(String value) {
      for (CmdStateEnum b : CmdStateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("cmdState")
  private CmdStateEnum cmdState;

  /**
   * Gets or Sets cmdNature
   */
  public enum CmdNatureEnum {
    CASH("Cash"),
    
    COVER("Cover"),
    
    DAMAGE("Damage");

    private String value;

    CmdNatureEnum(String value) {
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
    public static CmdNatureEnum fromValue(String value) {
      for (CmdNatureEnum b : CmdNatureEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("cmdNature")
  private CmdNatureEnum cmdNature;

  @JsonProperty("cmdDeliveryId")
  private Long cmdDeliveryId;

  @JsonProperty("cmdLoadingId")
  private Long cmdLoadingId;

  @JsonProperty("cmdClientId")
  private Long cmdClientId;

  @JsonProperty("cmdSalerId")
  private Long cmdSalerId;

  @JsonProperty("cmdInvoiceId")
  private Long cmdInvoiceId;

  @JsonProperty("cmdPosId")
  private Long cmdPosId;

  public CommandDto id(Long id) {
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

  public CommandDto cmdCode(String cmdCode) {
    this.cmdCode = cmdCode;
    return this;
  }

  /**
   * Get cmdCode
   * @return cmdCode
  */
  @ApiModelProperty(example = "C0000", value = "")


  public String getCmdCode() {
    return cmdCode;
  }

  public void setCmdCode(String cmdCode) {
    this.cmdCode = cmdCode;
  }

  public CommandDto cmdDate(OffsetDateTime cmdDate) {
    this.cmdDate = cmdDate;
    return this;
  }

  /**
   * Get cmdDate
   * @return cmdDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCmdDate() {
    return cmdDate;
  }

  public void setCmdDate(OffsetDateTime cmdDate) {
    this.cmdDate = cmdDate;
  }

  public CommandDto cmdComment(String cmdComment) {
    this.cmdComment = cmdComment;
    return this;
  }

  /**
   * Get cmdComment
   * @return cmdComment
  */
  @ApiModelProperty(value = "")


  public String getCmdComment() {
    return cmdComment;
  }

  public void setCmdComment(String cmdComment) {
    this.cmdComment = cmdComment;
  }

  public CommandDto cmdState(CmdStateEnum cmdState) {
    this.cmdState = cmdState;
    return this;
  }

  /**
   * Get cmdState
   * @return cmdState
  */
  @ApiModelProperty(example = "InEditing", value = "")


  public CmdStateEnum getCmdState() {
    return cmdState;
  }

  public void setCmdState(CmdStateEnum cmdState) {
    this.cmdState = cmdState;
  }

  public CommandDto cmdNature(CmdNatureEnum cmdNature) {
    this.cmdNature = cmdNature;
    return this;
  }

  /**
   * Get cmdNature
   * @return cmdNature
  */
  @ApiModelProperty(example = "Cash", value = "")


  public CmdNatureEnum getCmdNature() {
    return cmdNature;
  }

  public void setCmdNature(CmdNatureEnum cmdNature) {
    this.cmdNature = cmdNature;
  }

  public CommandDto cmdDeliveryId(Long cmdDeliveryId) {
    this.cmdDeliveryId = cmdDeliveryId;
    return this;
  }

  /**
   * Get cmdDeliveryId
   * @return cmdDeliveryId
  */
  @ApiModelProperty(value = "")


  public Long getCmdDeliveryId() {
    return cmdDeliveryId;
  }

  public void setCmdDeliveryId(Long cmdDeliveryId) {
    this.cmdDeliveryId = cmdDeliveryId;
  }

  public CommandDto cmdLoadingId(Long cmdLoadingId) {
    this.cmdLoadingId = cmdLoadingId;
    return this;
  }

  /**
   * Get cmdLoadingId
   * @return cmdLoadingId
  */
  @ApiModelProperty(value = "")


  public Long getCmdLoadingId() {
    return cmdLoadingId;
  }

  public void setCmdLoadingId(Long cmdLoadingId) {
    this.cmdLoadingId = cmdLoadingId;
  }

  public CommandDto cmdClientId(Long cmdClientId) {
    this.cmdClientId = cmdClientId;
    return this;
  }

  /**
   * Get cmdClientId
   * @return cmdClientId
  */
  @ApiModelProperty(value = "")


  public Long getCmdClientId() {
    return cmdClientId;
  }

  public void setCmdClientId(Long cmdClientId) {
    this.cmdClientId = cmdClientId;
  }

  public CommandDto cmdSalerId(Long cmdSalerId) {
    this.cmdSalerId = cmdSalerId;
    return this;
  }

  /**
   * Get cmdSalerId
   * @return cmdSalerId
  */
  @ApiModelProperty(value = "")


  public Long getCmdSalerId() {
    return cmdSalerId;
  }

  public void setCmdSalerId(Long cmdSalerId) {
    this.cmdSalerId = cmdSalerId;
  }

  public CommandDto cmdInvoiceId(Long cmdInvoiceId) {
    this.cmdInvoiceId = cmdInvoiceId;
    return this;
  }

  /**
   * Get cmdInvoiceId
   * @return cmdInvoiceId
  */
  @ApiModelProperty(value = "")


  public Long getCmdInvoiceId() {
    return cmdInvoiceId;
  }

  public void setCmdInvoiceId(Long cmdInvoiceId) {
    this.cmdInvoiceId = cmdInvoiceId;
  }

  public CommandDto cmdPosId(Long cmdPosId) {
    this.cmdPosId = cmdPosId;
    return this;
  }

  /**
   * Get cmdPosId
   * @return cmdPosId
  */
  @ApiModelProperty(value = "")


  public Long getCmdPosId() {
    return cmdPosId;
  }

  public void setCmdPosId(Long cmdPosId) {
    this.cmdPosId = cmdPosId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommandDto commandDto = (CommandDto) o;
    return Objects.equals(this.id, commandDto.id) &&
        Objects.equals(this.cmdCode, commandDto.cmdCode) &&
        Objects.equals(this.cmdDate, commandDto.cmdDate) &&
        Objects.equals(this.cmdComment, commandDto.cmdComment) &&
        Objects.equals(this.cmdState, commandDto.cmdState) &&
        Objects.equals(this.cmdNature, commandDto.cmdNature) &&
        Objects.equals(this.cmdDeliveryId, commandDto.cmdDeliveryId) &&
        Objects.equals(this.cmdLoadingId, commandDto.cmdLoadingId) &&
        Objects.equals(this.cmdClientId, commandDto.cmdClientId) &&
        Objects.equals(this.cmdSalerId, commandDto.cmdSalerId) &&
        Objects.equals(this.cmdInvoiceId, commandDto.cmdInvoiceId) &&
        Objects.equals(this.cmdPosId, commandDto.cmdPosId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cmdCode, cmdDate, cmdComment, cmdState, cmdNature, cmdDeliveryId, cmdLoadingId, cmdClientId, cmdSalerId, cmdInvoiceId, cmdPosId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommandDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cmdCode: ").append(toIndentedString(cmdCode)).append("\n");
    sb.append("    cmdDate: ").append(toIndentedString(cmdDate)).append("\n");
    sb.append("    cmdComment: ").append(toIndentedString(cmdComment)).append("\n");
    sb.append("    cmdState: ").append(toIndentedString(cmdState)).append("\n");
    sb.append("    cmdNature: ").append(toIndentedString(cmdNature)).append("\n");
    sb.append("    cmdDeliveryId: ").append(toIndentedString(cmdDeliveryId)).append("\n");
    sb.append("    cmdLoadingId: ").append(toIndentedString(cmdLoadingId)).append("\n");
    sb.append("    cmdClientId: ").append(toIndentedString(cmdClientId)).append("\n");
    sb.append("    cmdSalerId: ").append(toIndentedString(cmdSalerId)).append("\n");
    sb.append("    cmdInvoiceId: ").append(toIndentedString(cmdInvoiceId)).append("\n");
    sb.append("    cmdPosId: ").append(toIndentedString(cmdPosId)).append("\n");
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

