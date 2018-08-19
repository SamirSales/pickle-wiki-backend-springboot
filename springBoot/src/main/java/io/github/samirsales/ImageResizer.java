package io.github.samirsales;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResizer {

    public void resize(String inputImagePath, String outputImagePath, int biggerEdge) throws IOException {

        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        int scaledHeight, scaledWidth;

        if(width < biggerEdge && height < biggerEdge){
            scaledHeight = height;
            scaledWidth = width;
        }else if(width > height){
            scaledWidth = biggerEdge;
            scaledHeight = biggerEdge * height / width;
        }else if(width < height){
            scaledHeight = biggerEdge;
            scaledWidth = biggerEdge * width / height;
        }else {
            scaledHeight = biggerEdge;
            scaledWidth = biggerEdge;
        }

        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public void resize(String inputImagePath,
                              String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public void resize(String inputImagePath,
                              String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

    /**
     * Test resizing images
     */
//    public static void main(String[] args) {
//        String inputImagePath = "D:/Photo/Puppy.jpg";
//        String outputImagePath1 = "D:/Photo/Puppy_Fixed.jpg";
//        String outputImagePath2 = "D:/Photo/Puppy_Smaller.jpg";
//        String outputImagePath3 = "D:/Photo/Puppy_Bigger.jpg";
//
//        try {
//            // resize to a fixed width (not proportional)
//            int scaledWidth = 1024;
//            int scaledHeight = 768;
//            ImageResizer.resize(inputImagePath, outputImagePath1, scaledWidth, scaledHeight);
//
//            // resize smaller by 50%
//            double percent = 0.5;
//            ImageResizer.resize(inputImagePath, outputImagePath2, percent);
//
//            // resize bigger by 50%
//            percent = 1.5;
//            ImageResizer.resize(inputImagePath, outputImagePath3, percent);
//
//        } catch (IOException ex) {
//            System.out.println("Error resizing the image.");
//            ex.printStackTrace();
//        }
//    }

}