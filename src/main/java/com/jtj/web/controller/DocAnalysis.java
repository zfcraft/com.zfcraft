package com.jtj.web.controller;

import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 解析文档读取文档内容
 *
 * @author YUFEI
 *
 */
@Component(value="docAnalysis")
public class DocAnalysis {
  //  @Autowired
   // private LuceneConfig luceneConfig;
    public  StringBuilder AnalysisPPT(String path) {
        // 文本内容
        StringBuilder content = new StringBuilder();
        File file = new File(path);
        InputStream is = null;

        SlideShow slideShow = null;
        try {
            is = new FileInputStream(file);
            if (path.endsWith(".ppt")) {
                slideShow = new HSLFSlideShow(is);
            } else if (path.endsWith(".pptx")) {
                slideShow = new XMLSlideShow(is);
            }
            if (slideShow != null) {

                // 一页一页读取
                for (Slide slide : (List<Slide>) slideShow.getSlides()) {
                    List shapes = slide.getShapes();
                    if (shapes != null) {
                        for (int i = 0; i < shapes.size(); i++) {
                            Shape shape = (Shape) shapes.get(i);
                            if (shape instanceof HSLFTextShape) {// 文本框
                                String text = ((HSLFTextShape) shape).getText();
                                content.append(text);
                            }
                            if (shape instanceof XSLFTextShape) {// 文本框
                                String text = ((XSLFTextShape) shape).getText();
                                content.append(text);
                            }
//                            if (shape instanceof HSLFTable) {// 表格
//                                int rowSize = ((HSLFTable) shape).getNumberOfRows();
//                                int columnSize = ((HSLFTable) shape).getNumberOfColumns();
//                                for (int rowNum = 0; rowNum < rowSize; rowNum++) {
//                                    for (int columnNum = 0; columnNum < columnSize; columnNum++) {
//                                        HSLFTableCell cell = ((HSLFTable) shape).getCell(rowNum, columnNum);
//                                        if (cell != null) {
//                                            String text = cell.getText();
//                                            content.append(text);
//                                        }
//                                    }
//                                }
//                            }
                            if (shape instanceof XSLFTable) {// 表格
                                int rowSize = ((XSLFTable) shape).getNumberOfRows();
                                int columnSize = ((XSLFTable) shape).getNumberOfColumns();
                                for (int rowNum = 0; rowNum < rowSize; rowNum++) {
                                    for (int columnNum = 0; columnNum < columnSize; columnNum++) {
                                        XSLFTableCell cell = ((XSLFTable) shape).getCell(rowNum, columnNum);
                                        if (cell != null) {
                                            String text = cell.getText();
                                            content.append(text);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (content.length() >1000) {
                        System.out.println(content);
                        //content.delete(0, content.length());
                        break;
                    }
                }

                // 图片内容
                List pictures = slideShow.getPictureData();
                for (int i = 0; i < pictures.size(); i++) {
                    PictureData picture = (PictureData) pictures.get(i);
                    byte[] data = picture.getData();
                  //  FileOutputStream out = new FileOutputStream(luceneConfig.getPictures_path() + UUID.randomUUID() + ".jpg");
//                    out.write(data);
//                    out.close();
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (slideShow != null) {
                    slideShow.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
        }
        return content;
    }

    public  static  StringBuilder AnalysisWord(String path) {
        StringBuilder wordContent=new StringBuilder();
        String content = null;
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            InputStream is = null;
            HWPFDocument doc = null;
            XWPFDocument docx = null;
            POIXMLTextExtractor extractor = null;
            try {
                is = new FileInputStream(file);
                if (path.endsWith(".doc")) {
                    doc = new HWPFDocument(is);

                    // 文档文本内容
                    content = doc.getDocumentText();
                    // 文档图片内容
                    PicturesTable picturesTable = doc.getPicturesTable();
//                    List<Picture> pictures = picturesTable.getAllPictures();
//                    for (Picture picture : pictures) {
//                        // 输出图片到磁盘
//                        OutputStream out = new FileOutputStream(
//                                new File("D:/" + UUID.randomUUID() + "." + picture.suggestFileExtension()));
//                        picture.writeImageContent(out);
//                        out.close();
//                    }

                } else if (path.endsWith("docx")) {
                    docx = new XWPFDocument(is);
                    extractor = new XWPFWordExtractor(docx);

                    // 文档文本内容
                    content = extractor.getText();

                    // 文档图片内容
                    List<XWPFPictureData> pictures = docx.getAllPictures();
                    for (XWPFPictureData picture : pictures) {
                        byte[] bytev = picture.getData();
                        // 输出图片到磁盘
                        FileOutputStream out = new FileOutputStream(
                                "D:/" + UUID.randomUUID() + picture.getFileName());
                        out.write(bytev);
                        out.close();
                    }
                } else {
                    System.out.println("此文件不是word文件！");
                }
                wordContent.append(content);
                System.out.println(content);
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } finally {
                try {
                    if (doc != null) {
                        doc.close();
                    }
                    if (extractor != null) {
                        extractor.close();
                    }
                    if (docx != null) {
                        docx.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                }
            }
        }
        return wordContent;
    }

    public StringBuilder AnalysisExcel(String path) {
        StringBuilder excelContent=new StringBuilder();
        File file = new File(path);
        InputStream is = null;
        Workbook workbook = null;
        try {
            is = new FileInputStream(file);
            if (path.endsWith(".xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (path.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(is);
            }
            if (workbook != null) {
                int sheetCount = workbook.getNumberOfSheets();
                if (sheetCount > 0) {
                    // 文本内容
                    StringBuilder content = new StringBuilder();
                    for (int i = 0; i < sheetCount; i++) {
                        Sheet sheet = workbook.getSheetAt(i);
                        content.append(sheet.getSheetName());
                        for (int rownum = sheet.getFirstRowNum(); rownum <= sheet.getLastRowNum(); rownum++) {
                            Row row = sheet.getRow(rownum);
                            if (row == null || row.getFirstCellNum() < 0) {
                                break;
                            }
                            for (int columnnum = row.getFirstCellNum(); columnnum <= row
                                    .getLastCellNum(); columnnum++) {
                                String cellValue = getCellValue(row.getCell(columnnum));
                                content.append(cellValue);
                                if (content.length() > 500) {// 没500字输出一次
                                    System.out.println(content.toString());
                                    excelContent.append(content);
                                    content.delete(0, content.length());
                                }
                            }
                        }
                    }
                    if (content.length() > 0) {
                        System.out.println(content.toString());
                    }

                    // 图片内容
                    List<?> pictures = workbook.getAllPictures();
//                    if (pictures != null && !pictures.isEmpty()) {
//                        for (int i = 0; i < pictures.size(); i++) {
//                            PictureData picture = (PictureData) pictures.get(i);
//                            byte[] data = picture.getData();
//                            FileOutputStream out = new FileOutputStream(
//                                    luceneConfig.getPictures_path()+ UUID.randomUUID() + ".jpg");
//                            out.write(data);
//                            out.close();
//                        }
//                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
        }
        return excelContent;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        // 都按文本格式读取
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    public static  StringBuilder AnalysisTxt(String path) {
        StringBuilder txtContent=new StringBuilder();
        File file = new File(path);


        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));// 构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                System.out.println(s);
                //    result = result + "\n" + s;
                //txtContent.append(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return txtContent;
    }
    public static void ReadSql() throws IOException {
        InputStream is = new FileInputStream("F:\\Humanandsocialbureau\\bd_dbaudit_log_20180815_allnew.sql");
        //2、开始读取信息
        //先定义一个字节数组存放数据
        byte[] b = new byte[2048];//把所有的数据读取到这个字节当中
        //完整的读取一个文件
        int sql;
        while ((sql=is.read(b))!=-1) {

            //read:返回的是读取的文件大小
            //最大不超过b.length，返回实际读取的字节个数
            //System.out.println(Arrays.toString(b));//读取的是字节数组
            //把字节数组转成字符串
            System.out.println(new String(b,"utf-8"));
        }

    }
    public static void set_fileInfo(String filename) {
        Path path = Paths.get(filename);

        BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class,
                LinkOption.NOFOLLOW_LINKS);
        BasicFileAttributes attr;
        try {
            attr = basicview.readAttributes();
            // attr.lastModifiedTime();
            Date lastmodfiyTimeDate = new Date(attr.lastModifiedTime().toMillis());//修改时间
            Date CreateTimeDate = new Date(attr.creationTime().toMillis()); //创建时间
            Date accessTime= new Date(attr.lastAccessTime().toMillis());//访问时间
            FileOwnerAttributeView ownerView = Files.getFileAttributeView(path,
                    FileOwnerAttributeView.class);
            System.out.println("文件所有者：" + ownerView.getOwner());
            System.out.println("文件大小：" + attr.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        AnalysisWord("D:/mmm.docx");
    }

}
