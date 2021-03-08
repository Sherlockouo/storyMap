package com.storymap.util.common;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExtraUtil {

    public Map<String,String> getInfo(File file) throws JpegProcessingException, IOException {
        Metadata metadata = JpegMetadataReader.readMetadata(file);
        Map<String,String> mp=new HashMap<>();
        for(Directory directory : metadata.getDirectories()){
            for(Tag tag : directory.getTags()){
                mp.put(tag.getTagName(),tag.getDescription());
            }
        }
        String GPSLongitudeRef = "GPS Longitude Ref";
        String GPSLongitude = "GPS Longitude";
        String GPSLatitudeRef = "GPS Latitude Ref";
        String GPSLatitude = "GPS Latitude";
        String AltitudeRef = "Altitude Ref";
        String Altitude = "Altitude";
        String DateStamp = "Date Stamp";
        String Make = "Make";
        String Model = "Model";

        Map<String,String> res=new HashMap<>();
        res.put(GPSLongitudeRef,mp.get(GPSLongitudeRef));
        res.put(GPSLongitude,mp.get(GPSLongitude));
        res.put(GPSLatitudeRef,mp.get(GPSLatitudeRef));
        res.put(GPSLatitude,mp.get(GPSLatitude));
        res.put(AltitudeRef,mp.get(AltitudeRef));
        res.put(Altitude,mp.get(Altitude));
        res.put(DateStamp,mp.get(DateStamp));
        res.put(Make,mp.get(Make));
        res.put(Model,mp.get(Model));
        return res;
    }
}
