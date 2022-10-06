import java.util.Scanner;

public class Matriks {
    int BrsMax = 10;  // jml baris maksimal matriks
    int KolMax = 10;  // jml kolom maksimal matriks
    float[][] isi = new float[BrsMax+1][KolMax+1];
    int BrsEff = 0;   // jml baris yg diisi elemen matriks
    int KolEff = 0;   // jml kolom yg diisi elemen matriks
    int FirsIdxBrs = 0;
    int FirstIdxKol = 0;
    int LastIdxBrs;
    int LastIdxKol;
    int NbElmt;

    //Membuat matriks dengan ukuran nb x nk
    public static void MakeEmpty(Matriks M, int nb, int nk) {
        int i,j;
        M.BrsEff = nb;
        M.KolEff = nk;
        M.NbElmt = M.BrsEff * M.KolEff;
        M.LastIdxBrs = M.BrsEff - 1 + M.FirsIdxBrs;
        M.LastIdxKol = M.KolEff -1 + M.FirstIdxKol;
        for (i=M.FirsIdxBrs;i<=M.LastIdxBrs;i++){
            for (j=M.FirstIdxKol;j<=M.LastIdxKol;j++){
                M.isi[i][j] = 0; // seluruh isi matriks di-set menjadi 0.
            }
        }        
    }

    public static void BacaKeyboard(Matriks M){
        /**KAMUS LOKAL */
        Scanner baca = new Scanner(System.in);
        float elemen;
        int nb, nk,i,j;

        /**ALGORITMA */
        System.out.print("\n  >> Masukkan jumlah baris: ");
        nb = baca.nextInt();
        System.out.print("  >> Masukkan jumlah kolom: ");
        nk = baca.nextInt();
        Matriks.MakeEmpty(M, nb, nk);
        System.out.println("  \n!! Sekarang, silakan masukkan seluruh elemen matriks !!");

        for (i=M.FirsIdxBrs;i<=M.LastIdxBrs;i++){
            for (j=M.FirstIdxKol; j<=M.LastIdxKol;j++){
                elemen = baca.nextFloat();
                M.isi[i][j] = elemen;
            }
        }
    }

    public static void TulisLayar (Matriks M){
        /**KAMUS LOKAL */
        int i, j;

        /**ALGORITMA */
        for (i=M.FirsIdxBrs;i<=M.LastIdxBrs;i++){
            for (j=M.FirstIdxKol; j<=M.LastIdxKol;j++){
                System.out.printf("%7.2f", M.isi[i][j]);
            }
            System.out.println("");
        }
    }


    public static void CopyMatriks(Matriks M1, Matriks M2){ //Menyalin elemen M1 ke M2
        /**KAMUS LOKAL */
        int i, j;

        /**Algoritma */
        M2.BrsEff = M1.BrsEff;
        M2.KolEff = M1.KolEff;
        M2.NbElmt = M2.BrsEff * M2.KolEff;
        M2.LastIdxBrs = M2.BrsEff - 1 + M2.FirsIdxBrs;
        M2.LastIdxKol = M2.KolEff -1 + M2.FirstIdxKol;
        
        for (i=M2.FirsIdxBrs;i<=M2.LastIdxBrs;i++){
            for (j=M2.FirstIdxKol;j<=M2.LastIdxKol;j++){
                M2.isi[i][j] = M1.isi[i][j];
            }
        }
    }


    public static boolean IsSegitigaAtas(Matriks M){ //Mengecek apakah Matriks M matriks segitiga atas
        /**KAMUS LOKAL */
        boolean SegiTigaAtas;
        int i, j;
        /**ALGORITMA */
        if (M.BrsEff==M.KolEff){
            SegiTigaAtas = true;
            i = M.FirsIdxBrs + 1;
            while (SegiTigaAtas && i<=M.LastIdxBrs){
                j = M.FirstIdxKol;
                while (SegiTigaAtas && j<i){
                    if(M.isi[i][j] != 0){
                        SegiTigaAtas = false;
                    }
                    else{
                        j++;
                    }
                }
                if(SegiTigaAtas){
                    i++;
                }
            }
        }
        else{
            SegiTigaAtas = false;
        }
        return SegiTigaAtas;
    }

