import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        GenerateData.crearCSV();

        System.out.println("Ingrese cantidad de juegos a generar: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        ArrayList<Game> games = GenerateData.generador(cantidad);
        Dataset dataset = new Dataset(games);

        System.out.print("Ingrese el algoritmo de ordenamiento (bubbleSort, insertionSort, selectionSort, mergeSort, quickSort): ");
        String algoritmo = sc.nextLine();

        System.out.println("Ingrese el atributo que desea buscar (price, category, quality): ");
        String atributo = sc.nextLine();

        System.out.print("Ingrese el precio exacto para buscar juegos: ");
        int precio = sc.nextInt();

        ArrayList<Game> porPrecio = dataset.getGamesByPrice(precio);
        System.out.print("Juegos con precio "+ precio+ ": "+ porPrecio.size());

        System.out.print("Ingrese el precio minimo: ");
        int precioMin = sc.nextInt();
        System.out.print("Ingrese el precio maximo: ");
        int precioMax = sc.nextInt();

        ArrayList<Game> porRango = dataset.getGamesByPriceRange(precioMin, precioMax);
        System.out.println("Juegos entre "+precioMin+" y "+precioMax+": "+porRango.size());

        sc.nextLine();
        System.out.print("Ingrese la categoria: ");
        String categoria = sc.nextLine();

        ArrayList<Game> porCategoria = dataset.getGamesByCategory(categoria);
        System.out.println("Juegos entre "+categoria+": "+porCategoria.size());

        System.out.print("Ingrese al cantidad exacta: ");
        int calidad = sc.nextInt();

        ArrayList<Game> porCalidad = dataset.getGamesByQuality(calidad);
        System.out.println("Juegos entre "+calidad+": "+porCalidad.size());
    }
}
