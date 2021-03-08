package com.storymap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@ApiModel("地图信息类")
@TableName("fileInfo")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = -4944056418562659953L;

    @TableId
    Long id;

    @ApiModelProperty("用户id")
    Long userid;

    @ApiModelProperty("标语")
    String slogan;

    @ApiModelProperty("文件名")
    String filename;

    @ApiModelProperty("文件类型")
    String filetype;

    @ApiModelProperty("文件标签")
    String filetags;

    @ApiModelProperty("文件描述")
    String filedesc;

    @ApiModelProperty("行政地区位置")
    String geoLocation;

    @ApiModelProperty("经度方向")
    String GPSLongitudeRef;
    @ApiModelProperty("经度")
    String GPSLongitude ;

    @ApiModelProperty("纬度方向")
    String GPSLatitudeRef;
    @ApiModelProperty("经度")
    String GPSLatitude;

    @ApiModelProperty("海拔")
    String AltitudeRef;
    @ApiModelProperty("海拔")
    String Altitude;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("拍摄时间")
    String DateStamp;

    @JsonIgnore
    String Make;

    @JsonIgnore
    String Model;
}