    public static boolean IsSegitigaBawah(Matriks M){ //Mengecek apakah Matriks M matriks segitiga bawah
        /**KAMUS LOKAL */
        boolean SegiTigaBawah;
        int i, j;
        /**ALGORITMA */
        if (M.BrsEff==M.KolEff){
            SegiTigaBawah = true;
            i = M.LastIdxBrs - 1;
            while (SegiTigaBawah && i>=M.FirsIdxBrs){
                j = M.LastIdxKol;
                while (SegiTigaBawah && j>i){
                    if(M.isi[i][j] != 0){
                        SegiTigaBawah = false;
                    }
                    else{
                        j--;
                    }
                }
                if(SegiTigaBawah){
                    i--;
                }
            }
        }
        else{
            SegiTigaBawah = false;
        }
        return SegiTigaBawah;
    }

    public static void MakeEselon(Matriks M){ //Mengubah matriks M menjadi matriks eselon dengan OBE
        /**KAMUS LOKAL */
        int k, i, n, max;

        /**ALGORITMA */
        System.out.print("\n");
            if (M.BrsEff<M.KolEff-1){
                M.BrsEff = M.KolEff -1;
                M.LastIdxBrs = M.BrsEff -1;
            }
            n = M.KolEff-1;
            for (k=0;k<n;k++){
                max = k;
                for (i=k+1;i<=M.LastIdxBrs;i++){
                    if (Math.abs(M.isi[i][k])>(Math.abs(M.isi[max][k]))){
                        max = i;  // tukar baris
                    }
                }
                System.out.print("\n"); 
                Matriks.TukarBrs(M, max, k);

                if(M.isi[k][k] != 0){
                    KaliBrs(M, k, (1/lead(M, k)));
                }
                System.out.print("\n"); 
                for (i=k+1;i<=M.LastIdxBrs;i++){
                    TambahBrs(M, i, k, -1*lead(M, i));
                    M.isi[i][k]=0f;
                }               
            }
    }

    public static float lead(Matriks M, int i){
        int j = M.FirstIdxKol;
        float lead = 0f;
        while (lead==0 && j<=M.LastIdxKol){
            if (M.isi[i][j]!=0){
                lead = M.isi[i][j];
            }
            else{
                j++;
            }
        }
        return lead; 
    }

    public static void MakeReducedEselon(Matriks M){
        /**KAMUS LOKAL */
        int k, i, n, max;
        
        /**ALGORITMA */
        System.out.print("\n");
            if (M.BrsEff<M.KolEff-1){
            M.BrsEff = M.KolEff -1;
            M.LastIdxBrs = M.BrsEff -1;
            }
            n = M.KolEff-1;
            for (k=0;k<n;k++){
                max = k;
                for (i=k+1;i<=M.LastIdxBrs;i++){
                    if (Math.abs(M.isi[i][k])>(Math.abs(M.isi[max][k]))){
                        max = i;
                    }
                }
                System.out.print("\n"); 
                Matriks.TukarBrs(M, max, k);
                if(M.isi[k][k] != 0){
                    KaliBrs(M, k, (1/lead(M, k)));
                }
                System.out.print("\n"); 
                for (i=0;i<=M.LastIdxBrs;i++){
                    if (i!=k){
                    TambahBrs(M, i, k, -1*lead(M, i));
                    M.isi[i][k]=0f;
                    }
                }               
            }
    }        

    public static void KaliMatriks(Matriks M1, Matriks M2, Matriks MHsl){
        /*KAMUS LOKAL*/
        int i, j, k;
        float elemen;
        
        /*ALGORITMA*/
        Matriks.MakeEmpty(MHsl, M1.BrsEff, M2.KolEff);
        for (i=MHsl.FirsIdxBrs;i<=MHsl.LastIdxBrs;i++){
            for(j=MHsl.FirstIdxKol;j<=MHsl.LastIdxKol;j++){
                elemen = 0;
                for(k=M1.FirstIdxKol;k<=M2.LastIdxBrs;k++){
                    elemen += M1.isi[i][k] * M2.isi[k][j];
                }
                MHsl.isi[i][j] = elemen;
            }
        }
    }

