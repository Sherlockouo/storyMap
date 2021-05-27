package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("管理员")
@TableName("admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = 6202489132802261863L;

    @ApiModelProperty("头像")
    String avatar;

    @ApiModelProperty("昵称")
    String nickname;

    @ApiModelProperty("用户名")
    String username;

    @ApiModelProperty("密码")
    @JsonIgnore
    String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date modifyTime;
}
