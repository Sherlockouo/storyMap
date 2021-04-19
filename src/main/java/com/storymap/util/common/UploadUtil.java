package com.storymap.util.common;

import com.storymap.entity.FileEntity;
import com.storymap.entity.UserEntity;
import com.storymap.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class UploadUtil {

    @Autowired
    FileService fileService;

    public String uploadfiles(MultipartFile file, UserEntity one, HttpServletRequest request) throws FileNotFoundException {
        String randomName = UUID.randomUUID().toString().replace("-", "");

        String originName = file.getOriginalFilename();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(originName);
        fileEntity.setUserid(one.getId());
        String fname = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        //fiel-url
        String filePath="";

        randomName+=fname;
        fname=randomName;
        String pic = ".+(.png|.jpeg|.jpg|.gif|.bmp|.psd|.tiff|.tga|.eps)$";

        String video = ".+(.swf|.mkv|.flv|.mp4|.rmvb|.avi|.mpeg|.ra|.ram|.mov|.wmv)$";

        // match images
        Pattern compile = Pattern.compile(pic);
        Matcher matcher = compile.matcher(fname);

        // match videos
        Pattern vp = Pattern.compile(video);
        Matcher mv = compile.matcher(fname);

        File path = new File(ResourceUtils.getURL( "classpath:" ).getPath());
        if (!path.exists()) {
            path = new File( "" );
        }

        if (matcher.matches()) {
//                File dest = new File(winPicPath + fname).getCanonicalFile();


            File dest = new File(path.getAbsolutePath(),"files/images/");


            log.info("dest {}", dest);
            File target = new File(dest,fname);
            Path path1 = target.toPath();
            try {
                Files.write(path1,file.getBytes());

            } catch (Exception e) {
                log.error("{}", e);
                return "";
            }

            filePath = request.getHeader("X-Forwarded-Scheme") + "://" + request.getServerName()+ "/files/images/" + fname;
//            list.add(filePath);
            fileEntity.setType("image");
            fileEntity.setFileurl(filePath);
            fileService.save(fileEntity);
        }else if(mv.matches()){

            File dest = new File(path.getAbsolutePath(),"files/video/");


            log.info("dest {}", dest);
            File target = new File(dest,fname);
            Path path1 = target.toPath();
            try {
                Files.write(path1,file.getBytes());


            } catch (Exception e) {
                log.error("{}", e);
                return "";
            }

            filePath = request.getHeader("X-Forwarded-Scheme") + "://" + request.getServerName()   + "/files/videos/" + fname;

            fileEntity.setType("video");
            fileEntity.setFileurl(filePath);
            fileService.save(fileEntity);
        }else{



            File dest = new File(path.getAbsolutePath(),"files/others/");


            log.info("dest {}", dest);
            File target = new File(dest,fname);
            Path path1 = target.toPath();
            try {
                Files.write(path1,file.getBytes());

            } catch (Exception e) {
                log.error("{}", e);
                return "";
            }

            filePath = request.getHeader("X-Forwarded-Scheme") + "://" + request.getServerName() + "/files/others/" + fname;
            fileEntity.setType("others");
            fileEntity.setFileurl(filePath);
            fileService.save(fileEntity);
        }
        return filePath;
    }
}
