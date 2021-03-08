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
@TableName("file")
@ApiModel("文件")
public class FileEntity implements Serializable {
    private static final long serialVersionUID = -3192030837415289833L;

    @TableId
    private Long id;

    @ApiModelProperty("用户")
    private Long userid;

    @ApiModelProperty("类型")
    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("上传时间")
    private Date uploadTime;

    @ApiModelProperty("文件名")
    private String filename;

    @ApiModelProperty("文件url")
    private String fileurl;
}
