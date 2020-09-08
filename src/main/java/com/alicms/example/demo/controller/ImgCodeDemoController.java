package com.alicms.example.demo.controller;

import com.alicms.example.demo.utils.CodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 * 验证码生成测试
 * </p>
 *
 * @author zhenghao
 * @date 2020/8/18 15:20
 */
@RestController
@Slf4j
public class ImgCodeDemoController {

    @GetMapping(value = "/img-code2")
    public void getImg(HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        Map<String, Object> map = CodeUtils.generateCodeAndPic();
        Object code = map.get("code");
        RenderedImage codePic = (RenderedImage) map.get("codePic");
        ImageIO.write(codePic, "JPEG", response.getOutputStream());
        log.info("验证码：{}", code);
    }


    @GetMapping(value = "/img-code")
    public void getYzm(HttpServletResponse response) throws Exception {

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 定义图片长度和宽度
        int width = 65, height = 30;
        // 创建内存图像
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new SecureRandom();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.setColor(new Color(255, 255, 255));
        g.drawRect(0, 0, width - 1, height - 1);
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width), y = random.nextInt(height), xl = random.nextInt(12), yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 随机获取四位字母或数字型字符
        String str = "ABCDEFGHJKMNPQRSTUVWXYZ123456789";
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            char ch = str.charAt(new SecureRandom().nextInt(str.length()));
            sb.append(ch);
        }
        String code = sb.toString().toLowerCase();
        log.info("验证码：" + code);
        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
        g.drawString(code, 5, 20);

        //验证码id储存在cookie中

        String codeId = UUID.randomUUID().toString().replaceAll("-", "");
        response.setHeader("x-img-code", codeId);
        // 验证码储存在redis
//        redisUtil.set("imgCode:"+codeId, code, 60);

        //HttpSession session = request.getSession();
        //session.setAttribute("code", code);
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    private Color getRandColor(int fc, int bc) {
        SecureRandom random = new SecureRandom();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private Integer getRandomCode() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(900000) + 100000;
    }

}
