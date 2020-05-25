package com.example.myutil.beanValidator.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 品名资料明细表
 * </p>
 *
 * @author xianfuhao
 * @since 2020-03-27
 */
@ApiModel(description = "库存导入" ,value = "库存导入")
@Getter
@Setter
public class StockImportVo {

    private static final long serialVersionUID=1L;


    private String sort;



    @NotBlank(message = "品名不能为空")
    @ApiModelProperty(value = "品名")
    @TableField("TYPE_NAME")
    private String typeName;

    @NotBlank(message = "规格不能为空")
    @ApiModelProperty(value = "规格")
    @TableField("SPEC_NAME")
    private String specName;

    @NotBlank(message = "材质不能为空")
    @ApiModelProperty(value = "材质")
    @TableField("GRADE")
    private String grade;

    @ApiModelProperty(value = "钢厂id")
    @TableField("PAREA_ID")
    private String pareaId;

    //@NotBlank(message = "钢厂不能为空")
    @ApiModelProperty(value = "钢厂")
    @TableField("PAREA")
    private String parea;

    @NotBlank(message = "库存数量不能为空")
    @Pattern(regexp = "^[1-9]\\d*$",message = "库存数量必须是的正整数")
    @ApiModelProperty(value = "库存数量(件)")
    @TableField("COUNT")
    private String count;

    @NotBlank(message = "库存重量不能为空")
    @DecimalMin(value = "0",message = "库存重量必须是个大于0的数字,精度为3位")
    @Digits(integer=16, fraction=3,message = "库存重量必须是个大于0的数字,精度为3位")
    @ApiModelProperty(value = "库存重量(吨)")
    @TableField("WEIGHT")
    private String weight;

    @Pattern(regexp = "^[1-9]\\d*$",message = "可用数量必须是的正整数")
    @ApiModelProperty(value = "可用数量(件)")
    @TableField("VALID_NUM")
    private String validNum;

    @DecimalMin(value = "0",message = "可用重量必须是个大于0的数字,精度为3位")
    @Digits(integer=16, fraction=3,message = "可用重量必须是个大于0的数字,精度为3位")
    @ApiModelProperty(value = "可用重量(吨)")
    @TableField("VALID_WEIGHT")
    private String validWeight;

    @ApiModelProperty(value = "锁定数量(件)")
    @TableField("LOCKING_NUM")
    private String lockingNum;

    @ApiModelProperty(value = "锁定重量(吨)")
    @TableField("LOCKING_WEIGHT")
    private String lockingWeight;

        @ApiModelProperty(value = "仓位")
    @TableField("POSITION")
    private String position;

    //@NotBlank(message = "入库时间不能为空")
    @Pattern(regexp = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$"
            ,message = "请输入正确的日期格式（yyyy-MM-dd）"
    )
    @ApiModelProperty(value = "入库时间")
    @TableField("ENTRY_STORE_TIME")
    private String entryStoreTime;

    //@NotBlank(message = "采购价不能为空")
    @DecimalMin(value = "0",message = "采购价必须是个大于0的数字,精度为3位")
    @Digits(integer=16, fraction=3,message = "采购价必须是个大于0的数字,精度为3位")
    @ApiModelProperty(value = "采购价（元/吨）")
    @TableField("PURCHASE_PRICE")
    private String purchasePrice;

    @ApiModelProperty(value = "供应商ID")
    @TableField("SUPPLIER_ID")
    private String supplierId;

    @ApiModelProperty(value = "供应商")
    @TableField("SUPPLIER")
    private String supplier;

    @ApiModelProperty(value = "仓库库存切换")
    @TableField("STOCK_CHANGE")
    private Integer stockChange;

    @ApiModelProperty(value = "会员仓")
    @TableField("MEMBER_ID")
    private String memberId;

    @ApiModelProperty(value = "仓库包装号（不锈钢独有）")
    @TableField("STORE_PACK_NO")
    private String storePackNo;

    @ApiModelProperty(value = "仓库包装号（不锈钢独有）")
    @TableField("HAND_CODE")
    private String handCode;

    @ApiModelProperty(value = "备注")
    @TableField("REMARAK")
    private String remark;


}
