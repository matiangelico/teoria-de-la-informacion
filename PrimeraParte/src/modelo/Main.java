package modelo;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        double[][] matrizProbabilidades;
        double[] vectorEstacionario;
        int n;
        String fileName, datos;
        fileName= "DatosTP1.txt";
        File url = new File (fileName);
        BufferedReader archivo = new BufferedReader(new FileReader(url));
        datos = archivo.readLine();
        n = Model.cantidadSimbolos(datos) ;
        matrizProbabilidades = Model.generarMatrizProbabilidades(datos,n);

        double [][] m = new double[3][3];
        m[0][0]=0.2;m[0][1]=0.2;m[0][2]=0.2;
        m[1][0]=0.4;m[1][1]=0.4;m[1][2]=0.4;
        m[2][0]=0.4;m[2][1]=0.4;m[2][2]=0.4;

        System.out.println("Memoria No Nula " + Model.memoriaNoNula(m,n));
        Model.mostrarMatriz(m,n);//matrizProbabilidades,n);

        //System.out.format("Ergodica : %b \n",Model.ergodica(matrizProbabilidades,n));

        vectorEstacionario = Model.calcularVector(m,n);//matrizProbabilidades,n);

        Model.mostrarVector(vectorEstacionario,n);
    }

    //Matriz de Prueba
        /*
        double [][] matriz = new double[3][3];
        double[][] MI;
        MI = Model.generarMatrizIdentidad(3);
        matriz[0][0] = 7.3;matriz[0][1] = 0.;matriz[0][2] = 0.;
        matriz[1][0] = 0.;matriz[1][1] = 0.;matriz[1][2] = 0.;
        matriz[2][0] = 0.;matriz[2][1] = 0.;matriz[2][2] = 0.;
        */
}

