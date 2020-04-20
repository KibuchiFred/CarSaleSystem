package com.grocery.demo.Service;

import com.grocery.demo.Repository.DataCentre;

public class CalculateSum {

    private DataCentre dataCentre;

    public void setDataCentre(DataCentre dataCentre) {
        this.dataCentre = dataCentre;
    }

    public int dataCalculator(){
        int sum =0;
        int [] data = dataCentre.retriveData();

        for(int value : data){
            sum += value;
        }
        return sum;
    }


    public int calcSum (int [] data){
        int sum = 0;

        for (int value : data){
            sum += value;
        }
        return sum;
    }
}
