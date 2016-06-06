import java.io.*;

public class Main {

    private static String mydestpath;
    private static File sourcefiles;

    public static void main(String[] args) {
        sourcefiles = new File("C:\\Users\\ymh\\Desktop\\filetest\\GISDeve\\GISDeve\\Properties");
        mydestpath = "C:\\Users\\ymh\\Desktop\\filetest\\test\\Properties";
        listFiles(sourcefiles);
    }

    private static void listFiles(File myFile) {
        File[] listFiles = myFile.listFiles();
        for (File list : listFiles) {
            if (list.isDirectory()) {
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
            byte[] car = new byte[100];
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
