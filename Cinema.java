import java.util.Arrays;
import java.util.Scanner;

public class Cinema {


    public static void menu(String [][]seats,int row,int colum,int purCtr,int income,int total) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit\n");

        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                ShowTheSeats(seats,row,colum,purCtr,income,total);
                break;

            case 2:
                BuyATicket(seats,row,colum,purCtr,income,total);
                break;
            case 3:
                Stat(seats,purCtr,row,colum,income,total);
                break;
            case 0:
                break;
        }

    }


    public static void ShowTheSeats(String [][]seats,int row,int colum,int purCtr,int income,int total) {

        System.out.println("Cinema: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
        menu(seats,row,colum, purCtr,income,total);
    }

    public  static  void BuyATicket(String [][]seats,int row,int colum,int purCtr,int income,int total) {
        Scanner sc = new Scanner(System.in);

        int costOne = 10;
        int costLargeOne = 10;
        int coseLargeTwo = 8;


        if ((row <= 10 && row >= 0) && (colum <= 10 && colum >= 0)) {
            System.out.println("Enter a row number:");
            int rowBuy = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int colBuy = sc.nextInt();
            if (rowBuy < 10 && colBuy < 10 && rowBuy > 0 && colBuy > 0) {
                if (!seats[rowBuy][colBuy].equals("B")) {
                    seats[rowBuy][colBuy] = "B";

                    if (row * colum <= 60) {
                        System.out.println("Ticket price: $" + costOne);
                    } else {
                        if (rowBuy % 2 == 0) {
                            if (rowBuy <= (row - 1) / 2) {
                                System.out.println("Ticket price: $" + costLargeOne);
                                income += costLargeOne;
                            } else {
                                System.out.println("Ticket price: $" + coseLargeTwo);
                                income += coseLargeTwo;
                            }
                        } else {
                            if (rowBuy >= ((row - 1) / 2)) {
                                System.out.println("Ticket price: $" + coseLargeTwo);
                                income += coseLargeTwo;
                            } else {
                                System.out.println("Ticket price: $" + costLargeOne);
                                income += costLargeOne;
                            }
                        }
                    }
                } else {
                    System.out.println("That ticket has already been purchased!\n");
                    BuyATicket(seats, row, colum, purCtr, income, total);
                }

            }
            else {
                System.out.println("Wrong input!");
                BuyATicket(seats,row,colum,purCtr,income,total);
            }
        }else {
            System.out.println("Wrong input!");
            BuyATicket(seats,row,colum,purCtr,income,total);
        }
        ++purCtr;
        menu(seats,row,colum, purCtr,income,total);
    }

    public  static  void Stat(String [][]seats,int purCtr,int row,int colum,int income,int total) {

        double prcPurch = (purCtr*100);
        prcPurch /= (row * colum);

        String stat =  String.format("Number of purchased tickets: %d %n" +
                "Percentage: %.2f%% %n"+
                "Current income: $%d %n" +
                "Total income: $%d %n",purCtr,prcPurch,income,total);

        System.out.println(stat);

        menu(seats,row,colum,purCtr,income,total);
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int row = sc.nextInt()+1;
        System.out.println("Enter the number of seats in each row:");
        int colum = sc.nextInt()+1;


        int ctr = 1;
        int purCtr = 0 ;
        int ctrOne = 1;
        int income = 0;
        int total;
        if (row * colum <= 60) {
            total = (row-1) * (colum-1) * 10;
        } else  {
            total = (((((row-1)/2))  * 10)  + ((((row-1)/2)+1) * 8)) * (colum-1);
        }
        String [][]seats = new String[row][colum];


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum ; j++) {
                seats[i][j] = "S";

                if (i==0 && j!=0) {
                    seats[i][j] = String.valueOf(ctr);
                    ctr++;

                } if (j==0 && i!=0) {
                    seats[i][j] = String.valueOf(ctrOne);
                    ctrOne++;

                } if (i == 0 && j == 0) {
                    seats[i][j] = " ";

                }
            }
        }
        menu(seats,row,colum,purCtr,income,total);

    }
}