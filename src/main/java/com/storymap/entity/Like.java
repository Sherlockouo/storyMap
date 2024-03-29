package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("likes")
@ApiModel("like")
public class Like implements Serializable {
    private static final long serialVersionUID = -3251952488868627851L;
    @TableId
    Long id;

    @ApiModelProperty("被喜欢用户id")
    Long userid;

    @TableField(exist = false)
    UserEntity userEntity;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createTime;

    @ApiModelProperty("follower")
    Long likeuserid;

    @ApiModelProperty("Posterid")
    Long Posterid;

    @TableField(exist = false)
    Poster poster;

    @ApiModelProperty("status")
    Boolean status;
}
