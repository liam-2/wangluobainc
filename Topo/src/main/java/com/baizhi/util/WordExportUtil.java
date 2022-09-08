package com.baizhi.util;

import cn.afterturn.easypoi.word.entity.MyXWPFDocument;
import cn.afterturn.easypoi.word.parse.ParseWord07;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.springframework.util.Assert;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordExportUtil
{
  public static XWPFDocument exportWord07(String url, Map<String, Object> map)
    throws Exception
  {
    return new ParseWord07().parseWord(url, map);
  }

  public static void exportWord07(XWPFDocument document, Map<String, Object> map)
    throws Exception
  {
    new ParseWord07().parseWord(document, map);
  }
  
  /**
   * 导出word
   * <p>第一步生成替换后的word文件，只支持docx</p>
   * <p>第二步下载生成的文件</p>
   * <p>第三步删除生成的临时文件</p>
   * 模版变量中变量格式：{{foo}}
   *
   * @param is       word模板文件流
   * @param temDir   生成临时文件存放地址
   * @param fileName 文件名
   * @param params   替换的参数
 * @throws IOException 
   */
public static void exportWord(InputStream is, String temDir, String fileName, Map<String, Object> params) throws IOException {
      Assert.notNull(is, "模板不能为空");
      Assert.notNull(temDir, "临时文件路径不能为空 ");
      Assert.notNull(fileName, "导出文件名不能为空 ");
      Assert.isTrue(fileName.endsWith(".docx"), "word导出请使用docx格式");
     
	  OutputStream out = null;
	  
      if (!temDir.endsWith("/")) {
          temDir = temDir + File.separator;
      }
      File dir = new File(temDir);
      if (!dir.exists()) {
          dir.mkdirs();
      }
      try {

          MyXWPFDocument doc = new MyXWPFDocument(is);

          WordExportUtil.exportWord07(doc, params);

          String tmpPath = temDir + fileName;
          System.out.println("文件地址"+tmpPath);
          FileOutputStream fos = new FileOutputStream(tmpPath);
          doc.write(fos);

          //poi读取word
          FileInputStream fin = new FileInputStream(new File(tmpPath));
          XWPFDocument hdt=new XWPFDocument(fin);
          for (XWPFParagraph paragraph : hdt.getParagraphs()){
                if(paragraph.getText().contains("&章")){
                    XWPFRun run = paragraph.getRuns().get(0);
                    run.setText("", 0);
                    String imgFile = "D:\\data\\test\\test2.PNG";

                    System.out.println("插入盖章图片:" + imgFile);
                    FileInputStream imgis = new FileInputStream(imgFile);

                    run.addPicture(imgis, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(100), Units.toEMU(100));
                    System.out.println("插入成功");
                    // 100x100 pixels
                    //将图片变成浮动
                    // 2. 获取到图片数据
                    CTDrawing drawing = run.getCTR().getDrawingArray(0);
                    CTGraphicalObject graphicalobject = drawing.getInlineArray(0).getGraphic();

                    //拿到新插入的图片替换添加CTAnchor 设置浮动属性 删除inline属性
                    CTAnchor anchor = getAnchorWithGraphic(graphicalobject, "test2",
                            Units.toEMU(120), Units.toEMU(120),//图片大小
                            Units.toEMU(320), Units.toEMU(-50), false);//相对当前段落位置 需要计算段落已有内容的左偏移
                    drawing.setAnchorArray(new CTAnchor[]{anchor});//添加浮动属性
                    drawing.removeInline(0);//删除行内属性
                    System.out.println("设置浮动成功");

                    is.close();
                    break;
                }
          }
//          for(XWPFTable table : hdt.getTables()) {
//              for(XWPFTableRow row : table.getRows()) {
//                  for(XWPFTableCell cell : row.getTableCells()) {//遍历每一个单元格
//                      if(cell.getText().contains("&章")) {//如果遇到"&章"则进行替换
//                          try {
//                              insertCellStamp(cell);//给带有要盖章字样的单元格 加上章的图片
//                              System.out.println("插入成功");
//                          } catch (Exception e) {
//                              e.printStackTrace();
//                          }
//                      }
//                  }
//              }
//          }
          FileOutputStream fos2 = new FileOutputStream(tmpPath);
          hdt.write(fos2);

      } catch (Exception e) {
          e.printStackTrace();
      } finally {
         // delFileWord(temDir, fileName);//这一步看具体需求，要不要删
     	  //out.close();
      }
  }
  
  /**
   * 删除零时生成的文件
   */
  public static void delFileWord(String filePath, String fileName) {
      File file = new File(filePath + fileName);
      File file1 = new File(filePath);
      file.delete();
      file1.delete();
  }




    private static void insertCellStamp(XWPFTableCell cell) throws InvalidFormatException, IOException {//给带有要盖章字样的单元格 加上章的图片
       // List<String> stamps = new ArrayList<>();//存放要加入的图片
        int stampOrder = 0;//图片的序号，从0开始

        //获取需要的图片，
//        for(XWPFParagraph para :cell.getParagraphs()) {
//            String paraText = para.getText();//从段落中获取要盖的章的名称
////			System.out.println("para.getText():" + paraText);
//            if(paraText != null) {
//                String[] split = para.getText().split(" ");
//                for(String s : split) {
//                    s = s.trim();
//                    if(!s.isEmpty() ) {
//                        stamps.add(s.replace("&章", ""));//如：&章公章01.png，去掉标识符&章，只留下章的名字
//                    }
//                }
//            }
//        }

        //String basedir = "E:";
        for(XWPFParagraph para :cell.getParagraphs()) {
            for (XWPFRun run : para.getRuns()) {
                run.setText("", 0);//清空所有文字
            }
//			for (int i =para.getRuns().size()-1 ; i>=0; i--) {
//				XWPFRun run = para.getRuns().get(i);
//				System.out.println("清空所有文字后：run.getText(0): " + run.getText(0));
//			}

            //插入图片
          //  for(int i = 0; i<stamps.size() && i<para.getRuns().size(); i++) {
                try {
                    XWPFRun run = para.getRuns().get(0);
                   // String imgFile = basedir + "/公章管理/" + stamps.get(stampOrder++);
                    String imgFile = "D:/data/test/test2.PNG";
                    System.out.println("插入盖章图片:" + imgFile);
                    FileInputStream is = new FileInputStream(imgFile);
                    run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(100), Units.toEMU(100)); // 100x100 pixels
                    is.close();
                    run.setText("  ",0);

                } catch (Exception e) {
                    System.out.println("Error: ========  插入单个公章图片时出错了:可能是图片路径不存在。不影响主流程");
                    e.printStackTrace();
                }
            //}
        }
    }


    /**
     * @param ctGraphicalObject 图片数据
     * @param deskFileName      图片描述
     * @param width             宽
     * @param height            高
     * @param leftOffset        水平偏移 left
     * @param topOffset         垂直偏移 top
     * @param behind            文字上方，文字下方
     * @return
     * @throws Exception
     */
    public static CTAnchor getAnchorWithGraphic(CTGraphicalObject ctGraphicalObject,
                                                String deskFileName, int width, int height,
                                                int leftOffset, int topOffset, boolean behind) {
        String anchorXML =
                "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
                        + "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0) + "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">"
                        + "<wp:simplePos x=\"0\" y=\"0\"/>"
                        + "<wp:positionH relativeFrom=\"column\">"
                        + "<wp:posOffset>" + leftOffset + "</wp:posOffset>"
                        + "</wp:positionH>"
                        + "<wp:positionV relativeFrom=\"paragraph\">"
                        + "<wp:posOffset>" + topOffset + "</wp:posOffset>" +
                        "</wp:positionV>"
                        + "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
                        + "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>"
                        + "<wp:wrapNone/>"
                        + "<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\"" + deskFileName + "\"/><wp:cNvGraphicFramePr/>"
                        + "</wp:anchor>";

        CTDrawing drawing = null;
        try {
            drawing = CTDrawing.Factory.parse(anchorXML);
        } catch (XmlException e) {
            e.printStackTrace();
        }
        CTAnchor anchor = drawing.getAnchorArray(0);
        anchor.setGraphic(ctGraphicalObject);
        return anchor;
    }
}





