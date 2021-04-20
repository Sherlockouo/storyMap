package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
@ApiModel
@TableName("msg")
public class Message implements Serializable {
    private static final long serialVersionUID = -5277178172258304238L;

    private Long id;

    @ApiModelProperty("发送者id")
    private Long senduserid;

    @TableField(exist = false)
    private UserEntity senduser;

    @ApiModelProperty("接收者id")
    private Long reciveuserid;

    @TableField(exist = false)
    private UserEntity reciveuser;

    @ApiModelProperty("发送时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendtime;

    private String msgtype;

    private String sendtext;

    private Boolean deleted;
}
