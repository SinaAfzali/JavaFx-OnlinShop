package com.example.OnlineShop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class Methods {

    static Stage stage = new Stage();

    public static FXMLLoader loader(String fxml) {
        return new FXMLLoader(Main.class.getResource(fxml));
    }


    public static String imageRand() {
        Random random = new Random();
        int rand = random.nextInt(15) + 1;
        return switch (rand) {
            case 1 -> "src/main/resources/com/example/image/14726.png";
            case 2 -> "src/main/resources/com/example/image/17624.png";
            case 3 -> "src/main/resources/com/example/image/18543.png";
            case 4 -> "src/main/resources/com/example/image/79199.png";
            case 5 -> "src/main/resources/com/example/image/21695.png";
            case 6 -> "src/main/resources/com/example/image/28642.png";
            case 7 -> "src/main/resources/com/example/image/33697.png";
            case 8 -> "src/main/resources/com/example/image/34837.png";
            case 9 -> "src/main/resources/com/example/image/43481.png";
            case 10 -> "src/main/resources/com/example/image/53763.png";
            case 11 -> "src/main/resources/com/example/image/59788.png";
            case 12 -> "src/main/resources/com/example/image/63598.png";
            case 13 -> "src/main/resources/com/example/image/72696.png";
            case 14 -> "src/main/resources/com/example/image/82473.png";
            case 15 -> "src/main/resources/com/example/image/91758.png";
            default -> "";
        };
    }

    public static String codeImageRand(String image) {
        return switch (image) {
            case "src/main/resources/com/example/image/14726.png" -> "14726";
            case "src/main/resources/com/example/image/17624.png" -> "17624";
            case "src/main/resources/com/example/image/18543.png" -> "18543";
            case "src/main/resources/com/example/image/79199.png" -> "79199";
            case "src/main/resources/com/example/image/21695.png" -> "21695";
            case "src/main/resources/com/example/image/28642.png" -> "28642";
            case "src/main/resources/com/example/image/33697.png" -> "33697";
            case "src/main/resources/com/example/image/34837.png" -> "34837";
            case "src/main/resources/com/example/image/43481.png" -> "43481";
            case "src/main/resources/com/example/image/53763.png" -> "53763";
            case "src/main/resources/com/example/image/59788.png" -> "59788";
            case "src/main/resources/com/example/image/63598.png" -> "63598";
            case "src/main/resources/com/example/image/72696.png" -> "72696";
            case "src/main/resources/com/example/image/82473.png" -> "82473";
            case "src/main/resources/com/example/image/91758.png" -> "91758";
            default -> "";
        };
    }

    public static boolean searchUserName(String userName, int role) {
                if (role == 1 ) {
                    for (int i = 0; i < Information.getPersons().size(); i++) {
                        if (Information.getPersons().get(i) instanceof Customer && userName.equals(Information.getPersons().get(i).getUserName())) return true;
                    }
                }
                if (role == 2 ) {
                    for (int i = 0; i < Information.getPersons().size(); i++) {
                        if (Information.getPersons().get(i) instanceof Seller && userName.equals(Information.getPersons().get(i).getUserName())) return true;
                    }
                }

        return false;
    }



    public static boolean checkFieldText(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') return false;
        }
        return true;
    }


    public static void notification(String text, int second) {
        Notifications notificationBuilder = Notifications.create()
                .title("")
                .text(text)
                .darkStyle()
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .hideAfter(Duration.seconds(second))
                .position(Pos.TOP_CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent a) {
                        //
                    }
                });


        notificationBuilder.showInformation();
    }

    public static String moneyStandard(double money) {
        int m = (int) money;
        String s = String.valueOf(m);
        String f = "";
        for (int i = 0; i < s.length(); i++) {
            f += s.charAt(i);
            if ((s.length() - i - 1) % 3 == 0 && i != s.length() - 1) f += '/';
        }
        return f;
    }

    public static int[] sortBy(ArrayList<Product> products, int type , int category) {
        double[] data = new double[products.size()];
        for (int i = 0; i < products.size(); i++) {
            data[i] = products.get(i).getPrice();
        }
        double[] data1 = new double[products.size()];
        for (int i = 0; i < products.size(); i++) {
            data1[i] = products.get(i).getScore();
        }
        int sw = 0;
        int[] index = new int[products.size()];
        for (int i = 0; i < index.length; i++) {
            index[i] = -1;
        }
        double current = 0;
        switch (type) {
            case 1 -> {
                for (int i = 0; i < data.length; i++) {
                    for (int j = i + 1; j < data.length; j++) {
                        if (data[i] > data[j]) {
                            current = data[i];
                            data[i] = data[j];
                            data[j] = current;
                        }
                    }
                }

                for (int i = 0; i < data.length; i++) {
                    for (int j = 0; j < products.size(); j++) {
                        if (data[i] == products.get(j).getPrice()) {
                            boolean p = true;
                            for (int k = 0; k < index.length; k++) {
                                if (index[k] == j) p = false;
                            }
                            if (p) {
                                index[sw] = j;
                                sw++;
                            }
                        }
                    }
                }
                int f=0;
                if(category==0)return index;
                else {
                    for (int j : index) {
                        if (products.get(j).getCategory().equals(Methods.returnCategory(category))) f++;
                    }
                    int[]index2=new int[f];
                    f=0;
                    for (int j : index) {
                        if (products.get(j).getCategory().equals(Methods.returnCategory(category))) {
                            index2[f] = j;
                            f++;
                        }
                    }
                    return index2;
                }
            }
            case 2 -> {
                for (int i = 0; i < data.length; i++) {
                    for (int j = i + 1; j < data.length; j++) {
                        if (data[i] < data[j]) {
                            current = data[i];
                            data[i] = data[j];
                            data[j] = current;
                        }
                    }
                }
                for (double datum : data) {
                    for (int j = 0; j < products.size(); j++) {
                        if (datum == products.get(j).getPrice()) {
                            boolean p = true;
                            for (int k = 0; k < index.length; k++) {
                                if (index[k] == j) p = false;
                            }
                            if (p) {
                                index[sw] = j;
                                sw++;
                            }
                        }
                    }
                }
                int f=0;
                if(category==0)return index;
                else {
                    for (int j : index) {
                        if (products.get(j).getCategory().equals(Methods.returnCategory(category))) f++;
                    }
                    int[]index2=new int[f];
                    f=0;
                    for (int j : index) {
                        if (products.get(j).getCategory().equals(Methods.returnCategory(category))) {
                            index2[f] = j;
                            f++;
                        }
                    }
                    return index2;
                }
            }
            case 3 -> {
                for (int i = 0; i < data1.length; i++) {
                    for (int j = i + 1; j < data1.length; j++) {
                        if (data1[i] < data1[j]) {
                            current = data1[i];
                            data1[i] = data1[j];
                            data1[j] = current;
                        }
                    }
                }
                for (double v : data1) {
                    for (int j = 0; j < products.size(); j++) {
                        if (v == products.get(j).getScore()) {
                            boolean p = true;
                            for (int k = 0; k < index.length; k++) {
                                if (index[k] == j) p = false;
                            }
                            if (p) {
                                index[sw] = j;
                                sw++;
                            }
                        }
                    }
                }
                int f=0;
                if(category==0)return index;
                else {
                    for (int j : index) {
                        if (products.get(j).getCategory().equals(Methods.returnCategory(category))) f++;
                    }
                    int[]index2=new int[f];
                    f=0;
                    for (int j : index) {
                        if (products.get(j).getCategory().equals(Methods.returnCategory(category))) {
                            index2[f] = j;
                            f++;
                        }
                    }
                    return index2;
                }
            }

        }
        return index;
    }


    public static int[] sortByPrice(ArrayList<Product>products,double min, double max , int category){
        double[] data = new double[products.size()];
        for (int i = 0; i < products.size(); i++) {
            data[i] = products.get(i).getPrice();
        }
        double current=0;
        int sw=0;

        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) {
                    current = data[i];
                    data[i] = data[j];
                    data[j] = current;
                }
            }
        }
        int count=0;
        for (int i=0;i<data.length;i++){
            if (data[i]>=min && data[i]<=max)count++;
        }
        double[] data2=new double[count];
        int count2=0;
        for (int i=0;i<data.length;i++){
            if (data[i]>=min && data[i]<=max){
                data2[count2]=data[i];
                count2++;
            }
        }
        int[] index = new int[data2.length];
        Arrays.fill(index, -1);
        for (int i = 0; i < data2.length; i++) {
            for (int j = 0; j < products.size(); j++) {
                if (data2[i] == products.get(j).getPrice()) {
                    boolean p = true;
                    for (int k = 0; k < index.length; k++) {
                        if (index[k] == j) p = false;
                    }
                    if (p) {
                        index[sw] = j;
                        sw++;
                    }
                }
            }
        }

        int f=0;
        if(category==0)return index;
        else {
            for (int j : index) {
                if (products.get(j).getCategory().equals(Methods.returnCategory(category))) f++;
            }
            int[]index2=new int[f];
            f=0;
            for (int j : index) {
                if (products.get(j).getCategory().equals(Methods.returnCategory(category))) {
                    index2[f] = j;
                    f++;
                }
            }
            return index2;
        }
    }


    public static int[] sortByNewest(int category){

        if (category==0){
            int[]indexs=new int[Information.getProducts().size()];
            for (int i=0;i<Information.getProducts().size();i++){
                indexs[i]=Information.getProducts().size()-i-1;
            }
            return indexs;
        }
        else {
            int count=0;
            for (int i=Information.getProducts().size()-1;i>=0;i--){
                if (Information.getProducts().get(i).getCategory().equals(Methods.returnCategory(category)))count++;
            }
            int[]indexs=new int[count];
            count=0;
            for (int i=Information.getProducts().size()-1;i>=0;i--){
                if (Information.getProducts().get(i).getCategory().equals(Methods.returnCategory(category))){
                    indexs[count]=i;
                    count++;
                }
            }
            return indexs;
        }

    }


    public static int[] searchName(String text){
        int count=0;
        for (int i=0;i<Information.getProducts().size();i++){
            if (Information.getProducts().get(i).getName().contains(text) || Information.getProducts().get(i).getDescription().contains(text))count++;
        }

        int []indexs=new int[count];
        count=0;

        for (int  i=0;i<Information.getProducts().size();i++){

            if (Information.getProducts().get(i).getName().contains(text) || Information.getProducts().get(i).getDescription().contains(text) ){
                indexs[count]=i;
                count++;
            }

        }
        return indexs;
    }



    public static String returnCategory(int category){
        return switch (category) {
            case 1 -> "نوشیدنی";
            case 2 -> "صبحانه";
            case 3 -> "کنسرو و غذای آماده";
            case 4 -> "لبنیات";
            case 5 -> "میوه و سبزیجات";
            case 6 -> "خواروبار";
            case 7 -> "آجیل و خشکبار";
            case 8 -> "محصولات پروتیینی";
            case 9 -> "تنقلات";
            case 10 -> "چاشنی و ادویه جات";
            default -> "";
        };
    }


    public static String codeRandomString(){
        Random random=new Random();
        String s="";
        for (int i=0;i<6;i++){
            int r=random.nextInt(3)+1;
            switch (r) {
                case 1 -> {
                    char c = (char) (random.nextInt(26) + 97);
                    s += String.valueOf(c);
                }
                case 2, 3 -> {
                    int n = random.nextInt(10);
                    s += String.valueOf(n);
                }
            }
        }

       return s;
    }

    public static int numCInWH(String category,String nameWareHouse){
        int count=0;
        for (int i=0;i<Information.getProducts().size();i++){
            if (category.equals(Information.getProducts().get(i).getCategory()) && nameWareHouse.equals(Information.getProducts().get(i).getWareHouse()))count++;
        }
        return count;
    }

}
