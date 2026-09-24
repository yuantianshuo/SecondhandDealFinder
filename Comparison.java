import java.util.ArrayList;
public class Comparison{
    private ArrayList<Listing> selectedListings;
    public Comparison(){
        selectedListings=new ArrayList<Listing>();
    }
    public ArrayList<Listing> getSelectedListings(){
        return selectedListings;
    }
    public void selectListing(Listing listing){
        selectedListings.add(listing);
    }
    public void removeSelectedListing(Listing listing){
        selectedListings.remove(listing);
    }
    public String compareListings(){
        if(selectedListings.size()<2){
            return "Please select at least two listings.";
        }
        String result="";
        for(Listing listing:selectedListings){
            result=result+listing.getItemName()+"\n";
            result=result+"Price: $"+listing.getPrice()+"\n";
            result=result+"Condition: "+listing.getCondition()+"\n";
            result=result+"Location: "+listing.getLocation()+"\n";
            result=result+"Source: "+listing.getSourceWebsite()+"\n";
            result=result+"Advantages: "+listing.getAdvantages()+"\n";
            result=result+"Disadvantages: "+listing.getDisadvantages()+"\n\n";
        }
        return result;
    }
}