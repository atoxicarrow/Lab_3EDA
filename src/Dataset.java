import java.util.ArrayList;
import java.util.Comparator;

public class Dataset{
    ArrayList<Game>Data;
    String sortedByAttribute;

    public Dataset(ArrayList<Game> Data){
        this.Data = Data;
        this.sortedByAttribute = null;
    }

    //getters
    public ArrayList<Game> getGamesByPrice(int price){
        ArrayList<Game> aux = new ArrayList<>();

        if("price".equals(sortedByAttribute)) { //.equals compara la igualdad de 2 objetos, ya que "==" compara direcciones de memoria
            int pos = PriceBinarySort(0, Data.size()-1, price);
            if(pos != -1){
                int i = pos;
                while( i>=0 && Data.get(i).getPrice() == price){
                    aux.add(Data.get(i));
                    i--;
                }
                i = pos +1;
                while( i<Data.size() && Data.get(i).getPrice() == price){
                    aux.add(Data.get(i));
                    i++;
                }
            }
            else{
                for(Game game : Data){
                    if(game.getPrice() == price){
                        aux.add(game);
                    }
                }
            }
        }
        return aux;
    }

    private int PriceBinarySort(int left, int right, int price){
            if (left > right) {
                return -1;
            }

            int mid = (left + right) / 2;
            int midPrice = Data.get(mid).getPrice();

            if (midPrice == price) {
                return mid;
            } else if (midPrice < price) {
                return PriceBinarySort(mid + 1, right, price);
            } else {
                return PriceBinarySort(left, mid - 1, price);
            }
    }

