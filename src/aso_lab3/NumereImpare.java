package aso_lab3;

import java.util.Iterator;
import java.util.LinkedList;

class NumereImpare {
    private int number;
    public NumereImpare() {
        number = 0;
    }
    public void setNumber(int _number) {
        number = _number;
    }
    public int getNumber() {
        return number;
    }
}
class NumereImpareStr {
    public synchronized String getProductsStr(LinkedList<NumereImpare> _products) {
        String all = "";
        Iterator<NumereImpare> iterator = _products.iterator();
        String number;
        NumereImpare element;
        while (iterator.hasNext()) {
            element = iterator.next();
            number = String.valueOf(element.getNumber());
            if (number.length() < 2) all = all + " " + number;
            else all = all + number;
            if (iterator.hasNext()) all = all + " | ";
        }
        return all;
    }
}
