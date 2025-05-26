import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class GenerateData {
    private static final String[] palabras = {"League", "Social", "Age", "Legends", "Rivas", "Rocket", "Shadow", "Monster", "Hunter"};
    private static final String[] category = {"Rogelike", "Roguelite", "MOBA", "Tactial Shooter", "RPG", "Acción", "Coop", "JcE"};
    private static final Random random = new Random();

    public static ArrayList<Game> generador(int n) {
        ArrayList<Game> games = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String nombre = palabras[random.nextInt(palabras.length)] + palabras[random.nextInt(palabras.length)];
            String categoria = category[random.nextInt(category.length)];
            int price = random.nextInt(70001);
            int quality = random.nextInt(101);

            games.add(new Game(nombre, categoria, price, quality));
        }
        return games;
    }

    public static void CSV(ArrayList<Game> games, String file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Game juego : games) {
                writer.printf("%s,%s,%d,%d%n", juego.getName(), juego.getCategory(), juego.getPrice(), juego.getQuality());
            }

        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static void crearCSV(){
        int[] tamanos = {100, 10000, 1000000};

        File carpeta = new File("data");
        if (!carpeta.exists()) {
            boolean creada = carpeta.mkdirs();
            if (creada) {
                System.out.println("Carpeta 'data' creada.");
            } else {
                System.out.println("No se pudo crear la carpeta 'data'.");
                return;
            }
        }
        for (int tamano : tamanos) {
            ArrayList<Game> juegos = generador(tamano);
            String nombreArchivo = "data/games_" + tamano + ".csv";
            CSV(juegos, nombreArchivo);
            System.out.println("Archivo creado: " + nombreArchivo);
        }
    }

    public static void main(String[] args) {
        int [] tamanos = {100, 10000, 100000};
        for(int tamano : tamanos){
            ArrayList<Game> games = generador(tamano);
            CSV(games, "games_" + tamano + ".csv");
            System.out.println("Archivo generado: games_ + " + tamano + ".csv");
        }
    }
}

