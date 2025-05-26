public class Game{
    String name;
    String category;
    int price;
    int quality;

    public Game(String name, String category, int price, int quality){
        this.name = name;
        this.category = category;
        this.price = price;
        this.quality = quality;
    }

    int getPrice(){
        return price;
    }
    String getCategory(){
        return category;
    }

    int getQuality(){
        return quality;
    }
    String getName(){
        return name;
    }

}

