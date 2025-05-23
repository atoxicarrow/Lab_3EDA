import java.util.ArrayList;

public class Dataset{
    ArrayList<Game>Data;
    String sortedByAttribute;

    public Dataset(ArrayList<Game> Data){
        this.Data = Data;
        this.sortedByAttribute = null;
    }

    //getters
    ArrayList getGamesByPrice(int price){
        ArrayList<Game> aux = new ArrayList<>();

        if("price".equals(sortedByAttribute)){ //.equals compara la igualdad de 2 objetos, ya que "==" compara direcciones de memoria
        int left = 0;
        int right = Data.size() - 1;

        while(left <= right){
            int mid = (left + right) / 2;
            int midPrice = Data.get(mid).getPrice();
        }

        }
    }

    int getGamesByPriceRange(int min, int max){
        return ArrayList<Game> Data;
    }


}
