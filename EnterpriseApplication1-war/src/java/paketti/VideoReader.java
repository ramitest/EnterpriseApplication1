package paketti;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import java.util.List;
import org.json.simple.*;

public class VideoReader extends AbstractReader {

    public JSONArray getSubPathsOfVidsDirs() throws IOException {
        return getSubPathsDirs(getPicsDir());
    }

    public JSONArray getVidsFromDir(String imgDir, boolean fullPath) throws IOException {
        return getMediaFilesFromDir(imgDir, fullPath);
    }

    @Override
    protected List<String> getMediaFileEnds() {
        return Arrays.asList(".mp4", ".ogg", ".webm");
    }

    @Override
    protected JSONObject getJsonObject(String fileName) throws IOException {
        JSONObject obj = new JSONObject();
        BufferedImage bimg = ImageIO.read(new File(fileName));
        obj.put("src", changeDiskPathToUrl(fileName));
        obj.put("width",    bimg.getWidth());
        obj.put("height",   bimg.getHeight());
        return obj;
    }
}

