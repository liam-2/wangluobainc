package com.baizhi.imgded.sift;


import com.baizhi.imgded.imgeded;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SIFTTest extends imgeded{
    int matchesPointCount;
    float nndrRatio=0.5f;
    public void matchImage(BufferedImage templateImageB, BufferedImage originalImageB) {

        Mat resT = new Mat();
        Mat resO = new Mat();

        //即当detector 又当Detector
        SIFT sift = SIFT.create();

        Mat templateImage = getMatify(templateImageB);
        Mat originalImage = getMatify(originalImageB);

        MatOfKeyPoint templateKeyPoints = new MatOfKeyPoint();
        MatOfKeyPoint originalKeyPoints = new MatOfKeyPoint();

        //获取模板图的特征点
        sift.detect(templateImage, templateKeyPoints);
        sift.detect(originalImage, originalKeyPoints);


        sift.compute(templateImage, templateKeyPoints, resT);
        sift.compute(originalImage, originalKeyPoints, resO);

        List<MatOfDMatch> matches = new LinkedList();
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
        System.out.println("寻找最佳匹配");

        printPic("ptest", templateImage);
        printPic("ptesO", originalImage);

        printPic("test", resT);
        printPic("tesO", resO);

        /**
         * knnMatch方法的作用就是在给定特征描述集合中寻找最佳匹配
         * 使用KNN-matching算法，令K=2，则每个match得到两个最接近的descriptor，然后计算最接近距离和次接近距离之间的比值，当比值大于既定值时，才作为最终match。
         */
        descriptorMatcher.knnMatch(resT, resO, matches, 2);
        System.out.println("计算匹配结果");
        LinkedList<DMatch> goodMatchesList = new LinkedList();
        //对匹配结果进行筛选，依据distance进行筛选
        matches.forEach(match -> {
            DMatch[] dmatcharray = match.toArray();
            DMatch m1 = dmatcharray[0];
            DMatch m2 = dmatcharray[1];

            if (m1.distance <= m2.distance * nndrRatio) {
                goodMatchesList.addLast(m1);
            }
        });

        matchesPointCount = goodMatchesList.size();
        double flag=(double)matchesPointCount/matches.size();
        System.out.println("匹配比率为"+flag/0.1);
        //当匹配后的特征点大于等于 4 个，则认为模板图在原图中，该值可以自行调整
        if (flag >= 0.07) {
            System.out.println("模板图在原图匹配成功！");


//            List<KeyPoint> templateKeyPointList = templateKeyPoints.toList();
//            List<KeyPoint> originalKeyPointList = originalKeyPoints.toList();
//            LinkedList<Point> objectPoints = new LinkedList();
//            LinkedList<Point> scenePoints = new LinkedList();
//            goodMatchesList.forEach(goodMatch -> {
//                objectPoints.addLast(templateKeyPointList.get(goodMatch.queryIdx).pt);
//                scenePoints.addLast(originalKeyPointList.get(goodMatch.trainIdx).pt);
//            });
//            MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f();
//            objMatOfPoint2f.fromList(objectPoints);
//            MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f();
//            scnMatOfPoint2f.fromList(scenePoints);
//            //使用 findHomography 寻找匹配上的关键点的变换
//            Mat homography = Calib3d.findHomography(objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC, 3);
//
//            /**
//             * 透视变换(Perspective Transformation)是将图片投影到一个新的视平面(Viewing Plane)，也称作投影映射(Projective Mapping)。
//             */
//            Mat templateCorners = new Mat(4, 1, CvType.CV_32FC2);
//            Mat templateTransformResult = new Mat(4, 1, CvType.CV_32FC2);
//            templateCorners.put(0, 0, new double[]{0, 0});
//            templateCorners.put(1, 0, new double[]{templateImage.cols(), 0});
//            templateCorners.put(2, 0, new double[]{templateImage.cols(), templateImage.rows()});
//            templateCorners.put(3, 0, new double[]{0, templateImage.rows()});
//            //使用 perspectiveTransform 将模板图进行透视变以矫正图象得到标准图片
//            Core.perspectiveTransform(templateCorners, templateTransformResult, homography);
//
//            //矩形四个顶点  匹配的图片经过旋转之后就这个矩形的四个点的位置就不是正常的abcd了
//            double[] pointA = templateTransformResult.get(0, 0);
//            double[] pointB = templateTransformResult.get(1, 0);
//            double[] pointC = templateTransformResult.get(2, 0);
//            double[] pointD = templateTransformResult.get(3, 0);
//
//            //指定取得数组子集的范围
//
//
//            //将匹配的图像用用四条线框出来
//            Imgproc.rectangle(originalImage, new Point(pointA), new Point(pointC), new Scalar(0, 255, 0));
//
//
//            MatOfDMatch goodMatches = new MatOfDMatch();
//            goodMatches.fromList(goodMatchesList);
//            Mat matchOutput = new Mat(originalImage.rows() * 2, originalImage.cols() * 2, Imgcodecs.IMREAD_COLOR);
//            Features2d.drawMatches(templateImage, templateKeyPoints, originalImage, originalKeyPoints, goodMatches, matchOutput, new Scalar(0, 255, 0), new Scalar(255, 0, 0), new MatOfByte(), 2);
//            Features2d.drawMatches(templateImage, templateKeyPoints, originalImage, originalKeyPoints, goodMatches, matchOutput, new Scalar(0, 255, 0), new Scalar(255, 0, 0), new MatOfByte(), 2);
//
//            printPic("ppgc", matchOutput);
//            printPic("ytwz", originalImage);
        } else {
            System.out.println("模板图不在原图中！");
        }
        printPic("模板特征点", resT);
    }


    public void printPic(String name, Mat pre) {
        Imgcodecs.imwrite(name + ".jpg", pre);
    }

    /**
     * 尝试把BufferedImage转换为Mat
     *
     * @param im
     * @return
     */
    public Mat getMatify(BufferedImage im) {
        BufferedImage bufferedImage = toBufferedImageOfType(im, BufferedImage.TYPE_3BYTE_BGR);
        //将bufferedimage转换为字节数组
        byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        Mat image = new Mat(bufferedImage.getHeight(), bufferedImage.getWidth(), CvType.CV_8UC3);

        image.put(0, 0, pixels);

        return image;
    }

    /**
     * 转换图片类型
     *
     * @param original
     * @param type
     * @return
     */
    public static BufferedImage toBufferedImageOfType(BufferedImage original, int type) {
        if (original == null) {
            throw new IllegalArgumentException("original == null");
        }
        // Don't convert if it already has correct type
        if (original.getType() == type) {
            return original;
        }
        // Create a buffered image
        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), type);

        // Draw the image onto the new buffer
        Graphics2D g = image.createGraphics();
        try {
            g.setComposite(AlphaComposite.Src);
            g.drawImage(original, 0, 0, null);
        } finally {
            g.dispose();
        }

        return image;
    }



    public static void main(String[] args) throws IOException {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

        String img1path="D:\\data\\snap\\"+ imgeded.Str1;
//        String img2path="C:\\TEMP\\wangluobainc\\Topo\\Users\\zhangguilin\\photo\\2022\\03\\30\\32000000000105010103000000195788\\camera\\before\\35\\320000000001050101030000001957880342214161599169010120220329161416713144_000.jpg";
        String img2path="D:\\data\\snap\\"+imgeded.Str2;

        BufferedImage image1 = ImageIO.read(new File(img1path));
        BufferedImage image2 = ImageIO.read(new File(img2path));

        SIFTTest siftTest=new SIFTTest();
        siftTest.matchImage(image1,image2);

    }
}
