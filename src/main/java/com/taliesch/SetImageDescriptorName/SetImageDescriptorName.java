package com.taliesch.SetImageDescriptorName;

import com.taliesch.FileToSort.FileToSort;
import com.taliesch.Unit.Unit;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetImageDescriptorName {

    public void setImageName(ArrayList<Unit> unitsList, String sourceDirectory, String outputDirectory){

        String[] factories = {"AA", "AIR", "ART", "HEL", "INF", "LOG", "REC", "TNK"};
        Set<String> currentFileList;
        int correctAmount = 0;

        for (String factory : factories) {
            String fileDirectory = sourceDirectory + "\\" + factory + "\\";

            switch (factory) {
                case "AA" -> correctAmount = 57;
                case "AIR" -> correctAmount = 115;
                case "ART" -> correctAmount = 58;
                case "HEL" -> correctAmount = 51;
                case "INF" -> correctAmount = 206;
                case "LOG" -> correctAmount = 59;
                case "REC" -> correctAmount = 93;
                case "TNK" -> correctAmount = 138;
            }

            int amountPerCategory = 0;


            File[] directories = new File(fileDirectory).listFiles(File::isDirectory);

            assert directories != null;
            int fileListLength = directories.length;
            int counter = 0;

            for (int i = 0; i < fileListLength; i++) {
                String currentDirectory = sourceDirectory + "\\" + factory + "\\" + i + "\\";
                try {
                    currentFileList = listFilesUsingFilesList(currentDirectory);

                    ArrayList<String> fileArrayList = new ArrayList<>(currentFileList);
                    ArrayList<FileToSort> fileToSortArrayList = new ArrayList<>();
                    for (String s : fileArrayList) {
                        FileToSort fileToSort = new FileToSort(s.substring(0, s.lastIndexOf("_") + 1), Integer.valueOf(s.substring(s.lastIndexOf("_") + 1, s.indexOf("."))));
                        fileToSortArrayList.add(fileToSort);
                    }
                    fileToSortArrayList.sort(Comparator.comparing(FileToSort::getFileName));

                    for (FileToSort file : fileToSortArrayList) {
                        while (true) {
                            Unit unit = unitsList.get(counter);
                            counter++;
                            if (unit.getUnitFactory().equals(factory) && unit.getUnitExistsInArsenal()) {
                                File imageFile = new File(currentDirectory + file.getFilePrefix() + file.getFileName() + ".png");
                                BufferedImage bufferedImage = ImageIO.read(imageFile);
                                File fileName = new File(outputDirectory + "\\" + unit.getUnitName() + ".png");
                                ImageIO.write(bufferedImage, "png", fileName);
                                amountPerCategory++;
                                break;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("File Error");
                    e.printStackTrace();
                }
            }
            if (amountPerCategory != correctAmount) {
                System.out.println("Incorrect amount for " + factory + ". Recorded amount was: " + amountPerCategory +
                        ". Amount missing: "+ (correctAmount - amountPerCategory));
            } else{
                System.out.println("Correct amount for " + factory);
            }
        }
    }

    private Set<String> listFilesUsingFilesList(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }
}