    public ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherPrice){
        ArrayList<Game> aux = new ArrayList<>();

        if("price".equals(sortedByAttribute)){
            int inicio = Primero(lowerPrice, 0, Data.size() - 1);
            if(inicio == -1){
                return aux;
            }
            for(int i=inicio; i<Data.size(); i++){
                int price = Data.get(i).getPrice();
                if(price > higherPrice){
                    break;
                }
                aux.add(Data.get(i));
            }
        } else {
            for(Game game : Data){
                int price = game.getPrice();
                if(price >= lowerPrice && price <= higherPrice){
                    aux.add(game);
                }
            }
        }
        return aux;
    }

    private int Primero(int target, int left, int right){
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midPrice = Data.get(mid).getPrice();

        if (midPrice < target) {
            return Primero(target, mid + 1, right);
        } else {
            int anterior = Primero(target, left, mid -1);
            if(anterior != -1){
                return anterior;
            } else{
                return mid;
            }
        }
    }

    public ArrayList<Game> getGamesByCategory(String category){
        ArrayList<Game> aux = new ArrayList<>();

        if("category".equals(sortedByAttribute)){
            int pos = categoryBinarySearch(category, 0, Data.size()-1);
            if(pos != -1){
                return aux;
            }

            int i = pos;
            while(i >= 0 && Data.get(i).getCategory().equals(category)){
                i--;
            }
            i++;

            while( i<Data.size() && Data.get(i).getCategory().equals(category)){
                aux.add(Data.get(i));
                i++;
            }

        }else{
            for(Game game : Data){
                if(game.getCategory().equals(category)){
                    aux.add(game);
                }
            }
        }
        return aux;
    }

    private int categoryBinarySearch(String target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        String midCategory = Data.get(mid).getCategory();
        int pos = midCategory.compareTo(target);
        if (pos < 0){
            return categoryBinarySearch(target, mid + 1, right);
        }else if (pos > 0){
            return categoryBinarySearch(target, left, mid - 1);
        }else{
            return mid;
        }
    }

    public ArrayList<Game> getGamesByQuality(int quality){
        ArrayList<Game> aux = new ArrayList<>();
        if("quality".equals(sortedByAttribute)){
            int pos = qualityBinarySearch(quality, 0, Data.size()-1);
            if(pos != -1){
                return aux;
            }
            int i = pos;
            while(i >= 0 && Data.get(i).getQuality() == quality){
                aux.add(Data.get(i));
                i++;
            }
        } else{
            for(Game game : Data){
                if(game.getQuality() == quality){
                    aux.add(game);
                }
            }
        }
        return aux;
    }

    private int qualityBinarySearch(int target, int left, int right) {
        if(left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midQuality = Data.get(mid).getQuality();

        if(midQuality < target){
            return qualityBinarySearch(target, mid + 1, right);
        }else if(midQuality > target){
            return qualityBinarySearch(target, left, mid - 1);
        }else{
            return mid;
        }
    }

    public void sortByAlgorith(String algorithm, String attribute){
        Comparator<Game> comparator;

        switch(attribute){
            case "quality":
                comparator = Comparator.comparingInt(Game::getQuality);
                break;
            case "price":
                comparator = Comparator.comparingInt(Game::getPrice);
                break;
            case "category":
                comparator = Comparator.comparing(Game::getCategory);
                break;
            default:
                comparator = Comparator.comparingInt(Game::getPrice);
                attribute = "price";
                break;
        }

        switch(algorithm){
            case "bubbleSort":
                bubbleSort(comparator);
                break;
            case "insertionSort":
                insertionSort(comparator);
                break;
            case "selectionSort":
                selectionSort(comparator);
                break;
            case "mergeSort":
                Data = mergeSort(Data, comparator);
                break;
            case "quickSort":
                quickSort(0, Data.size()-1, comparator);
                break;
            default:
                Data.sort(comparator);
                break;
        }
        sortedByAttribute = attribute;
    }
    private void bubbleSort(Comparator<Game> comp) {
        for (int i = 0; i < Data.size(); i++) {
            for (int j = 0; j < Data.size() - i - 1; j++) {
                if (comp.compare(Data.get(j), Data.get(j + 1)) > 0) {
                    Game temp = Data.get(j);
                    Data.set(j, Data.get(j + 1));
                    Data.set(j + 1, temp);
                }
            }
        }
    }

    private void insertionSort(Comparator<Game> comp) {
        for (int i = 1; i < Data.size(); i++) {
            Game key = Data.get(i);
            int j = i - 1;

            while (j >= 0 && comp.compare(Data.get(j), key) > 0) {
                Data.set(j + 1, Data.get(j));
                j--;
            }
            Data.set(j + 1, key);
        }
    }


    private void selectionSort(Comparator<Game> comp) {
        for (int i = 0; i < Data.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < Data.size(); j++) {
                if (comp.compare(Data.get(j), Data.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Game temp = Data.get(i);
                Data.set(i, Data.get(minIndex));
                Data.set(minIndex, temp);
            }
        }
    }

    private ArrayList<Game> mergeSort(ArrayList<Game> list, Comparator<Game> comp) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;
        ArrayList<Game> left = mergeSort(new ArrayList<>(list.subList(0, mid)), comp);
        ArrayList<Game> right = mergeSort(new ArrayList<>(list.subList(mid, list.size())), comp);

        return merge(left, right, comp);
    }

    private ArrayList<Game> merge(ArrayList<Game> left, ArrayList<Game> right, Comparator<Game> comp) {
        ArrayList<Game> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (comp.compare(left.get(i), right.get(j)) <= 0) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));

        return merged;
    }

    private void quickSort(int low, int high, Comparator<Game> comp) {
        if (low < high) {
            int pivotIndex = partition(low, high, comp);
            quickSort(low, pivotIndex - 1, comp);
            quickSort(pivotIndex + 1, high, comp);
        }
    }

    private int partition(int low, int high, Comparator<Game> comp) {
        Game pivot = Data.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comp.compare(Data.get(j), pivot) <= 0) {
                i++;
                Game temp = Data.get(i);
                Data.set(i, Data.get(j));
                Data.set(j, temp);
            }
        }

        Game temp = Data.get(i + 1);
        Data.set(i + 1, Data.get(high));
        Data.set(high, temp);

        return i + 1;
    }


}
