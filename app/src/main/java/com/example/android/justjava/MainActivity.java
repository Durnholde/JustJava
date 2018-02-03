/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity
{
    int quantity = 0;
    int price = 5;
    int whippedCreamPrice = 1;
    int chocolatePrice = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox a = findViewById(R.id.whipped_cream_checkbox);
        CheckBox b = findViewById(R.id.chocolate_checkbox);
        EditText nameBox = findViewById(R.id.nameBox);
        String name = nameBox.getText().toString();
        boolean hasCream = a.isChecked();
        boolean hasChocolate = b.isChecked();
        int total = countPrice(hasCream, hasChocolate);

        String priceMessage = "Welcome " + name + ", your order is:";
        priceMessage += "\nTotal: $" + total + "\nThank you!";
        priceMessage += "\nWith Whipped Cream : " + hasCream;
        priceMessage += "\nWith Chocolate : " + hasChocolate;
//        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(intent.EXTRA_TEXT, priceMessage);
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    int countPrice(boolean a, boolean b)
    {
        int total = price;
        if(a == true)
            total += whippedCreamPrice;
        if(b == true)
            total += chocolatePrice;
        return total*quantity;
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if(quantity<100)
            quantity++;
        else
            Toast.makeText(getApplicationContext(), "Too much coffee!", Toast.LENGTH_SHORT).show();
        display(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if(quantity>1)
            quantity--;
        else
            Toast.makeText(getApplicationContext(), "At least 1 coffee required!", Toast.LENGTH_SHORT).show();
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}