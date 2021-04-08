package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
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

    @TableField(exist = false)
    @ApiModelProperty("username")
    String username;

    @ApiModelProperty("avarturl")
    @TableField(exist = false)
    String avatar;

    @ApiModelProperty("地址")
    String address;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createTime;

    @ApiModelProperty("经度")
    double longtitude;

    @ApiModelProperty("纬度")
    double latitude;

    @ApiModelProperty("点赞数")
    Integer likes;

    @ApiModelProperty("title")
    String title;

    @ApiModelProperty("描述")
    String message;

    @ApiModelProperty("类型")
    String type;

    @ApiModelProperty("文件urls")
    String files;

    @ApiModelProperty("tags")
    String tags;
}
