import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class SecondhandDealFinder extends JFrame{
    private ArrayList<ShoppingProject> projects;
    private ShoppingProject currentProject;
    private Search search;
    public SecondhandDealFinder(){
        projects=new ArrayList<ShoppingProject>();
        search=new Search();
        ShoppingProject bicycle=new ShoppingProject("Bicycle");
        bicycle.addListing(new Listing("Trek FX 2","Bicycle",300,"Good",
                "Black","M","Minneapolis","Facebook Marketplace",
                "Smooth ride, lightweight","Minor scratches"));
        bicycle.addListing(new Listing("Giant Escape 3","Bicycle",250,"Like New",
                "Blue","M","St. Paul","Facebook Marketplace",
                "Low price, like new","No accessories"));
        bicycle.addListing(new Listing("Trek Verve","Bicycle",350,"Good",
                "Red","M","Eagan","Craigslist",
                "Comfortable","Older tires"));
        projects.add(bicycle);
        projects.add(new ShoppingProject("Rice Cooker"));
        projects.add(new ShoppingProject("Electric Scooter"));
        projects.add(new ShoppingProject("Laptop"));
        setTitle("Secondhand Deal Finder");
        setSize(650,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        displayHomeScreen();
        setVisible(true);
    }
    public void clearScreen(){
        getContentPane().removeAll();
    }
    public void refresh(){
        revalidate();
        repaint();
    }
    // SCREEN 1
    public void displayHomeScreen(){
        clearScreen();
        setLayout(new GridLayout(0,1,5,5));
        add(new JLabel("Secondhand Deal Finder",SwingConstants.CENTER));
        JTextField searchField=new JTextField();
        JButton searchButton=new JButton("Search Lists");
        add(searchField);
        add(searchButton);
        for(ShoppingProject project:projects){
            JButton button=new JButton(project.getProjectName()+" - "+
                    project.getStatus()+" - "+
                    project.getListings().size()+" saved");
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    currentProject=project;
                    displayProjectScreen();
                }
            });
            add(button);
        }
        JButton createButton=new JButton("Create New List");
        add(createButton);
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ArrayList<ShoppingProject> results=
                        search.searchComparisonLists(projects,searchField.getText());
                String result="";
                for(ShoppingProject project:results){
                    result=result+project.getProjectName()+"\n";
                }
                if(result.equals("")){
                    result="No comparison list found.";
                }
                JOptionPane.showMessageDialog(SecondhandDealFinder.this,result);
            }
        });
        createButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name=JOptionPane.showInputDialog("List Name:");
                if(name!=null){
                    if(!name.equals("")){
                        projects.add(new ShoppingProject(name));
                        displayHomeScreen();
                    }
                }
            }
        });
        refresh();
    }
    // SCREEN 2
    public void displayProjectScreen(){
        clearScreen();
        setLayout(new GridLayout(0,1,5,5));
        add(new JLabel(currentProject.getProjectName(),SwingConstants.CENTER));
        add(new JLabel("Status: "+currentProject.getStatus(),SwingConstants.CENTER));
        add(new JLabel("Saved Listings: "+
                currentProject.getListings().size(),SwingConstants.CENTER));
        JButton addButton=new JButton("Add new listing");
        JButton savedButton=new JButton("View saved listings");
        JButton searchButton=new JButton("Search / Filter listings");
        JButton compareButton=new JButton("Compare items");
        JButton backButton=new JButton("Back");
        add(addButton);
        add(savedButton);
        add(searchButton);
        add(compareButton);
        add(backButton);
        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayAddListingScreen();
            }
        });
        savedButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displaySavedListingsScreen();
            }
        });
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displaySearchScreen();
            }
        });
        compareButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayCompareScreen();
            }
        });
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayHomeScreen();
            }
        });
        refresh();
    }
    // SCREEN 3
    public void displayAddListingScreen(){
        clearScreen();
        JPanel panel=new JPanel(new GridLayout(11,2));
        JTextField name=new JTextField();
        JTextField category=new JTextField();
        JTextField price=new JTextField();
        JTextField condition=new JTextField();
        JTextField color=new JTextField();
        JTextField size=new JTextField();
        JTextField location=new JTextField();
        JTextField source=new JTextField();
        JTextField advantages=new JTextField();
        JTextField disadvantages=new JTextField();
        panel.add(new JLabel("Item Name:")); panel.add(name);
        panel.add(new JLabel("Category:")); panel.add(category);
        panel.add(new JLabel("Price:")); panel.add(price);
        panel.add(new JLabel("Condition:")); panel.add(condition);
        panel.add(new JLabel("Color:")); panel.add(color);
        panel.add(new JLabel("Size:")); panel.add(size);
        panel.add(new JLabel("Location:")); panel.add(location);
        panel.add(new JLabel("Source Website:")); panel.add(source);
        panel.add(new JLabel("Advantages:")); panel.add(advantages);
        panel.add(new JLabel("Disadvantages:")); panel.add(disadvantages);
        JButton saveButton=new JButton("Save");
        JButton backButton=new JButton("Back");
        panel.add(saveButton);
        panel.add(backButton);
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    double itemPrice=Double.parseDouble(price.getText());
                    Listing listing=new Listing(name.getText(),category.getText(),
                            itemPrice,condition.getText(),color.getText(),
                            size.getText(),location.getText(),source.getText(),
                            advantages.getText(),disadvantages.getText());
                    currentProject.addListing(listing);
                    displayProjectScreen();
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(
                            SecondhandDealFinder.this,"Invalid price.");
                }
            }
        });
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayProjectScreen();
            }
        });
        add(panel);
        refresh();
    }
    // SCREEN 4
    public void displaySavedListingsScreen(){
        clearScreen();
        setLayout(new GridLayout(0,1));
        add(new JLabel("Saved Listings",SwingConstants.CENTER));
        for(Listing listing:currentProject.getListings()){
            JPanel row=new JPanel();
            row.add(new JLabel(listing.getItemName()+"  $"+
                    listing.getPrice()+"  "+listing.getCondition()));
            JButton details=new JButton("Details");
            JButton delete=new JButton("Delete");
            row.add(details);
            row.add(delete);
            add(row);
            details.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    displayDetailsScreen(listing);
                }
            });
            delete.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    currentProject.deleteListing(listing);
                    displaySavedListingsScreen();
                }
            });
        }
        JButton back=new JButton("Back");
        add(back);
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayProjectScreen();
            }
        });
        refresh();
    }
    // SCREEN 5
    public void displayDetailsScreen(Listing listing){
        clearScreen();
        setLayout(new BorderLayout());
        String text="";
        text=text+"Item Name: "+listing.getItemName()+"\n";
        text=text+"Category: "+listing.getCategory()+"\n";
        text=text+"Price: $"+listing.getPrice()+"\n";
        text=text+"Condition: "+listing.getCondition()+"\n";
        text=text+"Color: "+listing.getColor()+"\n";
        text=text+"Size: "+listing.getSize()+"\n";
        text=text+"Location: "+listing.getLocation()+"\n";
        text=text+"Source: "+listing.getSourceWebsite()+"\n";
        text=text+"Advantages: "+listing.getAdvantages()+"\n";
        text=text+"Disadvantages: "+listing.getDisadvantages();
        JTextArea area=new JTextArea(text);
        area.setEditable(false);
        add(area,BorderLayout.CENTER);
        JButton delete=new JButton("Delete");
        JButton back=new JButton("Back");
        JPanel buttons=new JPanel();
        buttons.add(delete);
        buttons.add(back);
        add(buttons,BorderLayout.SOUTH);
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                currentProject.deleteListing(listing);
                displaySavedListingsScreen();
            }
        });
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displaySavedListingsScreen();
            }
        });
        refresh();
    }
    // SCREEN 6
    public void displaySearchScreen(){
        clearScreen();
        setLayout(new GridLayout(0,2));
        JTextField name=new JTextField();
        JTextField min=new JTextField();
        JTextField max=new JTextField();
        JTextField condition=new JTextField();
        add(new JLabel("Item Name:")); add(name);
        add(new JLabel("Min Price:")); add(min);
        add(new JLabel("Max Price:")); add(max);
        add(new JLabel("Condition:")); add(condition);
        JButton searchButton=new JButton("Search / Filter");
        JButton backButton=new JButton("Back");
        add(searchButton);
        add(backButton);
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    double minPrice=0;
                    double maxPrice=999999;
                    if(!min.getText().equals("")){
                        minPrice=Double.parseDouble(min.getText());
                    }
                    if(!max.getText().equals("")){
                        maxPrice=Double.parseDouble(max.getText());
                    }
                    ArrayList<Listing> results=search.filterListings(
                            currentProject,minPrice,maxPrice,condition.getText());
                    String result="";
                    for(Listing listing:results){
                        if(name.getText().equals("")){
                            result=result+listing.getItemName()+" - $"+
                                    listing.getPrice()+"\n";
                        }
                        else{
                            if(listing.getItemName().contains(name.getText())){
                                result=result+listing.getItemName()+" - $"+
                                        listing.getPrice()+"\n";
                            }
                        }
                    }
                    if(result.equals("")){
                        result="No listings found.";
                    }
                    JOptionPane.showMessageDialog(
                            SecondhandDealFinder.this,result);
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(
                            SecondhandDealFinder.this,"Invalid price.");
                }
            }
        });
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayProjectScreen();
            }
        });
        refresh();
    }
    // SCREEN 7
    public void displayCompareScreen(){
        clearScreen();
        setLayout(new GridLayout(0,1));
        Comparison comparison=new Comparison();
        add(new JLabel("Compare Items",SwingConstants.CENTER));
        for(Listing listing:currentProject.getListings()){
            JCheckBox box=new JCheckBox(listing.getItemName());
            box.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(box.isSelected()){
                        comparison.selectListing(listing);
                    }
                    else{
                        comparison.removeSelectedListing(listing);
                    }
                }
            });
            add(box);
        }
        JButton compare=new JButton("Compare");
        JButton back=new JButton("Back");
        add(compare);
        add(back);
        compare.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(comparison.getSelectedListings().size()<2){
                    JOptionPane.showMessageDialog(
                            SecondhandDealFinder.this,
                            "Select at least two listings.");
                }
                else{
                    String result=comparison.compareListings();
                    JOptionPane.showMessageDialog(
                            SecondhandDealFinder.this,result);
                    String name=JOptionPane.showInputDialog(
                            "Enter final item name:");
                    if(name!=null){
                        for(Listing listing:comparison.getSelectedListings()){
                            if(listing.getItemName().equals(name)){
                                displayFinalChoiceScreen(listing);
                            }
                        }
                    }
                }
            }
        });
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayProjectScreen();
            }
        });
        refresh();
    }
    // SCREEN 8
    public void displayFinalChoiceScreen(Listing listing){
        currentProject.chooseFinalListing(listing);
        clearScreen();
        setLayout(new GridLayout(0,1));
        add(new JLabel("Final Choice",SwingConstants.CENTER));
        add(new JLabel(listing.getItemName(),SwingConstants.CENTER));
        add(new JLabel("Price:"+listing.getPrice(),SwingConstants.CENTER));
        add(new JLabel("Condition: "+listing.getCondition(),SwingConstants.CENTER));
        add(new JLabel("Location: "+listing.getLocation(),SwingConstants.CENTER));
        add(new JLabel("Source: "+listing.getSourceWebsite(),SwingConstants.CENTER));
        add(new JLabel("FINAL ITEM SELECTED",SwingConstants.CENTER));
        JButton back=new JButton("Back to List");
        JButton home=new JButton("Home");
        add(back);
        add(home);
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayProjectScreen();
            }
        });
        home.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayHomeScreen();
            }
        });
        refresh();
    }
    public static void main(String[] args){
        SecondhandDealFinder program=new SecondhandDealFinder();
    }
}