    public static boolean AllZero(Matriks M, int i){
        int j;
        boolean allzero = true;
        
        j = M.FirstIdxKol;
        while (allzero && j<=M.LastIdxKol){
            if (M.isi[i][j] != 0){
                allzero = false;
            }
            else{
                j++;
            }
        }
        return allzero;
    }

    /*OBE*/
    public static void transposeMatriks(Matriks M){
        /**KAMUS LOKAL */
        int i, j;
        float temp;
        /**ALGORITMA */
        for (i=M.FirsIdxBrs; i<=M.LastIdxBrs; i++){
            for (j=M.FirstIdxKol; j<i; j++){
                temp = M.isi[i][j];
                M.isi[i][j] = M.isi[j][i];
                M.isi[j][i] = temp;
            }
        }
    }
    
    public static void TukarBrs(Matriks M, int r1, int r2){ //Menukar elemen baris r1 dan r2
        /**KAMUS LOKAL */
        int j;
        float temp;
        /**ALGORITMA */
        for (j=M.FirstIdxKol;j<=M.LastIdxKol;j++){
            temp = M.isi[r1][j];
            M.isi[r1][j] = M.isi[r2][j];
            M.isi[r2][j] = temp;
        }
    }

    public static void KaliBrs(Matriks M, int i, float x){ //Mengalikan baris i dengan x
        /**KAMUS LOKAL */
        int j;
        /**ALGORITMA */
         for(j=M.FirstIdxKol;j<=M.LastIdxKol;j++){
            M.isi[i][j] = M.isi[i][j] * x;
         }

    }

    public static void TambahBrs(Matriks M, int r1, int r2, float x){   //Menambahkan tiap elemen r1 dengan x kali elemen r2
        /**KAMUS LOKAL */
        int j;
        
        /**ALGORITMA */
        for (j=M.FirstIdxKol; j<=M.LastIdxKol ; j++){
            M.isi[r1][j] += M.isi[r2][j]*x;
        }
    }

    //Asumsi Matriks berukuran nxn atau NeffBrs == NeffKol
    public static float DeterminanKofaktor(Matriks M){
        /**KAMUS LOKAL */
        int i,j,k;
        int sign;
        Matriks Mnxt = new Matriks();
        float det;
        int i2, j2;

        /**ALGORITMA */
        if(M.NbElmt == 4){  // jika matriks berukuran 2 x 2
            return ((M.isi[0][0] * M.isi[1][1]) - (M.isi[0][1] * M.isi[1][0]));
        } else if(M.NbElmt == 1){
            return M.isi[0][0];  // jika matriks hanya memiliki 1 elemen (1 x 1)
        } else{
            sign = 1;  // untuk tanda positif atau negatif kolom matriks
            det = 0;
            // iterasi untuk menghitung n kali matriks kofaktor, dimana n adalah nomor kolom matriks persegi dari parameter
            for (i=M.FirsIdxBrs ; i<=M.LastIdxBrs; i++){
                Matriks.MakeEmpty(Mnxt, M.BrsEff-1, M.KolEff-1);
                i2 = -1;
                for (j=M.FirsIdxBrs; j<=M.LastIdxBrs; j++){
                    i2++;
                    if (j==0){
                        j++;
                    }
                    j2 = -1;
                    for (k=M.FirstIdxKol; k<=M.LastIdxKol; k++){
                        j2++;
                        if (k == i){ // untuk mengeliminasi kolom ketika menghitung kofaktor
                            k++;
                        }
                        Mnxt.isi[i2][j2] = M.isi[j][k];
                    }
                }
                det += M.isi[0][i] * Matriks.DeterminanKofaktor(Mnxt) * sign;
                sign *= -1;
            }
        }
        return det;
    }

    public static Matriks buangBrKolMatriks(Matriks M, int a, int b){
        /**KAMUS LOKAL */
        int j,k;
        Matriks Mnxt = new Matriks();
        int i2, j2;

        /**ALGORITMA */
        MakeEmpty(Mnxt, M.BrsEff-1, M.KolEff-1);
        i2 = -1;
        for (j=M.FirsIdxBrs; j<=M.LastIdxBrs; j++){
            i2++;
            if (j==a){
                j++;
            }
            j2 = -1;
            for (k=M.FirstIdxKol; k<=M.LastIdxKol; k++){
                j2++;
                if (k == b){
                    k++;
                }
                Mnxt.isi[i2][j2] = M.isi[j][k];
            }
        }
        return Mnxt;
    }

