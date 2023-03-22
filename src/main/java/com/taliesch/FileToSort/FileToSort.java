package com.taliesch.FileToSort;

public class FileToSort {

    String filePrefix;
    Integer fileName;

    public FileToSort(String filePrefix, Integer fileName) {
        this.filePrefix = filePrefix;
        this.fileName = fileName;
    }

    public String getFilePrefix () {
        return filePrefix;
    }

    public Integer getFileName () {
        return fileName;
    }

}
