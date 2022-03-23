package com.example.dustnshine.ui;

import java.util.ArrayList;

public interface QuantityListener {
    void onQuantityChange(ArrayList<Integer> servicesID, ArrayList<String> servicesName, ArrayList<Integer> servicesPrice, ArrayList<Integer> quantityOfService);
}
