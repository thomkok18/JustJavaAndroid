package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called for making a summary for the order.
     */
    public String createOrderSummary() {
        int order_summary = calculateorder_summary();
        String name = "Kaptain Kunal", quantityText = "Quantity: " + quantity;
        String order_summaryMessage = name + "\n" + quantityText + "\n" + "Total: $" + order_summary;
        if (order_summary != 0) {
            order_summaryMessage = order_summaryMessage + "\nThank you!";
        }
        return order_summaryMessage;
    }

    /**
     * This method is called for calculating the order_summary.
     */
    public int calculateorder_summary() {
        int order_summary = quantity * 5;
        return order_summary;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary());
    }

    public void increment(View view) {
        quantity++;
        if (quantity < 0) {
            quantity = 0;
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

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}