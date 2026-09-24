import java.util.ArrayList;
public class Search{
    public Search(){
    }
    public ArrayList<ShoppingProject> searchComparisonLists(ArrayList<ShoppingProject> projects,String name){
        ArrayList<ShoppingProject> results=new ArrayList<ShoppingProject>();
        for(ShoppingProject project:projects){
            if(project.getProjectName().contains(name)){
                results.add(project);
            }
        }
        return results;
    }
    public ArrayList<Listing> searchListings(ShoppingProject project,String itemName){
        ArrayList<Listing> results=new ArrayList<Listing>();
        for(Listing listing:project.getListings()){
            if(listing.getItemName().contains(itemName)){
                results.add(listing);
            }
        }
        return results;
    }
    public ArrayList<Listing> filterListings(ShoppingProject project,double minPrice,double maxPrice,String condition){
        ArrayList<Listing> results=new ArrayList<Listing>();
        for(Listing listing:project.getListings()){
            if(listing.getPrice()>=minPrice){
                if(listing.getPrice()<=maxPrice){
                    if(condition.equals("")){
                        results.add(listing);
                    }
                    else{
                        if(listing.getCondition().equals(condition)){
                            results.add(listing);
                        }
                    }
                }
            }
        }
        return results;
    }
}