    //Asumsi Matriks berukuran nxn atau NeffBrs == NeffKol
    public static Matriks matriksKofaktor(Matriks M){
        /**KAMUS LOKAL */
        int i, j;
        Matriks Mkof = new Matriks();
        int sign = 1;
        /**ALGORITMA */
        Matriks.MakeEmpty(Mkof, M.BrsEff, M.KolEff);
        for (i=M.FirsIdxBrs; i<=M.LastIdxBrs; i++){
            for (j=M.FirstIdxKol; j<=M.LastIdxKol; j++){
                Mkof.isi[i][j] = Matriks.DeterminanKofaktor(Matriks.buangBrKolMatriks(M, i, j)) * sign;
                if (Matriks.DeterminanKofaktor(Matriks.buangBrKolMatriks(M, i, j))==0 && sign ==-1){
                    Mkof.isi[i][j] = 0;
                }
                sign *= -1;
                if (M.KolEff % 2 == 0 && j == M.LastIdxKol){
                    sign *= -1;
                }
            }
        }
        return Mkof;
    }

    //Asumsi Matriks berukuran nxn atau NeffBrs == NeffKol
    public static Matriks inverseMatriks(Matriks M){
        /**KAMUS LOKAL */
        int i, j;
        float determinan;
        /**ALGORITMA */
        Matriks matriksInverse = new Matriks();
        Matriks matriksAdj = new Matriks();
        Matriks.MakeEmpty(matriksInverse, M.BrsEff, M.KolEff);
        matriksAdj = Matriks.matriksKofaktor(M);
        Matriks.transposeMatriks(matriksAdj);
        determinan = Matriks.DeterminanKofaktor(M);
        for (i=M.FirsIdxBrs; i<=M.LastIdxBrs; i++){
            for (j=M.FirstIdxKol; j<=M.LastIdxKol; j++){
                matriksInverse.isi[i][j] = matriksAdj.isi[i][j] / determinan ;
            }
        }
        return matriksInverse;
    }

    public static float DeterminanReduksiBaris(Matriks M){
        /**KAMUS LOKAL */
        float det, bagi;
        int i, j, k, l, p, q;
        int idxfb0, tuker, tanda;
        boolean bukan0;
        /**ALGORITMA */
        if (Matriks.IsSegitigaAtas(M) || Matriks.IsSegitigaBawah(M)){
            det = M.isi[0][0];
            for (i=M.FirstIdxKol+1; i<=M.LastIdxBrs; i++){
                det *= M.isi[i][i];
            }
            return det;
        } else{
            j = 0;
            tuker = 0;
            while (j<=M.LastIdxKol){
                k = j;
                bukan0 = false;
                while(k<=M.LastIdxBrs && bukan0==false){
                    if (M.isi[k][j] != 0){
                        bukan0 = true;
                    } else{
                        k++;
                    }
                }
                if (bukan0 == false){
                    det = 0;
                    return det;
                } else{
                    idxfb0 = k;
                    if (idxfb0 > j){
                        Matriks.TukarBrs(M, idxfb0, j);
                        tuker ++;
                    }
                    l = j+1;
                    while (l<=M.LastIdxKol){
                        if (M.isi[l][j] != 0){
                            bagi = (M.isi[l][j] / M.isi[j][j]) * -1;
                            Matriks.TambahBrs(M, l, j, bagi);
                        }
                        l++;
                    }
                }
                j++;
            }
            det = M.isi[0][0];
            q = 0;
            tanda = -1;
            while (q <= tuker){
                tanda *= -1;
                q++;
            }
            for (p = 1; p<=M.LastIdxKol; p++){
                det *= M.isi[p][p];
            }
            return det*tanda;
        }
    }

