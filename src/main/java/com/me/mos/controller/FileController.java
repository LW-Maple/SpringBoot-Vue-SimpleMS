package com.me.mos.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.me.mos.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author Maple
 * @Date 2022/8/15 17:25
 * @Version 1.0
 */
@RestController
@RequestMapping("/files")
public class FileController {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        //1.获取文件名
        String originalFilename=file.getOriginalFilename();
        //2.定义文件唯一标识
        String flag= IdUtil.fastSimpleUUID();
        //3.设置文件保存路径
        String filePath=System.getProperty("user.dir")+"/src/main/resources/files/"+flag+originalFilename;
        FileUtil.writeBytes(file.getBytes(), filePath);
        return Result.success();
    }

    /**
     * 下载文件
     * @param flag 文件标识
     * @param response 响应流
     * @return
     */
    @GetMapping("/{flag}")
    public void getFile(@PathVariable String flag, HttpServletResponse response) throws IOException {
        //1.创建相关变量
        OutputStream os;
        //2.遍历存储文件的文件名找到需要下载的文件
        String basePath=System.getProperty("user.dir")+"/src/main/resources/files/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String fileName=fileNames.stream().filter(name->name.contains(flag)).findAny().orElse("");
        //3.设置相关参数进行上传
        if (StrUtil.isNotEmpty(fileName)){
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/octet-stream");
            byte[] bytes=FileUtil.readBytes(basePath+fileName);
            os=response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        }
    }

    @GetMapping("/allFlags")
    public Result<?> getAllFileFlags(){
        String basePath=System.getProperty("user.dir")+"/src/main/resources/files/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        return Result.success(fileNames);
    }

}
