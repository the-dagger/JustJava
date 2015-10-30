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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {
    int noc = 1;
    ToggleButton toggle;
    Button restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restart = (Button) findViewById(R.id.button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView noc1 = (TextView) findViewById(R.id.numberOfCoffee);
        toggle= (ToggleButton) findViewById(R.id.toggle);
        noc1.setText(noc+"");
        toggle.setOnCheckedChangeListener(this);
        Button plus = (Button) findViewById(R.id.plus);
        Button minus = (Button) findViewById(R.id.minus);
        plus.setOnClickListener(MainActivity.this);
        minus.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            //            @Override
            public void onClick(View view) {
                submitOrder(view);
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        EditText name = (EditText) findViewById(R.id.name);
        String strname = name.getText().toString();
        super.onSaveInstanceState(outState);
        outState.putInt("noc", noc);
        //outState.putString("Name", strname);
    }



    public void submitOrder(View view) {
        EditText name = (EditText) findViewById(R.id.name);
        String strname = name.getText().toString();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        Boolean hasChocolate = chocolate.isChecked();
        CheckBox cream = (CheckBox) findViewById(R.id.whippedCream);
        Boolean hasCream = cream.isChecked();
        String message = getString(R.string.name) + " :" + strname + "\n" + getString(R.string.quantityOrder) + noc + "\n" + getString(R.string.price) + calcPrice(hasCream, hasChocolate) + "\n" + getString(R.string.whippedCream) + " : " + hasCream + "\n" + getString(R.string.chocolate) + " : " + hasChocolate + "\n" + getString(R.string.greeting);
        if (strname.equals("")) {
            Toast.makeText(MainActivity.this, getString(R.string.noName), Toast.LENGTH_SHORT).show();
//            Toast toast = new Toast(this);
//            toast.setDuration(Toast.LENGTH_SHORT);
//            LayoutInflater lin = getLayoutInflater();
//            View toastobj = lin.inflate(R.layout.toast_layout,(ViewGroup)findViewById(R.id.toastLinearLayout));
//            toast.setView(toastobj);
//            toast.show();
        } else {
            displaySummary(message);
            Uri emailuri = Uri.parse("mailto:");
            Intent intent = new Intent(Intent.ACTION_SENDTO, emailuri);
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order) + "" + strname);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else
                Toast.makeText(this, getString(R.string.noEmail), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        EditText name = (EditText) findViewById(R.id.name);
        String strname = name.getText().toString();
        super.onRestoreInstanceState(savedInstanceState);
        noc = savedInstanceState.getInt("noc");
        //strname = savedInstanceState.getString("Name");
    }
    public int calcPrice(boolean hasCream, boolean hasChocolate) {

        int price = 5;
        if (hasCream)
            price += 1;
        if (hasChocolate)
            price += 2;

        return price * noc;
    }


    public Boolean cream(View view) {

        CheckBox hasCream = (CheckBox) findViewById(R.id.whippedCream);
        return (hasCream.isChecked());
    }

    public Boolean chocolate(View view) {
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate);
        return (hasChocolate.isChecked());
    }

    private void display(int var) {
        TextView numberOfCoffee = (TextView) findViewById(R.id.numberOfCoffee);
        numberOfCoffee.setText("" + var);
    }

    public void displaySummary(String var) {
        TextView summary = (TextView) findViewById(R.id.orderSummary);
        summary.setText(var);
    }
//    public void newLayout(View v){
//        LinearLayout l;
//        TextView t;
//        Button b;
//        l = new LinearLayout(this);
//        t = new TextView(this);
//        b = new Button(this);
//        ViewGroup.LayoutParams dimension = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        t.setText("HELLO");
//        b.setText("Button");
//        l.setOrientation(LinearLayout.VERTICAL);
//        l.setLayoutParams(dimension);
//        ViewGroup.LayoutParams dimension2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        t.setLayoutParams(dimension2);
//        b.setLayoutParams(dimension2);
//        l.addView(t);
//        l.addView(b);
//        setContentView(l);
//    }
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.plus)
        {
            noc++;
            display(noc);
        }
        else if (v.getId() == R.id.minus)
        {
            if (noc > 1)
               noc--;
        else {
            Toast.makeText(MainActivity.this, getString(R.string.noOrder), Toast.LENGTH_SHORT).show();
            noc = 1;
        }
        display(noc);
        }
    }

 public void restart(View v){
     Intent i = new Intent(this,MainActivity.class);
     startActivity(i);
 }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
            restart.setVisibility(View.VISIBLE);
        }
        else
        {
            restart.setVisibility(View.GONE);
        }
    }
}
