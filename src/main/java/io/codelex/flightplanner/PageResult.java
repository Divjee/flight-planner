package io.codelex.flightplanner;

import java.util.ArrayList;

public class PageResult {
    private double page;
    private int totalItems;
    private ArrayList<Flight> items;
    int pageDivider = 10;

    public PageResult(double page, int totalItems, ArrayList<Flight> items) {
        this.page = page = PageNr();
        this.totalItems  = totalItems = items.size();
        this.items =  items;
    }

    public PageResult(ArrayList<Flight> items) {
        this.items = items;
    }

    public double getPage() {
        return page;
    }

    public double PageNr() {

        if(pageDivider / totalItems == 1 && page > 1){
            page ++;
            pageDivider +=10;
        }else{
            page = 0;
        }
        return page;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public ArrayList<Flight> getItems() {
        return items;
    }

    public void setItems(ArrayList<Flight> items) {
        this.items = items;
    }

    public void setPage(double page) {
        this.page = page;
    }


}
