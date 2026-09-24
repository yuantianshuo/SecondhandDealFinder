public class Listing{
    private String itemName;
    private String category;
    private double price;
    private String condition;
    private String color;
    private String size;
    private String location;
    private String sourceWebsite;
    private String advantages;
    private String disadvantages;
    public Listing(String itemName,String category,double price,String condition,String color,String size,String location,String sourceWebsite,String advantages,String disadvantages){
        this.itemName=itemName;
        this.category=category;
        this.price=price;
        this.condition=condition;
        this.color=color;
        this.size=size;
        this.location=location;
        this.sourceWebsite=sourceWebsite;
        this.advantages=advantages;
        this.disadvantages=disadvantages;
    }
    public String getItemName(){
        return itemName;
    }
    public String getCategory(){
        return category;
    }
    public double getPrice(){
        return price;
    }
    public String getCondition(){
        return condition;
    }
    public String getColor(){
        return color;
    }
    public String getSize(){
        return size;
    }
    public String getLocation(){
        return location;
    }
    public String getSourceWebsite(){
        return sourceWebsite;
    }
    public String getAdvantages(){
        return advantages;
    }
    public String getDisadvantages(){
        return disadvantages;
    }
    public void editListing(String itemName,String category,double price,String condition,String color,String size,String location,String sourceWebsite,String advantages,String disadvantages){
        this.itemName=itemName;
        this.category=category;
        this.price=price;
        this.condition=condition;
        this.color=color;
        this.size=size;
        this.location=location;
        this.sourceWebsite=sourceWebsite;
        this.advantages=advantages;
        this.disadvantages=disadvantages;
    }
}
