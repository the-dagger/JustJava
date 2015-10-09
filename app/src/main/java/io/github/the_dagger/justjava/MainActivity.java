package io.github.the_dagger.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int noc = 0;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
          String priceMessage = "Total amount is $" + noc*10 + "\nThank you!";
          displayMessage(priceMessage);
        //  displayPrice(noc * 10);
    }

    public void increment(View view) {
        noc++;
        display(noc);
    }

    public void decrement(View view) {
        if(noc == 0)
        noc = 0;
        else
        noc--;
        display(noc);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.total);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String var) {
        TextView disppriceTextView = (TextView) findViewById(R.id.dispprice);
        disppriceTextView.setText(var);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.dispprice);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}