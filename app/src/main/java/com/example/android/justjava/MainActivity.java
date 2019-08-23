package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    CheckBox hasWhippedCream, hasChocolate;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hasWhippedCream = (CheckBox) findViewById(R.id.whippedCream);
        hasChocolate = (CheckBox) findViewById(R.id.chocolate);
        name = (EditText) findViewById(R.id.nameText);
    }

    /**
     * This method is called for making a summary for the order.
     */
    public String createOrderSummary() {
        int order_summary = calculateorder_summary();
        String quantityText = getString(R.string.quantityText) + quantity;
        String order_summaryMessage = getString(R.string.order_summary_name) + name.getText() + "\n" + quantityText + "\n" + getString(R.string.totalPrice) + order_summary;
        if (order_summary != 0) {
            order_summaryMessage = order_summaryMessage + "\n" + getString(R.string.thank_you);
        }
        return order_summaryMessage;
    }

    /**
     * This method is called for calculating the order_summary.
     */
    public int calculateorder_summary() {
        int order_summary, whippedCream = 0, chocolate = 0;
        if (hasWhippedCream.isChecked()) {
            whippedCream = quantity;
        }
        if (hasChocolate.isChecked()) {
            chocolate = quantity * 2;
        }
        order_summary = (quantity * 5) + whippedCream + chocolate;
        return order_summary;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String personName = name.getText().toString();
        if (!personName.matches("") && quantity != 0)  {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject) + name.getText());
            intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    public void increment(View view) {
        quantity++;
        if (quantity > 100) {
            quantity = 100;
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity--;
        if (quantity < 0) {
            quantity = 0;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
}