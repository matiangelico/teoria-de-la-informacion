package modelo;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        double[][] matrizProbabilidades;
        double[] vectorEstacionario;
        String fileName, datos;
        fileName = "DatosTP1.txt";
        File url = new File(fileName);
        BufferedReader archivo = new BufferedReader(new FileReader(url));
        datos = archivo.readLine();
        Model.generaHashSimbolos(datos);
        matrizProbabilidades = Model.generarMatrizProbabilidades(datos);

        double[][] m = new double[4][4];
        m[0][0] = 0.8;m[0][1] = 0.08;m[0][2] = 0.;m[0][3] = 0.09;
        m[1][0] = 0.18;m[1][1] = 0.8;m[1][2] = 0.0243;m[1][3] = 0.03;
        m[2][0] = 0.;m[2][1] = 0.07;m[2][2] = 0.889;m[2][3] = 0.08;
        m[3][0] = 0.02;m[3][1] = 0.05;m[3][2] = 0.0867;m[3][3] = 0.8;

        System.out.println("Matriz de Probabilidades");
        Model.mostrarMatriz(matrizProbabilidades);
        System.out.println("\nMemoria No Nula :" + Model.memoriaNoNula(matrizProbabilidades));
        System.out.format("Ergodica : %b \n",Model.ergodica(matrizProbabilidades));
        System.out.print("Vector Estacionario : ");
        vectorEstacionario = Model.calcularVector(matrizProbabilidades);
        Model.mostrarVector(vectorEstacionario);
        System.out.print("\nEntropia : "+Model.calcularEntropia(matrizProbabilidades,vectorEstacionario));
    }
}




