import java.io.*;

public class Main {

    private static File mydestpath;
    private static File sourcefiles;

    public static void main(String[] args) {
        sourcefiles = new File("C:\\Users\\ymh\\Desktop\\filetest\\GISDeve\\GISDeve");
        mydestpath = new File("C:\\Users\\ymh\\Desktop\\filetest\\ymhhwx");
//        listFiles(sourcefiles, mydestpath);
//        showFiles(new File("C:\\Users\\ymh\\Desktop\\ws\\wshop"), 0);
        printFileDetails(new File("C:\\Users\\ymh\\Desktop\\GISDeve\\GISDeve"), new File("C:\\Users\\ymh\\Desktop\\filetest\\ymhhwx"));
    }


    public static void showFiles(File destFile, int depth) {
        if (destFile.isFile()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("-");
            }
            System.out.println(destFile.getName());
            return;
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("-");
        }
        depth++;
        System.out.println(destFile.getName());
        File[] files = destFile.listFiles();
        for (File file : files) {
            showFiles(file, depth);
        }

    }

    /**
     * 打印每个文件夹以文件
     *
     * @param destFile
     */
    public static void printFileDetails(File srcFile, File destFile) {
        if (srcFile.isFile()) {
            copyFile(srcFile, destFile);
        } else {
            destFile.mkdirs();//确保文件夹存在
//            System.out.println("000"+destFile.getName());
            File[] files = srcFile.listFiles();
            for (File file : files) {
                printFileDetails(file, new File(destFile, file.getName()));
            }
        }

    }

    /**
     * 复制文件夹及内容
     */
    private static void listFiles(File sourcefiles, File mydestpath) {
        File[] listFiles = sourcefiles.listFiles();
        for (File list : listFiles) {
            if (list.isDirectory()) {
                File file = new File(list.getName());
                file.mkdirs();
                listFiles(list, file);
                continue;
            }
            copyFile(list, new File(mydestpath, list.getName()));
            System.out.println(list.getName());
        }
    }

    public static void copyFile(File srcfile, File destfile) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(srcfile);
            os = new FileOutputStream(destfile);
            byte[] car = new byte[1024];
            int len = 0;
            while (-1 != (len = is.read(car))) {
                os.write(car, 0, len);
                os.flush();
                String str = new String(car, 0, len);
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    os.flush();
                    os.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void copy(String srcpath, String destpath) {

        File srcfile = new File(srcpath);
        File destfile = new File(destpath);
        copyFile(srcfile, destfile);
    }

}
