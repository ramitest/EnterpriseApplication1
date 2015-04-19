package paketti;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import javax.imageio.ImageIO;
import org.json.simple.*;

public class FileReader {

    public static String PICS_DIR = "pics";
    public static String VIDS_DIR = "vids";
    public static String imgRootDir  = System.getProperty("my.imgviewer.imgdir");
    public static String urlRootPath = System.getProperty("my.imgviewer.urlpathroot");

    public static JSONArray getSubPathsOfPicsDirs() throws IOException {
        if( isDir(Paths.get(imgRootDir)) )
            for(Path dir : getDirStream(Paths.get(imgRootDir)))
                if( dir.endsWith(PICS_DIR) )
                    return getDirListing(dir);
        return null;
    }

    public static JSONArray getPicsFromDir(String imgDir, boolean fullPath) throws IOException {
        if(false == fullPath)
            imgDir = addBSlashToEnd(imgRootDir) + replaceSlashtoBSlash(imgDir);

        JSONArray imgsJson = new JSONArray();
        if( isDir( Paths.get(imgDir) ) )
            for ( Path imgPath : getImagesStream( Paths.get(imgDir) ) )
                imgsJson.add(getImgJson(imgPath.toString()));
        return imgsJson;
    }

    protected static DirectoryStream<Path> getDirStream(Path rootDirPath) throws IOException {
        return Files.newDirectoryStream(rootDirPath, getDirsFilter());
    }

    private static DirectoryStream<Path> getImagesStream(Path imgDirPath) throws IOException {
        return Files.newDirectoryStream( imgDirPath, getImagesFilter() );
    }

    private static boolean isDir(Path rootDirPath) {
        if(Files.exists(rootDirPath, LinkOption.NOFOLLOW_LINKS))
            if(Files.isDirectory(rootDirPath, LinkOption.NOFOLLOW_LINKS))
                return true;
        return false;
    }

    private static DirectoryStream.Filter<Path> getDirsFilter() {
        return new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path file) throws IOException {
                return Files.isDirectory(file);
            }
        };
    }
    
    private static DirectoryStream.Filter<Path> getImagesFilter() {
        return new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path file) throws IOException {
                String fileStr = file.toString();
                return fileStr.endsWith(".jpg") ||  fileStr.endsWith(".jpeg") ||
                       fileStr.endsWith(".gif") || fileStr.endsWith(".png") ||
                       fileStr.endsWith(".tiff");
            }
        };
    }

    private static JSONArray getDirListing(Path dir) throws IOException {
        JSONArray list = new JSONArray();
        for(Path subDir : getDirStream( dir ))
            list.add( getSubPathFromDiskFullPath( subDir.toString() ) );
        return list;
    }

    protected static JSONObject getImgJson(String fileName) throws IOException {
        JSONObject obj = new JSONObject();
        BufferedImage bimg = ImageIO.read(new File(fileName));
        obj.put("filename", changeDiskPathToUrl(fileName));
        obj.put("width",    bimg.getWidth());
        obj.put("height",   bimg.getHeight());
        return obj;
    }

    protected static String changeDiskPathToUrl(String fileName) {
        String subPath = getSubPathFromDiskFullPath(fileName);
        return urlRootPath + subPath;
    }

    protected static String getSubPathFromDiskFullPath(String fileName) {
        String subPath = fileName.substring(imgRootDir.length()+1);
        return replaceBSlashtoSlash(subPath);
    }

    protected static String replaceBSlashtoSlash(String str) {
        return str.replace("\\", "/");
    }
    protected static String replaceSlashtoBSlash(String str) {
        return str.replace("/", "\\");
    }
    protected static String addBSlashToEnd(String str) {
        if(str.endsWith("\\"))
            return str;
        return str.concat("\\");
    }
    
}

