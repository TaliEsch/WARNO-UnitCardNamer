package com.taliesch.Unit;

import java.util.ArrayList;

public class Unit {

    private String unitName;
    private String unitCountry;

    /* TODO: MAKE A LIST OF
            TTagsModuleDescriptor
        (
            TagSet = [

            And assign to category based on this
            Also note allowed for missile roe
     */

    private String unitType;
    private String unitFormation;

    private ArrayList<String> unitTagsList;

    // UnitConcealmentBonus
    private Float unitConcealmentBonus;

    // True/False, denotes weather the unit has a weapon or not (WeaponManager is TModuleSelector)
    private Boolean unitUnarmed;

    private Integer unitArmourFront;
    private Integer unitArmourSides;
    private Integer unitArmourRear;
    private Integer unitArmourTop;

    private Float unitHP;
    private Float unitECM;

    private Integer unitMaxSpeed;

    // Only if aircraft
    private Integer unitAltitude;
    private Integer unitTurnRadius;

    // Only if vehicle
    private Integer unitFuelCapacity;
    private Float unitFuelMoveDuration;

    private String unitFactory;

    private Integer unitCost;

    // Only if aircraft
    // private Integer unitEvacuationTime; - CURRENTLY UNUSED (ALWAYS 15)
    private Integer unitTravelDuration;


    // SpecialtiesList = [
    /*    private ArrayList<String> unitSpecialtiesList;*/

    // RealRoadSpeed
    private Integer unitRoadSpeed;

    // Weapon Stats
/*    private ArrayList<Ammuniton> unitAmmuntionList;*/

    // if -1 then no weapon
    private ArrayList<String> unitWeaponSalvos;

    private ArrayList<String> unitWeaponMainSalvo;

    private boolean unitExistsInArsenal = false;


/*    public Unit (String unitName, String unitCountry, String unitType, String unitFormation, ArrayList<String> unitTagsList, Float unitConcealmentBonus,
                 Integer unitArmourFront, Integer unitArmourSides, Integer unitArmourRear, Integer unitArmourTop,
                 Float unitHP, Float unitECM, Integer unitMaxSpeed, Integer unitAltitude, Integer unitTurnRadius, Integer unitFuelCapacity, Float unitFuelMoveDuration,
                 String unitFactory, Integer unitCost, Integer unitTravelDuration, Integer unitRoadSpeed){
        this.unitName = unitName;
        this.unitCountry = unitCountry;
        this.unitType = unitType;
        this.unitFormation = unitFormation;
        this.unitTagsList = unitTagsList;
        this.unitConcealmentBonus = unitConcealmentBonus;
        this.unitArmourFront = unitArmourFront;
        this.unitArmourSides = unitArmourSides;
        this.unitArmourRear = unitArmourRear;
        this.unitArmourTop = unitArmourTop;
        this.unitHP = unitHP;
        this.unitECM = unitECM;
        this.unitMaxSpeed = unitMaxSpeed;
        this.unitAltitude = unitAltitude;
        this.unitTurnRadius = unitTurnRadius;
        this.unitFuelCapacity = unitFuelCapacity;
        this.unitFuelMoveDuration = unitFuelMoveDuration;
        this.unitFactory = unitFactory;
        this.unitCost = unitCost;
        this.unitTravelDuration = unitTravelDuration;
        this.unitRoadSpeed = unitRoadSpeed;
    }*/

    public Unit(String unitName, String unitFactory){
        this.unitName = unitName;
        this.unitFactory = unitFactory;
    }

    public String getUnitName(){
        return unitName;
    }

    public String getUnitFactory(){
        return unitFactory;
    }

    public boolean getUnitExistsInArsenal(){
        return unitExistsInArsenal;
    }

    public void setUnitExistsInArsenal(boolean unitExistsInArsenal){
        this.unitExistsInArsenal = unitExistsInArsenal;
    }

}