    public static void MenuDeterminan(){
        int metodeInput;
        Matriks M = new Matriks();
        
        Matriks.BacaKeyboard(M);
        
        do {
            System.out.println("\n+================================================+");
            System.out.println("+----- Pilih Metode Penyelesaian Determinan -----+");
            System.out.println("|                                                |");
            System.out.println("|   1. Metode Ekspansi Kofaktor                  |");
            System.out.println("|   2. Metode Reduksi Baris                      |");
            System.out.println("|                                                |");
            System.out.println("+------------------------------------------------+");

            System.out.print("  >> Silahkan pilih metode: ");
            metodeInput = Menu.baca.nextInt();
            System.out.print("\n");
        } while (metodeInput < 1 && metodeInput > 2);
        
        if (metodeInput == 1) {
            float determinan;
            if (M.BrsEff == M.KolEff) {
                determinan = Matriks.DeterminanKofaktor(M);
                System.out.printf("  >> Determinan matriks tersebut dengan menggunakan metode ekspansi kofaktor adalah %.2f\n",determinan);
                System.out.print("\n");
            } else {
                System.out.println("  !! Maaf tidak dapat menentukan determinan matriks dengan ukuran baris dan kolom yang berbeda !!");
                System.out.print("\n");
            }
        } else {
            float determinan;
            if (M.BrsEff == M.KolEff) {
                if (M.NbElmt == 1) {
                    determinan = M.isi[0][0];
                    System.out.printf("  >> Determinan matriks tersebut dengan menggunakan metode reduksi baris adalah %.2f\n",determinan);
                    System.out.print("\n");
                } else {
                    Matriks Mbaru = new Matriks();
                    Matriks.CopyMatriks(M, Mbaru);
                    determinan = Matriks.DeterminanReduksiBaris(M);
                    System.out.println("  >> Setelah dilakukan reduksi baris pada matriks, maka matriks tersebut menjadi");
                    Matriks.TulisLayar(M);
                    System.out.print("\n");
                    System.out.printf("\n  >> Determinan matriks tersebut dengan menggunakan metode reduksi baris adalah %.2f\n",determinan);
                    System.out.print("\n");
                }
            } else {
                System.out.println("  !! Maaf tidak dapat menentukan determinan matriks dengan ukuran baris dan kolom yang berbeda");
                System.out.print("\n");
            }
        }
    }

    public static void MenuInverse(){
        Matriks M = new Matriks();
        
        Matriks.BacaKeyboard(M);
        
        if (M.BrsEff == M.KolEff){
            float determinan;
            if(M.NbElmt == 1){
                if (M.isi[0][0] != 0){
                    Matriks Mcopy = new Matriks();
                    Matriks.CopyMatriks(M, Mcopy);
                    M.isi[0][0] = 1 / M.isi[0][0];
                    System.out.printf("  >> Maka invers dari matriks tersebut adalah\n");
                    TulisLayar(M);
                    System.out.print("\n");
                } else{
                    System.out.println("  !! Matriks tersebut tidak memiliki invers");
                    System.out.print("\n");
                }
            } else if (M.NbElmt == 4){
                if (Matriks.DeterminanKofaktor(M) != 0){
                    Matriks Mcopy = new Matriks();
                    Matriks.CopyMatriks(M, Mcopy);
                    determinan = Matriks.DeterminanKofaktor(M);
                    float temp = M.isi[0][0];
                    M.isi[0][0] = M.isi[1][1];
                    M.isi[1][1] = temp;
                    M.isi[1][0] *= -1;
                    M.isi[0][1] *= -1;
                    for (int i = M.FirsIdxBrs; i<=M.LastIdxBrs;i++){
                        for (int j = M.FirstIdxKol; j<=M.LastIdxKol; j++){
                            M.isi[i][j] *= 1 / determinan;
                        }
                    }
                    System.out.printf("  >> Maka invers dari matriks tersebut adalah\n");
                    TulisLayar(M);
                    System.out.print("\n");
                } else{
                    System.out.println("  !! Matriks tersebut tidak memiliki invers");
                    System.out.print("\n");
                }
            } else{
                Matriks invers = new Matriks();
                if (Matriks.DeterminanKofaktor(M) != 0){
                    invers = Matriks.inverseMatriks(M);
                    System.out.printf("  >> Maka invers dari matriks tersebut adalah\n");
                    TulisLayar(invers);
                    System.out.print("\n");
                    
                } else{
                    System.out.println("  !! Matriks tersebut tidak memiliki invers");
                    System.out.print("\n");
                    
                }
            }
        } else{
            System.out.println("  !! Matriks tersebut tidak memiliki invers");
            System.out.print("\n");
        }
    
    }

