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
        Model.setN(Model.cantidadSimbolos(datos));
        matrizProbabilidades = Model.generarMatrizProbabilidades(datos);

        double[][] m = new double[Model.getN()][Model.getN()];
        m[0][0] = 0.2;m[0][1] = 0.2;m[0][2] = 0.2;
        m[1][0] = 0.4;m[1][1] = 0.4;m[1][2] = 0.4;
        m[2][0] = 0.4;m[2][1] = 0.4;m[2][2] = 0.4;

        System.out.println("Matriz de Probabilidades");
        Model.mostrarMatriz(matrizProbabilidades);
        System.out.println();
        System.out.println("Memoria No Nula :" + Model.memoriaNoNula(m));
        System.out.println();
        System.out.format("Ergodica : %b \n",Model.ergodica(matrizProbabilidades));
        System.out.println();
        System.out.println("Vector Estacionario");
        vectorEstacionario = Model.calcularVector(matrizProbabilidades);

        Model.mostrarVector(vectorEstacionario);
    }
}

