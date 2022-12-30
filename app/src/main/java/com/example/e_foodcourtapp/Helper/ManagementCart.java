package com.example.e_foodcourtapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.e_foodcourtapp.Domain.PopularDomain;
import com.example.e_foodcourtapp.Interface.ChangeNumberItem;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(PopularDomain item){
        ArrayList<PopularDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for(int i = 0; 1 < listFood.size(); i++){
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }

        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Food Added to Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PopularDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<PopularDomain> listFood, int position, ChangeNumberItem changeNumberItem){
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart());
        tinyDB.putListObject("CartList", listFood);
        changeNumberItem.changed();
    }

    public void minusNumberFood(ArrayList<PopularDomain> listFood, int position, ChangeNumberItem changeNumberItem){
        if(listFood.get(position).getNumberInCart() == 1){
            listFood.remove(position);
        }else{
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", listFood);
        changeNumberItem.changed();
    }

    public Double getTotalFee(){
        ArrayList<PopularDomain> listFood = getListCart();
        double fee = 0;
        for (int i = 0; i < listFood.size(); i++) {
            fee = fee + (listFood.get(i).getFee() * listFood.get(i).getNumberInCart());
        }
        return fee;
    }
}
