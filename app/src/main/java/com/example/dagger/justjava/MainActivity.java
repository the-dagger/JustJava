package com.example.dagger.justjava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int noc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit(view);
            }
        });
    }

    public Boolean chocolate(View view) {
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate);
        return (hasChocolate.isChecked());
    }

    public void submit(View view) {
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        Boolean hasChocolate = chocolate.isChecked();
        CheckBox cream = (CheckBox) findViewById(R.id.whippedCream);
        Boolean hasCream = cream.isChecked();
        EditText name = (EditText) findViewById(R.id.name);
        String strname = name.getText().toString();
        displaySummary("Name : " + strname + "\nQuantity : " + noc + "\nTotal Price : $" + calcPrice(hasCream,hasChocolate) + "\nWhipped Cream : " + hasCream + "\nChocolate Topping : " + hasChocolate + "\nThank You!");
    }

    public int calcPrice(boolean hasCream, boolean hasChocolate) {

        int price = 5;
        if (hasCream)
            price += 1;
        if (hasChocolate)
            price += 2;

        return price * noc;
    }

    public void plus(View view) {
        noc++;
        display(noc);
    }

    public Boolean cream(View view) {

        CheckBox hasCream = (CheckBox) findViewById(R.id.whippedCream);
        return (hasCream.isChecked());
    }

    public void minus(View view) {
        if (noc > 0)
            noc--;
        else
            noc = 0;
        display(noc);
    }

    private void display(int var) {
        TextView numberOfCoffee = (TextView) findViewById(R.id.numberOfCoffee);
        numberOfCoffee.setText("" + var);
    }

    public void displaySummary(String var) {
        TextView summary = (TextView) findViewById(R.id.orderSummary);
        summary.setText(var);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
