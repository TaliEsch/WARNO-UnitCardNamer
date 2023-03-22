package com.taliesch.UnitCardNamer;

import com.taliesch.ParseUniteDescriptorNDF.ParseUniteDescriptorNDF;
import com.taliesch.ParseWARNODB.ParseWARNODB;
import com.taliesch.Unit.Unit;

import java.util.ArrayList;

public class UnitCardNamer {

    ArrayList<Unit> unitsList;

    public UnitCardNamer(String uniteFilename, String warnoDbFilename){
        System.out.println(uniteFilename);
        ParseUniteDescriptorNDF parseUD = new ParseUniteDescriptorNDF();
        unitsList = parseUD.parseUniteDescriptorNDF(uniteFilename);

        ParseWARNODB parseWARNODB = new ParseWARNODB(warnoDbFilename, unitsList);
    }

    public static void main(String[] args){
        String uniteFilename = "ExternalFiles/UniteDescriptor.ndf";
        String warnoDbFilename = "ExternalFiles/units.json";

        UnitCardNamer unitCardNamer =  new UnitCardNamer(uniteFilename, warnoDbFilename);
        unitCardNamer.outputUnitsList();
    }

    public void outputUnitsList(){
        for(Unit unit : unitsList){
            System.out.println(unit.getUnitName());
            System.out.println(unit.getUnitExistsInArsenal());
        }
    }
}
