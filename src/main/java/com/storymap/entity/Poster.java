package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("poster")
@ApiModel("poster")
public class Poster implements Serializable {
    private static final long serialVersionUID = -3251952488868627851L;
    @TableId
    Long id;

    @ApiModelProperty("用户id")
    Long userid;

    @ApiModelProperty("地址")
    String address;

    @ApiModelProperty("经度")
    double longitude;

    @ApiModelProperty("纬度")
    double latitude;

    @ApiModelProperty("描述")
    String message;

    @ApiModelProperty("文件urls")
    String files;
}