    public static Matriks kalikanDuaMatriks (Matriks M1, Matriks M2) {
        Matriks matriksHsl = new Matriks();
        Matriks.MakeEmpty(matriksHsl, M1.KolEff, M2.BrsEff);

        int i, j, k;
        float sum = 0;
        for (i = 0; i < M1.BrsEff; i++) {
            for (j = 0; j < M2.KolEff; j++) {
                for (k = 0; k < M2.BrsEff; k++) {
                    sum += M1.isi[i][k] * M2.isi[k][j];
                }
                matriksHsl.isi[i][j] = sum;
                sum = 0;
            }
        }
        
        return matriksHsl;
    }

    public static void MenuPerkalianMatriks() {
        System.out.println("\n  !! Untuk melakukan perkalian dua matriks, pastikan bahwa");
        System.out.println("tiap matriks memiliki ordo sama (jml elemen sama) !!");
        Matriks m1 = new Matriks();

        System.out.println("\nUntuk matriks pertama,");
        Matriks.BacaKeyboard(m1);

        System.out.println("\nUntuk matriks kedua,");
        Matriks m2 = new Matriks();
        Matriks.BacaKeyboard(m2);

        if (m1.KolEff == m2.BrsEff) {
            Matriks hasil = new Matriks();
            Matriks.MakeEmpty(hasil, 2, 2);
            hasil = kalikanDuaMatriks(m1, m2);

            System.out.println("\nHasil perkalian dua matriks tersebut adalah sbb. :");
            System.out.println("");
            TulisLayar(hasil); System.out.println("");
        } else {
            System.out.println("\n  !! Maaf, perkalian matriks tidak dapat dilakukan");
            System.out.println("Jumlah kolom matriks pertama harus sama dengan jumlah baris matriks kedua");
        }
    }

    public static void MatrixAddition() {
    	int i, j;
    	
    	Matriks M1 = new Matriks();
    	Matriks M2 = new Matriks();
    	Matriks M3 = new Matriks();
    	
    	System.out.print("\n  >> Masukkan Matriks Pertama ");
    	Matriks.BacaKeyboard(M1);
    	
    	System.out.print("\n  >> Masukkan Matriks Kedua ");
    	Matriks.BacaKeyboard(M2);
    	
    	MakeEmpty(M3, M1.BrsEff, M1.KolEff);
    	
    	if((M1.BrsEff == M2.BrsEff) && (M1.KolEff == M2.KolEff)) {
    		for(i=0; i<M1.BrsEff; i++) {
    			for(j=0; j<M1.KolEff; j++) {
    				M3.isi[i][j] = M1.isi[i][j] + M2.isi[i][j];
    			}
    		}
    		System.out.print("\n  >> Hasil Penjumlahan Matriks \n");
    		TulisLayar(M3);
    	}else {
    		System.out.println("  !! Penjumlahan Tidak Dapat Dilakukan");
    		System.out.println("  !! Penjumlahan Matriks Harus Memiliki Ordo Yang Sama");
            System.out.print("\n");
    	}
    	
    }
    
    public static void MatrixSubstraction() {
    	int i, j;
    	
    	Matriks M1 = new Matriks();
    	Matriks M2 = new Matriks();
    	Matriks M3 = new Matriks();
    	
    	System.out.print("\n  >> Masukkan Matriks Pertama ");
    	Matriks.BacaKeyboard(M1);
    	
    	System.out.print("\n  >> Masukkan Matriks Kedua ");
    	Matriks.BacaKeyboard(M2);
    	
    	MakeEmpty(M3, M1.BrsEff, M1.KolEff);
    	
    	if((M1.BrsEff == M2.BrsEff) && (M1.KolEff == M2.KolEff)) {
    		for(i=0; i<M1.BrsEff; i++) {
    			for(j=0; j<M1.KolEff; j++) {
    				M3.isi[i][j] = M1.isi[i][j] - M2.isi[i][j];
    			}
    		}
    		System.out.print("\n  >> Hasil Pengurangan Matriks \n");
    		TulisLayar(M3);
    	}else {
    		System.out.println("  !! Pengurangan Tidak Dapat Dilakukan");
    		System.out.println("  !! Pengurangan Matriks Harus Memiliki Ordo Yang Sama");
            System.out.print("\n");
    	}
    }
}
