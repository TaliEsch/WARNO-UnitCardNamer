package com.taliesch.ParseWARNODB;

import com.taliesch.Unit.Unit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ParseWARNODB {
    ArrayList<String> descriptorList = new ArrayList<>();

    public ParseWARNODB (String filename, ArrayList<Unit> unitsList) {

        // Creates new BufferedReader, to read WARNODB
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            try {
                descriptorList = getUnitDescriptors(reader);
                updateUnits(descriptorList, unitsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading WARNODB");
        }
    }

    private ArrayList<String> getUnitDescriptors (BufferedReader reader) throws IOException {
        String line;
        String descriptor;
        String descriptorFinal = null;
        String name = null;

        while ((line = reader.readLine()) != null) {
            while (!line.contains("}") && (line = reader.readLine()) != null) {
                if (line.contains("descriptor")) {
                    String editedData = line.substring(line.indexOf(":") + 1);
                    descriptor = editedData.trim().replaceAll("[, ]", "");
                    descriptorFinal = descriptor.trim().replaceAll("\"", "");
                } else if (line.contains("name")) {
                    String editedData = line.substring(line.indexOf(":") + 1);
                    name = editedData.trim().replaceAll("[, ]", "");
                }
            }
            if (name != null && name.length() != 2){
                descriptorList.add(descriptorFinal);
            }
        }
        return descriptorList;
    }

    private void updateUnits (ArrayList<String> descriptorList, ArrayList<Unit> unitsList){
        for (Unit unit : unitsList){
            if (descriptorList.contains(unit.getUnitName())){
                unit.setUnitExistsInArsenal(true);
            }
        }
    }
}
