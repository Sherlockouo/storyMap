package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("collect")
@ApiModel("collect")
public class Collect implements Serializable {
    private static final long serialVersionUID = -3251952488868627851L;
    @TableId
    Long id;

    @ApiModelProperty("用户id")
    Long userid;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createTime;

    @ApiModelProperty("posterid")
    Long posterid;

    @ApiModelProperty("被收藏用户id")
    Long collectuserid;

    @ApiModelProperty("是否喜欢")
    Boolean status;
}
