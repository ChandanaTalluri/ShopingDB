/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author 0809379
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<LineItem> items;

    public Cart() {
        items = new ArrayList<LineItem>();
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item) {
        LineItem lineItem = findItem(item.getProduct().getCode());
        if (lineItem == null)
            items.add(item);
        else
            lineItem.setQuantity(lineItem.getQuantity() + item.getQuantity());
    }
    
    public LineItem findItem(String code){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getCode().equals(code))
                return items.get(i);
        }
        return null;
    }

    public void removeItem(String code) {
        LineItem item = findItem(code);
        if (item != null)
            items.remove(item);
    }
    
    public void updateItem(String code, int quantity){
        LineItem item = findItem(code);
        if (item != null)
            item.setQuantity(quantity);
    }
}
