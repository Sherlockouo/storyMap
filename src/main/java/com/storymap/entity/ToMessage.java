package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
public class ToMessage implements Serializable {

    private static final long serialVersionUID = -1138562656190091989L;

    @ApiModelProperty("发送者id")
    private Long senduserid;

    @ApiModelProperty("接收者id")
    private Long reciveuserid;


    @ApiModelProperty("发送时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendtime;

    private String msgtype;

    private String sendtext;
}
