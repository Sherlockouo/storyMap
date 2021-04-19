package com.storymap.controller.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.FileEntity;
import com.storymap.entity.UserEntity;
import com.storymap.service.FileService;
import com.storymap.service.UserService;
import com.storymap.util.common.R;
import com.storymap.util.common.UploadUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: desc
 * @author: wdf
 * @email: wdf.coder@gmail.com
 * @date: 2021/1/11 14:59
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class FileController {

    @Value("${files.path.winpicupload}")
    private String winPicPath;

    @Value("${files.path.winviupload}")
    private String winViPath;

    @Value("${files.path.winotherupload}")
    private String winOtherPath;

    @Autowired
    private FileService fileService;

    @Autowired
    UserService userService;

    @Autowired
    UploadUtil uploadUtil;

    @PostMapping("/files")
    @ApiOperation(value = "上传文件 返回文件连接")
    @Transactional(propagation = Propagation.REQUIRED)
    public R UploadImageFile(HttpServletRequest request, @RequestParam("files") MultipartFile files[]) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        QueryWrapper<UserEntity> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username",name);
        UserEntity one = userService.getOne(objectQueryWrapper);
        Map<String, Object> res = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileUrl = uploadUtil.uploadfiles(file,one,request);
            if(fileUrl.equals("")){
                return R.error(502,"文件上传出错");
            }
            list.add(fileUrl);
        }
        res.put("code", 200);
        res.put("msg", "上传成功");
        res.put("files", list);
        return R.success(res);

    }
//    改为直接通过访问 静态文件夹下载
//
//    @RequestMapping("/download")
//    public R fileDownLoad(HttpServletResponse response, @RequestParam("fileName") String fileName) {
//        File file = new File(winPicPath + '/' + fileName);
//        if (!file.exists()) {
//            return R.error(404, "文件不存在");
//        }
//        response.reset();
//        response.setContentType("application/octet-stream");
//        response.setCharacterEncoding("utf-8");
//        response.setContentLength((int) file.length());
//        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//
//        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
//            byte[] buff = new byte[1024];
//            OutputStream os = response.getOutputStream();
//            int i = 0;
//            while ((i = bis.read(buff)) != -1) {
//                os.write(buff, 0, i);
//                os.flush();
//            }
//        } catch (IOException e) {
//            log.error("{}", e);
//            return R.error(400, "下载失败");
//        }
//        return R.success("下载成功");
//    }
}
