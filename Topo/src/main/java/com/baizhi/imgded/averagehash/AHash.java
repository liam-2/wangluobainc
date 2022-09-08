package com.baizhi.imgded.averagehash;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 均值哈希算法/Average hash algorithm/AHA
 * <p>
 * 最适用于缩略图，放大图搜索
 * <p>
 * 虽然均值哈希更简单且更快速，但是在比较上更死板、僵硬。<br>
 * 它可能产生错误的漏洞，如有一个伽马校正或颜色直方图被用于到图像。<br>
 * 这是因为颜色沿着一个非线性标尺 - 改变其中“平均值”的位置，并因此改变哪些高于/低于平均值的比特数
 * <p>
 *
 * @author xuyanhua
 * @data Jan 10, 2017 1:09:46 AM
 */
public class AHash {

    /**
     * 图片指纹
     *
     * @param imagePath
     * @return
     * @throws IOException
     */
    public static long fingerprint(String imagePath) throws IOException {
        BufferedImage srcImage = ImageIO.read(new File(imagePath));
        /*
         * 1.缩小尺寸. 为了保留结构去掉细节，去除大小、横纵比的差异，把图片统一缩放到8*8，共64个像素的图片
         */
//        BufferedImage image8x8 = ImageUtil.resize(srcImage, 8, 8);
        BufferedImage image8x8=new BufferedImage(8,8,BufferedImage.TYPE_INT_RGB);
        image8x8.getGraphics().drawImage(srcImage,0,0,8,8,null);

        /*
         * 2.简化色彩,转化为灰度图. 把缩放后的图片转化为256阶的灰度图
         */
        int width = image8x8.getWidth();
        int height = image8x8.getHeight();
        int[] grayPix = new int[64];
        int i = 0;
        int sum = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image8x8.getRGB(x, y);
                int r = rgb >> 16 & 0xff;
                int g = rgb >> 8 & 0xff;
                int b = rgb >> 0 & 0xff;
                int gray = (r * 30 + g * 59 + b * 11) / 100;
                grayPix[i++] = gray;
                sum += gray;
            }
        }
        /* 3.计算平均值, 计算进行灰度处理后图片的所有像素点的平均值 */
        int avg = sum / 64;
        /*
         * 4.比较像素灰度值,遍历灰度图片每一个像素，如果大于平均值记录为1，否则为0. 5.获取指纹
         */
        long figure = 0;
        for (i = 63; i >= 0; i--) {
            long b = (long) (grayPix[i] > avg ? 1 : 0);
            figure |= b << i;
        }
        return figure;
    }

}
