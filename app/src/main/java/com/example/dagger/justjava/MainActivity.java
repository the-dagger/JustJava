package com.example.dagger.justjava;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int noc = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
            public void onClick(View view) {
                submitOrder(view);
            }
        });
    }



//    public void submitOrder(View view){
//        Uri emailuri = Uri.parse("mailto:");
//        Intent intent = new Intent(Intent.ACTION_SENDTO, emailuri);
//        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order for " + strname );
//        intent.putExtra(Intent.EXTRA_TEXT, submit(view));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//
//    }
    public void submitOrder(View view) {

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        Boolean hasChocolate = chocolate.isChecked();
        EditText name = (EditText) findViewById(R.id.name);
        String strname = name.getText().toString();
        CheckBox cream = (CheckBox) findViewById(R.id.whippedCream);
        Boolean hasCream = cream.isChecked();
        String message = getString(R.string.name) + " :" + strname + "\n" + getString(R.string.quantityOrder) + noc + "\n" + getString(R.string.price) + calcPrice(hasCream, hasChocolate) + "\n" + getString(R.string.whippedCream) + " : " + hasCream + "\n" + getString(R.string.chocolate) + " : " + hasChocolate + "\n" + getString(R.string.greeting);
        if (strname.equals("")) {
            Toast.makeText(MainActivity.this, getString(R.string.noName), Toast.LENGTH_SHORT).show();
        } else {
            displaySummary(message);
            Uri emailuri = Uri.parse("mailto:");
            Intent intent = new Intent(Intent.ACTION_SENDTO, emailuri);
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order) + "" + strname);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else
                Toast.makeText(MainActivity.this, getString(R.string.noEmail), Toast.LENGTH_SHORT).show();
        }
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

    public Boolean chocolate(View view) {
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate);
        return (hasChocolate.isChecked());
    }

    public void minus(View view) {
        if (noc > 1)
            noc--;
        else {
            Toast.makeText(MainActivity.this, getString(R.string.noOrder), Toast.LENGTH_SHORT).show();
            noc = 1;
        }
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
            Uri telNumber = Uri.parse("tel:01143601741");
            Intent call = new Intent(Intent.ACTION_DIAL,telNumber);
            startActivity(call);
        }
        if (id == R.id.reach_us){
            Uri webPage = Uri.parse("http://the-dagger.github.io/");
            Intent web = new Intent(Intent.ACTION_VIEW, webPage);
            startActivity(web);
        }
        if(id == R.id.gPlay) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.example.android"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else
                Toast.makeText(MainActivity.this, getString(R.string.noGplay), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
