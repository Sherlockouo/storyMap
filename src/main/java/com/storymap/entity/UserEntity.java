package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("用户")
@TableName("user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -5541133799771246443L;

    @TableId
    Long id;

    @ApiModelProperty("头像")
    String avatar;

    @ApiModelProperty("昵称")
    String nickname;

    @ApiModelProperty("用户名")
    String username;

    @ApiModelProperty("密码")
    @JsonIgnore
    String password;

    @JsonIgnore
    String openid;

    @ApiModelProperty("电话号码")
    String phonenumber;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date modifyTime;

}
