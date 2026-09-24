import java.util.ArrayList;
public class ShoppingProject{
    private String projectName;
    private String status;
    private ArrayList<Listing> listings;
    private Listing finalListing;
    public ShoppingProject(String projectName){
        this.projectName=projectName;
        status="in progress";
        listings=new ArrayList<Listing>();
        finalListing=null;
    }
    public String getProjectName(){
        return projectName;
    }
    public String getStatus(){
        return status;
    }
    public ArrayList<Listing> getListings(){
        return listings;
    }
    public Listing getFinalListing(){
        return finalListing;
    }
    public void addListing(Listing listing){
        listings.add(listing);
    }
    public void deleteListing(Listing listing){
        listings.remove(listing);
    }
    public void chooseFinalListing(Listing listing){
        finalListing=listing;
        updateStatus();
    }
    public void updateStatus(){
        if(finalListing!=null){
            status="completed";
        }
    }
}
