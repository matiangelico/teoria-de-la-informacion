package modelo;

public class Model {
    public static int n;

    public static void setN(int n) {
        Model.n = n;
    }

    public static int getN() {
        return n;
    }

    public static double[][] generarMatrizProbabilidades(String datos) {
        double[][] prob = new double[n][n];
        int i, j, pos;
        int[] totales = new int[n];
        for (i = 1; i < datos.length(); i++) {
            pos = caracterAindice(datos.charAt(i));
            prob[pos][caracterAindice(datos.charAt(i - 1))] += 1;
            totales[pos] +=1;
        }
        for (i = 0; i < 3; i++)
            for (j = 0; j < 3; j++)
                prob[i][j] /= totales[i];
        return prob;
    }

    public static int caracterAindice(char a) {
        int indice = -1;
        switch (a) {
            case ('A'):
                indice = 0;
                break;
            case ('B'):
                indice = 1;
                break;
            case ('C'):
                indice = 2;
                break;
        }
        return indice;
    }

    public static double[][] generarMatrizIdentidad() {
        double[][] matrizI = new double[n][n];
        int f, c;
        for (f = 0; f < n; f++)
            for (c = 0; c < n; c++) {
                if (f == c)
                    matrizI[f][c] = 1.;
                else
                    matrizI[f][c] = 0.;
            }
        return matrizI;
    }

    public static void mostrarMatriz(double[][] matriz) {
        int f, c;
        for (f = 0; f < n; f++) {
            for (c = 0; c < n; c++) {
                System.out.format(" %f  ", matriz[f][c]);
            }
            System.out.format("\n");
        }
    }

    public static boolean ergodica(double[][] matriz) {
        int f, c = 0;
        double suma;
        boolean ergodica = true;
        while (ergodica && c < n) {
            suma = 0;
            for (f = 0; f < n; f++) {
                if (f != c)
                    suma += matriz[f][c];
            }
            if (suma == 0)
                ergodica = false;
            c += 1;
        }
        return ergodica;
    }

    public static int cantidadSimbolos(String datos) {return 3;}

    public static double[][] restaMatrices(double[][] matrizProbabilidades) {
        int i,j;
        double[][] restaMatrices = new double[n][n];
        double[][] matrizIdentidad = generarMatrizIdentidad();
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                restaMatrices[i][j] = matrizProbabilidades[i][j] - matrizIdentidad [i][j];
        return restaMatrices;
    }

    public static double[] calcularVector (double[][] matriz){
        double[] vectorEstacionario;
        double[][] restaMatrices = restaMatrices(matriz);
        double[][] matrizAmpliada = creaMatrizAmpliada(restaMatrices);
        matrizAmpliada = triangulacionGauss(matrizAmpliada);
        matrizAmpliada = imponerCondicion (matrizAmpliada);
        matrizAmpliada = triangulacionGauss(matrizAmpliada);
        vectorEstacionario = sustitucionRegresiva(matrizAmpliada);
        return vectorEstacionario;
    }

    public static double[][] imponerCondicion (double[][]matriz){
        int t;
        for (t = 0; t<= n; t++){
            matriz[n-1][t] = 1.;
        }
        return matriz;
    }

    public static boolean memoriaNoNula (double[][] matriz){
        boolean filaIguales = false;
        int f = 0,c = 0;
        while (f<n && !filaIguales){
            while (!filaIguales && c<n-1){
                if (matriz[f][c] != matriz[f][c+1])
                    filaIguales = true;
                c+=1;
            }
            f+=1;
        }
        return filaIguales;
    }
    public static double[][] triangulacionGauss(double[][] matrizAmpliada) {
        int i, j, t, r;
        for (i = 0; i < n; i++) {
            for (t = i + 1; t < n + 1 ; t++)
                matrizAmpliada[i][t] /= matrizAmpliada[i][i];
            matrizAmpliada[i][i] = 1.;
            for (j = i+1; j < n; j++) {
                for (r = i + 1; r < n + 1; r++)
                    matrizAmpliada[j][r] = matrizAmpliada[j][r] - matrizAmpliada[i][r]*matrizAmpliada[j][i];
                matrizAmpliada[j][i] = 0.;
            }
        }
        return matrizAmpliada;
    }

    private static double[][] creaMatrizAmpliada(double[][] matriz){
        int f,c;
        double[][] mAmpliada = new double[n][n+1];
        for (f = 0; f < n; f++)
            for (c = 0; c < n; c++)
                mAmpliada[f][c] = matriz[f][c];
        for (f = 0; f < n; f++)
            mAmpliada[f][n]=0.;
        return mAmpliada;
    }

    private static double[] sustitucionRegresiva(double[][] matrizAmpliada){
        double suma;
        int f,c;
        double[] vectorEstacionario = new double[n];
        vectorEstacionario[n-1] = matrizAmpliada[n-1][n] / matrizAmpliada[n-1][n-1];
        for (f = n-2; f >= 0; f--) {
            suma = 0.;
            for (c = f+1; c < n; c++)
                suma += matrizAmpliada[f][c] * vectorEstacionario[c];
            vectorEstacionario[f]= (matrizAmpliada[f][n]-suma) / matrizAmpliada[f][f];
        }
        return vectorEstacionario;
    }

    public static void mostrarVector(double[] vectorEstacionario) {
        int t;
        for (t = 0; t<n;t++)
            System.out.format("%f  ",vectorEstacionario[t]);
    }
}
