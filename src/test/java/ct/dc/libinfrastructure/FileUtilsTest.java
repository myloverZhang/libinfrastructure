package ct.dc.libinfrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by CTWLPC on 2017/9/30.
 */
public class FileUtilsTest {


    @Test
    public void readFile1() throws Exception {
        FileUtils fileUtils = new FileUtils("E:\\test.txt");
        String content = fileUtils.readFile();
        fileUtils.close();
        System.out.println(content);
        assert content.length() > 0;
    }

    @Test
    public void readLines() throws Exception {
        FileUtils fileUtils = new FileUtils("E:\\test.txt");
        List<String> contents = fileUtils.readLines();
        for (String content:contents){
            System.out.println(content);
        }
        fileUtils.close();
        assert contents.size() > 0;
    }

    @Test
    public void readLine() throws Exception {
        FileUtils fileUtils = new FileUtils("E:\\test.txt");
        String line = fileUtils.readLine();
        while (line!=null){
            System.out.println(line);
            line = fileUtils.readLine();
        }
        assert true;
    }

    @Test
    public void writeLine() throws Exception {
        FileUtils fileUtils = new FileUtils("E:\\test.txt");
        fileUtils.writeLine("one发违法发生的发",true);
        fileUtils.writeLine("kaskfoiawejfiojj-平均分IPA违法",false);
        assert true;
    }

    @Test
    public void write() throws Exception {
        FileUtils fileUtils = new FileUtils("E:\\test.txt");
        fileUtils.write("ssssssssssssss",true);
        fileUtils.write("bbbbbbbbbbbbbbb",false);
        assert true;
    }

    @Test
    public void getFileSize() throws Exception {
        long fileSize = FileUtils.getFileSize("E:\\test.txt");
        System.out.println(fileSize);
        assert fileSize > 0;
    }

    @Test
    public void deleteDirectory() throws Exception {
        FileUtils.deleteDirectory("E:\\a\\b\\c");
        assert true;
    }

    @Test
    public void deleteFile() throws Exception {
        FileUtils.createFile("E:\\a\\b\\c\\d.txt",true);
        FileUtils.deleteFile("E:\\a\\b\\c");
        assert true;
    }

    @Test
    public void writeFile() throws Exception {
        FileUtils.writeFile("E:\\tests.txt","\n天下第一武道会",false);
        assert true;
    }

    @Test
    public void readFileToList() throws Exception {
        List<String> contents = FileUtils.readFileToList("E:\\tests.txt");
        for (String content:contents){
            System.out.println(content);
        }
        assert contents.size()>0;
    }

    @Test
    public void readFile() throws Exception {
        String content = FileUtils.readFile("E:\\test.txt");
        System.out.println(content);
        assert content.length() > 0;
    }


    @Test
    public void createDirectory() throws Exception {
        boolean success = FileUtils.createDirectory("E:\\shusf\\ssf\\fasda",true);
        System.out.println(success);
        assert success;
    }

    @Test
    public void getParentPath() throws Exception {
        String parent = FileUtils.getParentPath("E:\\test.txt");
        System.out.println(parent);
        assert parent.equals("E:\\");
    }

    @Test
    public void pathExists() throws Exception {
        boolean exists = FileUtils.pathExists("E:\\test.txt");
        System.out.println(exists);
        assert true;
    }

    @Test
    public void getFileName() throws Exception {
        String fileName = FileUtils.getFileName("E:\\test.txt");
        System.out.println(fileName);
        assert fileName.equals("test.txt");
    }

}