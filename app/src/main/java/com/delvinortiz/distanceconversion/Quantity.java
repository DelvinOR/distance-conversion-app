package com.delvinortiz.distanceconversion;

import java.text.DecimalFormat;

public class Quantity {
    //each quantity is going to hold a value and a unit
    final double value;
    final Unit unit;

    //enum use a constant key to hold values and this is helpful to convert from teaspoons to anything and vice versa
    //make it static because it is part of the class and does not rely on any fields and instead provides fields
    public static enum Unit{

        mile(1.00d), inch(63360d), meter(1609.34d), cm(160934d), feet(5280d), km(1.60934d),
        yard(1760d), micrometer(1609340000d), nm(160934000000d), nauticalmile(0.868976d),
        mm(1609340d);

        //this is where you define your base unit which in my case is mile
        final static Unit baseUnit = mile;

        //since this is not static, this applies to every enum that their byBaseUnit value is inMile
        final double byBaseUnit;

        private Unit(double inMile){this.byBaseUnit = inMile;}

        public double toBaseUnit(double value){
            //say inches is the base, then to go from feet to inches you divide by ratio of feet in inches which would be (1/12)
            return value/byBaseUnit;
        }

        public double fromBaseUnit(double value){
            return value * byBaseUnit;
        }
    }

    public Quantity(double value, Unit unit){
        super();
        this.value = value;
        this.unit = unit;
    }

    //now lets convert from miles to the desired type
    public Quantity to(Unit newUnit){

        Unit oldUnit = this.unit;

        //basically, you go from the old unit and convert into the base unit(miles) using each byBaseUnit unique value
        //and then you you go into the new unit from having the base unit(miles), again using each unique byBaseUnit ratio
        return new Quantity(newUnit.fromBaseUnit(oldUnit.toBaseUnit(this.value)), newUnit);
    }

    public String toString(){
        DecimalFormat df = new DecimalFormat("#.0000");
        return df.format(this.value) + " " + this.unit.name();
    }




}
