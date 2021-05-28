package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
@TableName("notify")
public class Notify implements Serializable {
    private static final long serialVersionUID = 1960185129625756735L;

    Long id;

    String title;

    String message;

    Date createDate;

    Date modifyDate;

    Integer status;
}
