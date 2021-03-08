package com.storymap.util.common;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;

/**
 * @description: 二维码生成/解析工具
 * @author: wdf
 * @email: wdf.coder@gmail.com
 * @date: 2021/1/18 11:52
 */
@Component
@Slf4j
public class QRUtils {

    final int defWidth =360;

    final int defHeight = 360;

    final String defFormat = "png";

    public String generateQRCode(String txt){
        if(txt.isEmpty())
            return null;

        String res="";

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        Path file;
        /** 生成二维码 */
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(txt, BarcodeFormat.QR_CODE, defWidth, defHeight, hints);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ImageIO.write(bufferedImage,"png",os);
            res+="data:image/png;base64,"+ Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 生成二维码
     * @param txt
     * @param width
     * @param height
     * @return
     */
    public String generateQRCode(String txt,int width,int height,String format){
        if(txt.isEmpty())
            return null;

        String res="";


        if(width==0)
            width=defWidth;
        if(height==0)
            height=defHeight;
        if(format.equals(""))
            format=defFormat;

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        Path file;
        /** 生成二维码 */
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(txt, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ImageIO.write(bufferedImage,"png",os);
            res+="data:image/png;base64,"+ Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }


    public String decodeQRCode(File file){
        MultiFormatReader formatReader = new MultiFormatReader();
        Result result=null;
        try {
            BufferedImage image = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            HashMap hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            result = formatReader.decode(binaryBitmap, hints);

            System.out.println("解析结果： " + result.toString());
            System.out.println("二维码的格式类型：" + result.getBarcodeFormat());
            System.out.println("二维码文本内容： " + result.getText() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getText();
    }

}
