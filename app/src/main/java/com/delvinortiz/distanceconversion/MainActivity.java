package com.delvinortiz.distanceconversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Spinner unitTypeSpinner;
    private EditText amountTextView;
    TextView mileTextView, inchTextView, meterTextView, cmTextView, feetTextView, kmTextView,
    yardTextView, micrometerTextView, nmTextView, nauticalMileTextView, mmTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //now we are going to have to add items to our Spinner
        //and we will use a string resource file to do that
        addItemsToUnitTypeSpinner();

        //method for the listener of the UnitType selected
        addListenerToUnitTypeSpinner();

        //get data that was entered in the EditText box
        amountTextView = (EditText)findViewById(R.id.amount_text_view);

        //method to initialize all unit textviews
        initializeTextViews();
    }

    //this is just to format the array adapter and set the adapter to the spinner
    public void addItemsToUnitTypeSpinner(){

        unitTypeSpinner = (Spinner)findViewById(R.id.unit_type_spinner);

        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.conversion_types, android.R.layout.simple_spinner_item);

        //this is how you set the unitTypeSpinner to have a a drop down layout
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //now just set the unitTypeSpinnerAdapter to the spinner
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

        //DONE
    }

    //this is the listener method for spinner whenever a specific unit is selected
    public void addListenerToUnitTypeSpinner(){
        unitTypeSpinner = (Spinner)findViewById(R.id.unit_type_spinner);

        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();

                //from the notes, check if I wanted to convert from the base unit miles
                checkIfConvertingFromMile(itemSelectedInSpinner);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //on nothing selected, I attempted to use the previous selected item on spinner
                //didn't work
                //String lastItemSelected = parent.getItemAtPosition(1).toString();
                //checkIfConvertingFromMile(lastItemSelected);
            }
        });
    }

    public void initializeTextViews(){
        mileTextView = (TextView)findViewById(R.id.mile_text_view);
        inchTextView = (TextView)findViewById(R.id.inch_text_view);
        meterTextView = (TextView)findViewById(R.id.meter_text_view);
        cmTextView = (TextView)findViewById(R.id.cm_text_view);
        feetTextView = (TextView)findViewById(R.id.feet_text_view);
        kmTextView = (TextView)findViewById(R.id.km_text_view);
        yardTextView = (TextView)findViewById(R.id.yard_text_view);
        micrometerTextView = (TextView)findViewById(R.id.micrometer_text_view);
        nmTextView = (TextView)findViewById(R.id.nm_text_view);
        nauticalMileTextView = (TextView)findViewById(R.id.nauticalmile_text_view);
        mmTextView = (TextView)findViewById(R.id.mm_text_view);
    }

    public void checkIfConvertingFromMile(String currentUnit){

        /**
         * currentUnit holds the selected unit type that the user inputted and we have to
         * check if that unit type is equal to miles and if it is implement method "updateUnitTypeUsingMile"
         * else, implement method, "updateUnitTypeUsingOther
         */

        if(currentUnit.equals("mile")){
            updateUnitTypeUsingMile(Quantity.Unit.mile);
        }else{
            if(currentUnit.equals("meter")){
                updateUnitTypeUsingOther(Quantity.Unit.meter);
            }else if(currentUnit.equals("inch")){
                updateUnitTypeUsingOther(Quantity.Unit.inch);
            }else if(currentUnit.equals("feet")){
                updateUnitTypeUsingOther(Quantity.Unit.feet);
            }else if(currentUnit.equals("centimeter")){
                updateUnitTypeUsingOther(Quantity.Unit.cm);
            }else if(currentUnit.equals("kilometer")){
                updateUnitTypeUsingOther(Quantity.Unit.km);
            }else if(currentUnit.equals("yard")){
                updateUnitTypeUsingOther(Quantity.Unit.yard);
            }else if(currentUnit.equals("micrometer")){
                updateUnitTypeUsingOther(Quantity.Unit.micrometer);
            }else if(currentUnit.equals("nanometer")){
                updateUnitTypeUsingOther(Quantity.Unit.nm);
            }else if(currentUnit.equals("nautical mile")){
                updateUnitTypeUsingOther(Quantity.Unit.nauticalmile);
            }else if(currentUnit.equals("millimeter")){
                updateUnitTypeUsingOther(Quantity.Unit.mm);
            }else{
                //updateUnitTypeUsingOther(Quantity.Unit.furlong);
            }
        }
    }

    /**
     * This is the method to update all of the unit types using miles(base unit)
     * @param currentUnit
     */

    public void updateUnitTypeUsingMile(Quantity.Unit currentUnit){

        //first we need to take care of updating the text field for mile
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

        String mileValueAndUnit = doubleToConvert + " mile";

        mileTextView.setText(mileValueAndUnit);

        //now all we need to do is update all of the remaining text fields using mile
        //and in this method we will pass on the value that we need to convert as well as the unit type and also the proper textviews
        updateUnitTextFieldsUsingMile(doubleToConvert, Quantity.Unit.meter, meterTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, Quantity.Unit.inch, inchTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, Quantity.Unit.feet, feetTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, Quantity.Unit.cm, cmTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, Quantity.Unit.km, kmTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, Quantity.Unit.yard, yardTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, Quantity.Unit.micrometer, micrometerTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, Quantity.Unit.nm, nmTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, Quantity.Unit.nauticalmile, nauticalMileTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, Quantity.Unit.mm, mmTextView);
    }

    /**
     * This is the method for updating the text fields for the given units that you converted to.
     * A mile Quantity object and in that converted from mile to the unitConvertingTo
     * @param doubleToConvert
     * @param unitConvertingTo
     * @param unitTextView
     */
    public void updateUnitTextFieldsUsingMile(double doubleToConvert, Quantity.Unit unitConvertingTo, TextView unitTextView){

        //Quantity newUnit  = new Quantity(unitConvertingTo.fromBaseUnit(doubleToConvert), unitConvertingTo);
        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.mile);

        //converted from miles to the preferred unit
        String newUnitAndValue = unitQuantity.to(unitConvertingTo).toString();

        unitTextView.setText(newUnitAndValue);
    }

    /**
     * Now this method is for when the use selects a unit type that is not miles and
     * so we have convert that double amount into miles and then to all of the Unit types
     * @param currentUnit
     */
    public void updateUnitTypeUsingOther(Quantity.Unit currentUnit){

        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

        //we will take care of the mile quantity and update its unit and textfield because it's special
        Quantity unitQuantity = new Quantity(doubleToConvert, currentUnit);
        String mileValueAndUnit = unitQuantity.to(Quantity.Unit.mile).toString();
        mileTextView.setText(mileValueAndUnit);

        //now lets pass on these values into another method in order to convert all of the text fields
        updateUnitTextFieldsUsingMile(doubleToConvert, currentUnit, Quantity.Unit.meter, meterTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, currentUnit, Quantity.Unit.inch, inchTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, currentUnit, Quantity.Unit.feet, feetTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, currentUnit, Quantity.Unit.cm, cmTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, currentUnit, Quantity.Unit.km, kmTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, currentUnit, Quantity.Unit.yard, yardTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, currentUnit, Quantity.Unit.micrometer, micrometerTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, currentUnit, Quantity.Unit.nm, nmTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, currentUnit, Quantity.Unit.nauticalmile, nauticalMileTextView);
        updateUnitTextFieldsUsingMile(doubleToConvert, currentUnit, Quantity.Unit.mm, mmTextView);

        //now this is where we check that the current unit selected has the correct values and text in its text view
        String currentUnitTextViewText = doubleToConvert + " " + currentUnit.name();

        String currentUnitTextViewName = currentUnit.name() + "_text_view";

        int currentId = getResources().getIdentifier(currentUnitTextViewName, "id", MainActivity.this.getPackageName());

        TextView currentTextView = (TextView)findViewById(currentId);
        currentTextView.setText(currentUnitTextViewText);
    }

    /**
     * now this method will help us in converting our currentUnits value to the all of the other unit and set the textFields too
     * not that this method is different than the one we used back up there with mile and that's because we followed the UML diagram approach
     * @param doubleToConvert
     * @param currentUnit
     * @param preferredUnit
     * @param currentTextView
     */
    public void updateUnitTextFieldsUsingMile(double doubleToConvert, Quantity.Unit currentUnit, Quantity.Unit preferredUnit, TextView currentTextView){

        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        //String newUnitValueAndQuantityText = currentQuantitySelected.to(preferredUnit).toString();

        //The bottom string is used instead of the one above but we can check to see if both work correctly
        String newUnitValueAndQuantityText = currentQuantitySelected.to(Quantity.Unit.mile).to(preferredUnit).toString();
        currentTextView.setText(newUnitValueAndQuantityText);
    }

    /**
     * So this application was a success, maybe try out the commented out lines of code.
     * The main take away is to learn how this unit conversion works and the skills/tricks to building an app
     */
}
