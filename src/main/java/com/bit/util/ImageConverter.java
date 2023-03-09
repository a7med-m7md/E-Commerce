package com.bit.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageConverter {
    public byte[] converToByteArray(String path) throws IOException {
        BufferedImage bImage = ImageIO.read(new File(path));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String tokens[] = path.split("[.]",0);
        ImageIO.write(bImage, tokens[tokens.length-1], bos );
        byte [] data = bos.toByteArray();

        return data;
    }
    public File converToImage(byte[] bytes, String path) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        BufferedImage bImage2 = ImageIO.read(bis);
        File file = new File(path) ;
        String tokens[] = path.split("[.]",0);
        ImageIO.write(bImage2, tokens[tokens.length-1], file);

        return file;
    }
}
