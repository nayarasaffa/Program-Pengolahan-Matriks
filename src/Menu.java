import java.util.Scanner;

public class Menu{
    static final Scanner baca = new Scanner(System.in);

    /* MAIN PROGRAM DIMULAI DARI SINI */
    public static void main(String[] args) {
        /*KAMUS DATA */
        boolean exit = false;
        Scanner baca = new Scanner(System.in);
        int read = 0;  // menyimpan nilai menu yang dipilih user
        
        /*ALGORITMA */

        while(!exit){
            System.out.println("+==============================================+");
            System.out.println("+------- Menu Kalkulator Aljabar Linear -------+");
            System.out.println("|                                              |");
            System.out.println("|   1. Sistem Persamaan Linear                 |");
            System.out.println("|   2. Determinan                              |");
            System.out.println("|   3. Matriks balikan                         |");
            System.out.println("|   4. Penjumlahan Matriks                     |");
            System.out.println("|   5. Pengurangan Matriks                     |");
            System.out.println("|   6. Perkalian Dua Matriks                   |");
            System.out.println("|                                              |");
            System.out.println("|   7. Keluar                                  |");
            System.out.println("+----------------------------------------------+");

            System.out.print("  >>  Silahkan pilih menu: ");
            read = baca.nextInt();
            System.out.print(" ");

            if(read == 1){
                SPL.SPLMenu();
            }
            else if (read==2){
                Matriks.MenuDeterminan();
            }
            else if (read==3){
                Matriks.MenuInverse();
            }
            else if (read==4){
                Matriks.MatrixAddition();
            }
            else if(read==5){
                Matriks.MatrixSubstraction();
            }
            else if(read==6){
                Matriks.MenuPerkalianMatriks();
            }
            else if (read==7){
                System.out.println("\n  >>> Terimakasih. Sampai Jumpa!  <<< ");
                exit = true;
            }
        }

        baca.close();  // menutup scanner
    }
}