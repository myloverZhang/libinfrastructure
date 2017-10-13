package ct.dc.libinfrastructure;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CTWLPC on 2017/9/30.
 */
public class FileUtils implements Closeable{

    private String filePath;
    private RandomAccessFile file;
    private Long lineSeek;


    public FileUtils(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        file = new RandomAccessFile(this.filePath, "rw");
        lineSeek = 0L;
    }

    /**
     * 读取文件内容
     * @return
     */
    public String readFile() throws IOException {
        file.seek(0L);
        StringBuffer stringBuffer = new StringBuffer();
        String line = file.readLine();
        while (line!=null){
            stringBuffer.append(new String(line.getBytes("ISO-8859-1"),"utf-8"));
            line = file.readLine();
        }
        return stringBuffer.toString();
    }

    /**
     *  按行读取所有文件内容
     * @return
     */
    public List<String> readLines() throws IOException {
        file.seek(0L);
        ArrayList<String> contents = new ArrayList<>();
        String line = file.readLine();
        while (line != null){
            contents.add(new String(line.getBytes("ISO-8859-1"),"UTF-8"));
            line = file.readLine();
        }
        return contents;
    }

    /**
     * 按行的数据
     * @return
     */
    public String readLine() throws IOException {
        file.seek(lineSeek);
        String line = file.readLine();
        lineSeek = file.getFilePointer();
        if (line == null)
            return null;
        return new String(line.getBytes("ISO-8859-1"), "UTF-8");
    }

    /**
     *
     * @param content
     * @param isAppend
     * @return
     */
    public void writeLine(String content,boolean isAppend) throws IOException {
        write(String.format("%s\n",content), isAppend);
    }

    /**
     * 写文件
     * @param content
     * @param isAppend
     * @throws IOException
     */
    public void write(String content,boolean isAppend) throws IOException {
        byte[] bytes = content.getBytes();
        if (!isAppend){
            file.seek(0L);
            file.write(bytes);
            file.setLength(bytes.length);
        }else {
            file.seek(file.length());
            file.write(bytes);
        }
    }
    /**
     * 判断文件是否存在
     * @param path
     * @return
     */
    public static boolean pathExists(String path){
        File file = new File(path);
        return file.exists();
    }

    /**
     * 获取文件名
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static String getFileName(String path) throws FileNotFoundException {
        File file = new File(path);
        return file.getName();
    }

    /**
     * 获取上级目录
     * @param path
     * @return
     */
    public static String getParentPath(String path){
        File file = new File(path);
        return file.getParent();
    }


    /**
     * 创建文件夹
     * @param dirPath
     * @param isCreateParentDir
     * @return
     */
    public static boolean createDirectory(String dirPath, boolean isCreateParentDir) {
        File dir = new File(dirPath);
        if (!dir.exists())
            if (!isCreateParentDir) {
                return dir.mkdir();
            } else {
                if (createDirectory(getParentPath(dirPath), true)) {
                    dir.mkdir();
                }
            }
        return true;
    }

    /**
     * 创建文件
     * @param filePath
     * @param isCreateParentDir
     * @return
     * @throws IOException
     */
    public static boolean createFile(String filePath,boolean isCreateParentDir) throws IOException {
        File file = new File(filePath);
        if (!file.exists())
            if (!isCreateParentDir){
                return file.createNewFile();
            }else {
                if (createDirectory(getParentPath(filePath), true))
                    file.createNewFile();
            }
        return true;

    }
    /**
     * 删除文件
     * @param filePath
     * @return
     */
    public static void deleteFile(String filePath){
        File file = new File(filePath);
        if (file.exists())
            file.delete();
    }

    /**
     * 删除文件夹
     * @param dirPath
     */
    public static void deleteDirectory(String dirPath){
        File file = new File(dirPath);
        File[] childFiles = file.listFiles();
        if (childFiles!= null && childFiles.length > 0) {
            for (File f : childFiles) {
                System.out.println(f.getPath());
                deleteDirectory(f.getPath());
            }
        }
        file.delete();
    }

    /**
     * 获取文件的大小
     * @param filePath
     * @return
     */
    public static long getFileSize(String filePath){
        File file = new File(filePath);
        return file.length();
    }
    /**
     * 读取全部文件全部类型
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        try(RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            String line =  file.readLine();
            while (line!=null){
                String tmp = new String(line.getBytes("iso8859-1"),"UTF-8");
                System.out.println(tmp);
                stringBuffer.append(tmp);
                line = file.readLine();
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 按行读取内容到行中
     * @param filePath
     * @return
     */
    public static List<String> readFileToList(String filePath) throws IOException {
        ArrayList<String> contents = new ArrayList<>();
        try(RandomAccessFile file = new RandomAccessFile(filePath,"r")) {
            String line = file.readLine();
            while (line != null){
                contents.add(new String(line.getBytes("iso8859-1"),"UTF-8"));
                line = file.readLine();
            }
        }
        return contents;
    }

    /**
     * 文件写入内容 父目录不存在 自动添加
     * @param filePath
     * @param content
     * @param isAppend 是否文件尾部追加
     */
    public static void writeFile(String filePath,String content,boolean isAppend) throws IOException {
        byte[] bytes = content.getBytes();
        try(RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            if (isAppend){
                file.write(bytes);
                file.setLength(bytes.length);
            }else {
                long seek = file.length();
                file.seek(seek);
                file.write(bytes);
            }
        }
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     * <p>
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        if (file!=null){
            file.close();
        }
    }
}
