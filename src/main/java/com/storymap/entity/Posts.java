package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("poster")
public class Posts implements Serializable {
    private static final long serialVersionUID = -3251952488868627851L;
    @TableId
    Long id;

    Long userid;

    String address;

    String message;

    Integer likes;

    @TableField(exist = false)
    List<String> files;

}

