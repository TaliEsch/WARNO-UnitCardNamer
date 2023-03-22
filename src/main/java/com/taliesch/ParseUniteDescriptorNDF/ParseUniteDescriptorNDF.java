package com.taliesch.ParseUniteDescriptorNDF;

import com.taliesch.Unit.Unit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ParseUniteDescriptorNDF {

    public ArrayList<Unit> parseUniteDescriptorNDF (final String filename){
        ArrayList<Unit> readFileUnitsList = new ArrayList<>();

        // Variables
        String line;
        String unitName = null;
        String unitFactory = null;

        // Creates new BufferedReader, to read uniteDescriptor
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))){
            while((line = reader.readLine()) != null){
                while(line.contains("export")){
                    unitName = getVariableFromString(line, " ", 1, "[']");
                    unitName = unitName.substring(0, unitName.indexOf(" is"));
                    line = reader.readLine();
                    while (!line.contains("export")) {
                        line = reader.readLine();
                        if (line.contains("                    Factory            = EDefaultFactories/")) {
                            unitFactory = getVariableFromString(line, "/", 1, "[^\\w]");
                            switch (unitFactory) {
                                case "AT" -> unitFactory = "AA";
                                case "Support" -> unitFactory = "ART";
                                case "Planes" -> unitFactory = "AIR";
                                case "Helis" -> unitFactory = "HEL";
                                case "Infantry" -> unitFactory = "INF";
                                case "Logistic" -> unitFactory = "LOG";
                                case "Recons" -> unitFactory = "REC";
                                case "Tanks" -> unitFactory = "TNK";
                                default -> System.out.println("Error identifying unit category");
                            }
                            break;
                        }
                    }

                    try {
                        Unit currentUnit = new Unit(unitName, unitFactory);
                        readFileUnitsList.add(currentUnit);
                    } catch (Exception e) {
                        System.out.println("Missing field for current unit");
                        e.printStackTrace();
                    }
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("UniteDescriptor not found");
        }

        return readFileUnitsList;
    }

    private String getVariableFromString(final String line,final String indexValue,final int indexPoint,final String regex){
        String editedData = line.substring(line.indexOf(indexValue) + indexPoint);
        return(editedData.trim().replaceAll(regex, ""));
    }

}
