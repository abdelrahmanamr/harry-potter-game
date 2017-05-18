package harrypotter.view;


import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.magic.DamagingSpell;
import sun.audio.*;

import java.io.*;

import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.Task;
import harrypotter.model.tournament.ThirdTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.Obstacle;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.TreasureCell;
import harrypotter.model.world.WallCell;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.view.ApplicationListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;


public class Application extends JFrame implements ActionListener   {
private JPanel mainPanel;
private JPanel panel1;
private JPanel panel2;
private JPanel panel3;
private JPanel panel4;
private JPanel click1,click2,click3,click4,firstTasker;

private JButton button1;
private JButton button2;
private JButton button3;
private JButton button4;

private JTextField Text1;
private JTextField Text2;
private JTextField Text3;
private JTextField Text4;

private JComboBox com11;
private JComboBox com21;
private JComboBox com31;
private JComboBox com41;

private JComboBox com12;
private JComboBox com22;
private JComboBox com32;
private JComboBox com42;

private JComboBox com13;
private JComboBox com23;
private JComboBox com33;
private JComboBox com43;

private JComboBox com14;
private JComboBox com24;
private JComboBox com34;
private JComboBox com44;

private JLabel house1;
private JLabel house2;
private JLabel house3;
private JLabel house4, finals;

private JLabel spells1;
private JLabel spells2;
private JLabel spells3;
private JLabel spells4;

private JLabel image1;
private Tournament u;
private Application q;
private static int count =0;


public Application(){
	setSize(1500,1500);
	setVisible(true);
	setTitle("Harry Potter THE GAME");
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	music();
	try {
		u = new Tournament();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	this.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e){
            int confirmed = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to exit the program? , The GAME will be over", "Exit Program Message Box",
                    JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                  dispose();
                  
                }
              }
            });
	
	mainPanel = new JPanel(new FlowLayout());
	panel1 = new JPanel(new FlowLayout());
	panel2 = new JPanel(new FlowLayout());
	panel3 = new JPanel(new FlowLayout());
	panel4 = new JPanel(new FlowLayout());
	
	panel1.setBackground(Color.black);
	panel2.setBackground(Color.RED);
	panel3.setBackground(Color.black);
	panel4.setBackground(Color.RED);
	
	button1 = new JButton("START THE GAME");
	button1.setBackground(Color.YELLOW);
	
	String[] House = {"Gryffindor","Slytherin","Hufflepuff","Ravenclaw"};
	
	 String[] DMG = {"-Damaging Spell-","Sectumsempra" , "Reducto","Piertotum Locomotor","Oppugno","Incendio","Expulso","Bombarda","Avada Kedavra",
			 "Crucio","Igni","Kamehameha"};
			 
	 String[]HEL = {"-Healing Spell-","Cheering Charm","Expecto Patronum","Ferula","Protego Horribilis","Rennervate","Quen"};
	 String[]REL= {"-Relocating Spell-","Accio","Imperio","Wingardium Leviosa","Axii"};
	
	 com11 = new JComboBox();
	 com11 = addinCombo(House);
	 com21 = new JComboBox();
	 com21 = addinCombo(DMG);
	 com31 = new JComboBox();
	 com31 = addinCombo(HEL);
	 com41 = new JComboBox();
	 com41 = addinCombo(REL);
	
	 com12 = new JComboBox();
     com12 = addinCombo(House);
	 com22 = new JComboBox();
	 com22 = addinCombo(DMG);
	 com32 = new JComboBox();
	 com32 = addinCombo(HEL);
	 com42 = new JComboBox();
	 com42 = addinCombo(REL);
	 
	 com13 = new JComboBox();
	 com13 = addinCombo(House);
	 com23 = new JComboBox(DMG);
	 com23 = addinCombo(DMG);
	 com33 = new JComboBox();
	 com33 = addinCombo(HEL);
	 com43 = new JComboBox();
	 com43 = addinCombo(REL);
	 
	 com14 = new JComboBox();
	 com14 = addinCombo(House);
	 com24 = new JComboBox(DMG);
	 com24 = addinCombo(DMG);
	 com34 = new JComboBox();
	 com34 = addinCombo(HEL);
	 com44 = new JComboBox();
	 com44 = addinCombo(REL);
	
	 image1 = new JLabel();
	 image1.setIcon(new ImageIcon("src/final2.jpg"));
	
	 image1.setBounds(0,0,1000,1000);
	
	Text1= new JTextField("Enter the champion's name :");
	Text2= new JTextField("Enter the champion's name :");
	Text3= new JTextField("Enter the champion's name :" );
	Text4= new JTextField("Enter the champion's name :");
	
	house1 = new JLabel("The House:");
	house2 = new JLabel("The House:");
	house3 = new JLabel("The House:");
	house4= new JLabel("The House:");
	house2.setForeground(Color.WHITE);
	house1.setForeground(Color.WHITE);
	house3.setForeground(Color.WHITE);
	house4.setForeground(Color.WHITE);
	
	
	
	spells1 = new JLabel("The Spells:");
	spells2 = new JLabel("The Spells:");
	spells3 = new JLabel("The Spells:");
	spells4 = new JLabel("The Spells:");
	spells1.setForeground(Color.WHITE);
	spells2.setForeground(Color.WHITE);
	spells3.setForeground(Color.WHITE);
	spells4.setForeground(Color.WHITE);
	
	button1.addActionListener(this);
	
	
	panel1.add(button1);
	panel1.add(Text1);
	panel1.add(house1);
	panel1.add(com11);
	panel1.add(spells1);
	panel1.add(com21);
	panel1.add(com31);
	panel1.add(com41);
	
	
	//panel2.add(button2);
	panel2.add(Text2);
	panel2.add(house2);
	panel2.add(com12);
	panel2.add(spells2);
	panel2.add(com22);
	panel2.add(com32);
	panel2.add(com42);
	
	//panel3.add(button3);
	panel3.add(Text3);
	panel3.add(house3);
	panel3.add(com13);
	panel3.add(spells3);
	panel3.add(com23);
	panel3.add(com33);
	panel3.add(com43);
	
	//panel4.add(button4);
	panel4.add(Text4);
	panel4.add(house4);
	panel4.add(com14);
	panel4.add(spells4);
	panel4.add(com24);
	panel4.add(com34);
	panel4.add(com44);
	
	
	
	this.setContentPane(mainPanel);
	mainPanel.setBackground(Color.black);

	
	mainPanel.add(panel1);//BorderLayout.NORTH);
	mainPanel.add(panel2);//BorderLayout.EAST);
	mainPanel.add(panel3);//BorderLayout.WEST);
	mainPanel.add(panel4);//BorderLayout.SOUTH);
	mainPanel.add(image1);
	
	
	
	this.repaint();
	this.revalidate();
	
	
	
}

@Override
public void actionPerformed(ActionEvent a) {
	Object x = a.getSource();
	
	
	
	
	
	if(x.equals(button1)){
		
		
		  
		String y = (String) com11.getSelectedItem();
		String z = (String) com12.getSelectedItem();
		String w = (String) com13.getSelectedItem();
		String g = (String) com14.getSelectedItem();
		
		String p1 = (String) com21.getSelectedItem(); //dmg
		String b1 = (String) com22.getSelectedItem();
		String q1 = (String) com23.getSelectedItem();
		String e1 = (String) com24.getSelectedItem();
		
		
		String p2 = (String) com31.getSelectedItem();//hel
		String b2= (String) com32.getSelectedItem();
		String q2= (String) com33.getSelectedItem();
		String e2 = (String) com34.getSelectedItem();
		
		String p3 = (String) com41.getSelectedItem();//rel
		String b3= (String) com42.getSelectedItem();
		String q3 = (String) com43.getSelectedItem();
		String e3 = (String) com44.getSelectedItem();
		
	
		
		
		String first = Text1.getText();
		String second = Text2.getText();
		String third = Text3.getText();
		String fourth = Text4.getText();
		
		if(p1.equals("-Damaging Spell-") || b1.equals("-Damaging Spell-") ||
				q1.equals("-Damaging Spell-") || e1.equals("-Damaging Spell-")) //checks if valid dmg
			JOptionPane.showMessageDialog(mainPanel, "Please choose a valid Damaging Spells.");
		
		else{
		if(p2.equals("-Healing Spell-") || b2.equals("-Healing Spell-") ||
				q2.equals("-Healing Spell-") || e2.equals("-Healing Spell-")) //checks if valid dmg
			JOptionPane.showMessageDialog(mainPanel, "Please choose a valid Healing Spells.");
		else{
	
		if(p3.equals("-Relocating Spell-") || b3.equals("-Relocating Spell-") ||
				q3.equals("-Relocating Spell-") || e3.equals("-Relocating Spell-")) //checks if valid dmg
			JOptionPane.showMessageDialog(mainPanel, "Please choose a valid Relocating Spells.");
		
		else{
			
		
		ArrayList<Champion> c = new ArrayList();
		
		
	
		
		switch(y){
		case("Gryffindor"): c.add(new GryffindorWizard(first));break;
		case("Slytherin"):c.add(new SlytherinWizard(first));break;
		case("Hufflepuff"):c.add(new HufflepuffWizard(first));break;
		case("Ravenclaw"): c.add(new RavenclawWizard(first));break;
		
		}
		switch(z){
		case("Gryffindor"): c.add(new GryffindorWizard(second));break;
		case("Slytherin"):c.add(new SlytherinWizard(second));break;
		case("Hufflepuff"):c.add(new HufflepuffWizard(second));break;
		case("Ravenclaw"): c.add(new RavenclawWizard(second));break;
		}switch(w){
		case("Gryffindor"): c.add(new GryffindorWizard(third));break;
		case("Slytherin"):c.add(new SlytherinWizard(third));break;
		case("Hufflepuff"):c.add(new HufflepuffWizard(third));break;
		case("Ravenclaw"): c.add(new RavenclawWizard(third));break;
		}switch(g){
		case("Gryffindor"): c.add(new GryffindorWizard(fourth));break;
		case("Slytherin"):c.add(new SlytherinWizard(fourth));break;
		case("Hufflepuff"):c.add(new HufflepuffWizard(fourth));break;
		case("Ravenclaw"): c.add(new RavenclawWizard(fourth));break;
		}
		
		try {
			
			addChampioninTour(c,u);
		    u.beginTournament();
			ArrayList<Spell> spell1 = LoadSpell(p1 , p2 , p3, u.getSpells());
			ArrayList<Spell> spell2 = LoadSpell(b1 , b2 , b3, u.getSpells());
			ArrayList<Spell> spell3 = LoadSpell(q1 , q2 , q3, u.getSpells());
			ArrayList<Spell> spell4 = LoadSpell(e1 , e2 , e3, u.getSpells());
			
			for(int i =0;i<c.size();i++){
				if(i==0)
				addSpells(c.get(0),spell1);
				if(i==1)
				addSpells(c.get(1),spell2);
				if(i==2)
				addSpells(c.get(2),spell3);
				if(i==3)
				addSpells(c.get(3),spell4);}
			
			tournament(u.getFirstTask().getCurrentChamp(),u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}}}}}


 
	
	public void tournament(Champion  c , Tournament u ) throws IOException{
		if(u.getTheTask().getChampions().size()==0){
			JOptionPane.showMessageDialog(null, "GAME OVER!, Run the game again if you want to try again.");
			System.exit(0);}
		
		mainPanel.removeAll();
		
		mainPanel.repaint();
		mainPanel.revalidate();
		JPanel tournamenter = new JPanel(null);
		 
		
		
		JLabel f = new JLabel(); //label of the logo of task
	
		
		tournamenter.setBackground(Color.BLACK);
		 
		
		  
		  if(u.getTheTask() instanceof FirstTask) {    //LOGO OF THE 1st TASK
			  f.setIcon(new ImageIcon("src/FIRSTTASK1jpg.jpg"));}
		  if(u.getTheTask() instanceof SecondTask) {    //LOGO OF THE 2nd TASK
			  f.setIcon(new ImageIcon("src/SECONDTASK2.jpg"));}
		  if(u.getTheTask() instanceof ThirdTask) {    //LOGO OF THE 3rd TASK
			  f.setIcon(new ImageIcon("src/thirdTASK2.jpg"));}
		    
		  f.setBounds(890,0,500,55);
		  
		 Cell [][] cell =(u.getTheTask()).getMap();
		
		GridLayout gl = new GridLayout(10,10);
		JPanel map = new JPanel();
	    map.setLayout(gl);
	   
	    map = generator(cell, u.getTheTask());
	    
	   
	  
		JPanel Info = info(u);
		
		 JLabel rm = new JLabel("RemainingMoves:" + u.getTheTask().getAllowedMoves() + "." + "        "+"Trait Activated: " + u.getTheTask().isTraitActivated() + ".");
		 rm.setForeground(Color.YELLOW);
		 rm.setFont(new Font("Harry P",Font.BOLD,25));
		 rm.setBounds(905,60,500,100);
		
		
		
		
		
		Info.setBounds(905,200, 500, 500);
		
		
	    JLabel image1 = new JLabel();
		
		
		
		
		map.setBackground(Color.RED);
		map.setBounds(0, 0, 900, 990);
	    
		getContentPane().remove(mainPanel);
		setContentPane(tournamenter);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src/wand3.png");
		Cursor cc = toolkit.createCustomCursor(image , new Point(tournamenter.getX(), 
		          tournamenter.getY()), "img");
		tournamenter.setCursor (cc);
		tournamenter.add(map);
		tournamenter.add(f);
		tournamenter.add(Info);
		tournamenter.add(rm);
		
		tournamenter.repaint();
		tournamenter.revalidate();
		
	}
		

	
	
	
	
	
public JPanel generator(Cell[][]cell , Task task ) {
	GridLayout gl = new GridLayout(10,10);
	JPanel map = new JPanel();
    map.setLayout(gl);
    ArrayList<Point> mCells = new ArrayList<Point>();
    
    if(task instanceof FirstTask){
    mCells = ((FirstTask)task).getMarkedCells();}
    
    ArrayList<Point> OldmCells = new ArrayList<Point>();
  
    if(u.getFirstTask().isAttacked() && task instanceof FirstTask){
    OldmCells = ((FirstTask)task).getOldmarkedCells();}
   
    map.setSize(900,990);
  
    
    
	
	for(int i = 0; i<10;i++){
		for(int j =0;j<10;j++){
			
			
			
			
		if(cell[i][j] instanceof EmptyCell ){
			JButton x = new JButton();
		
			if((task instanceof FirstTask)&&(u.getFirstTask().isAttacked())&&((OldmCells.get(0).x == i && OldmCells.get(0).y == j)||((OldmCells.get(1).x == i) && OldmCells.get(1).y==j))){
				x.setBackground(Color.green);
				   x.setIcon(new ImageIcon("src/fire1.gif"));
				   map.add(x);
	
			}else{
			
			
			if((task instanceof FirstTask)&&(u.getTheTask().getCurrentChamp() instanceof RavenclawWizard)&&(u.getTheTask().isTraitActivated())&&
					((mCells.get(0).x == i && mCells.get(0).y == j)||((mCells.get(1).x == i) && mCells.get(1).y==j))){
						x.setBackground(Color.GREEN);
						   x.setIcon(new ImageIcon("src/dragonaim1.png"));
						   map.add(x);
			
			}else{
		   if(task instanceof FirstTask && (i==6 && j==4)){
				//x.setBackground(Color.wh);
				   x.setIcon(new ImageIcon("src/cat2.gif"));
				   map.add(x);
		   }
		   else{ 
			if((task instanceof FirstTask)&&(i== 4 && j == 4)){
			x.setBackground(Color.GREEN);
		   x.setIcon(new ImageIcon("src/egg cell2.png"));
		   map.add(x);
			}
			 
		   else
			   
			   if(task instanceof SecondTask){
				x.setBackground(Color.BLUE);
				x.setIcon(new ImageIcon("src/water.png"));
				
				map.add(x);
			}else{
			   x.setBackground(Color.BLACK);
		   x.setIcon(new ImageIcon("src/emptycellgrass.png"));
			map.add(x);   }}}}}
			
		
		
		if(cell[i][j] instanceof CollectibleCell){
			JButton x = new JButton();
			if((task instanceof FirstTask)&&(u.getTheTask().getCurrentChamp() instanceof RavenclawWizard)&&(u.getTheTask().isTraitActivated())&&
					((mCells.get(0).x == i && mCells.get(0).y == j)||((mCells.get(1).x == i) && mCells.get(1).y==j))){
						x.setBackground(Color.GREEN);
						   x.setIcon(new ImageIcon("src/dragonaim1.png"));
						   map.add(x);
			
			}else{if(task instanceof FirstTask){
			x.setBackground(Color.GREEN);
			x.setIcon(new ImageIcon("src/emptycellgrass.png"));
			
			map.add(x);}
			
			else{
				if(task instanceof SecondTask){
					x.setBackground(Color.BLUE);
					x.setIcon(new ImageIcon("src/water.png"));
					
					map.add(x);
					
					
				}else{
					x.setBackground(Color.GREEN);
					x.setIcon(new ImageIcon("src/emptycellgrass.png"));
					
					map.add(x);
				}
			}
		}}
		
		if(cell[i][j] instanceof ObstacleCell){

			
			
			if(task instanceof FirstTask || task instanceof ThirdTask){
			JButton x = new JButton();
			if((task instanceof FirstTask)&&(u.getFirstTask().isAttacked())&&((OldmCells.get(0).x == i && OldmCells.get(0).y == j)||((OldmCells.get(1).x == i) && OldmCells.get(1).y==j))){
				x.setBackground(Color.GREEN);
				   x.setIcon(new ImageIcon("src/fire1.gif"));
				   map.add(x);
	
			}else{
			
			if((task instanceof FirstTask)&&(u.getTheTask().getCurrentChamp() instanceof RavenclawWizard)&&(u.getTheTask().isTraitActivated())&&
					((mCells.get(0).x == i && mCells.get(0).y == j)||((mCells.get(1).x == i) && mCells.get(1).y==j))){
						x.setBackground(Color.GREEN);
						   x.setIcon(new ImageIcon("src/dragonaim1.png"));
						   map.add(x);
			
			}else{
				
			x.setBackground(Color.DARK_GRAY);		
			x.setIcon(new ImageIcon("src/newobstacle.png"));
			Obstacle o = ((ObstacleCell)cell[i][j]).getObstacle();
			x.setToolTipText("Obstacle HP: " + o.getHp());
			map.add(x);}}}
			
			else{JButton x = new JButton();
			x.setBackground(Color.DARK_GRAY);
			x.setIcon(new ImageIcon("src/merperson1.png"));
			Merperson o = (Merperson)((ObstacleCell)cell[i][j]).getObstacle();
			x.setToolTipText("Merperson " +"HP: " +o.getHp() + "DMG :" + o.getDamage());
			map.add(x);}}
				
			
			
		if(cell[i][j] instanceof CupCell){
			JButton x = new JButton();
			x.setBackground(Color.DARK_GRAY);
			x.setIcon(new ImageIcon("src/emptycellgrass.png"));
			map.add(x);
		}if(cell[i][j] instanceof TreasureCell){
			JButton x = new JButton();
			x.setBackground(Color.GREEN);
			
			x.setIcon(new ImageIcon("src/water.png"));
			map.add(x);
		}if(cell[i][j] instanceof WallCell){
			JButton x = new JButton();
			x.setIcon(new ImageIcon("src/wallCell1.png"));
			x.setBackground(Color.DARK_GRAY);
			
			
			map.add(x);
		}
		if(cell[i][j] instanceof ChampionCell){
			
			JButton x = new JButton();
			x.setBackground(Color.DARK_GRAY);
			Wizard champ = (Wizard) ((ChampionCell)cell[i][j]).getChamp();
			if((task instanceof FirstTask)&&(u.getFirstTask().isAttacked())&&((OldmCells.get(0).x == i && OldmCells.get(0).y == j)||((OldmCells.get(1).x == i) && OldmCells.get(1).y==j))){
				x.setBackground(Color.BLACK);
				   x.setIcon(new ImageIcon("src/zombie.png"));
				   map.add(x);}else{
			if((task instanceof FirstTask)&&(u.getTheTask().getCurrentChamp() instanceof RavenclawWizard)&&(u.getTheTask().isTraitActivated())&&
					((mCells.get(0).x == i && mCells.get(0).y == j)||((mCells.get(1).x == i) && mCells.get(1).y==j))){
						x.setBackground(Color.ORANGE);
						   x.setIcon(new ImageIcon("src/dragonaim1.png"));
						   map.add(x);
			
			}else{
		
			if(champ instanceof GryffindorWizard){
				if(champ.getName().equalsIgnoreCase("batman")){
				x.setBackground(Color.RED);
				x.setIcon(new ImageIcon("src/batmancharacter1png.png"));
				x.getLocation();}
			else{
				if(champ.getName().equalsIgnoreCase("harry potter")){
				x.setBackground(Color.RED);
				x.setIcon(new ImageIcon("src/harry.png"));
				x.getLocation();}
				else{
					
				
				x.setBackground(Color.RED);
				x.setIcon(new ImageIcon("src/loaigryffindor2.png"));
				x.getLocation();}
			}}
			
		
			
		
			if(champ instanceof HufflepuffWizard){
				x.setBackground(Color.YELLOW);
				x.setIcon(new ImageIcon("src/abdocharacter1.png"));}
			x.getLocation();
			
				if(champ instanceof RavenclawWizard){
					x.setBackground(Color.BLUE);
					x.setIcon(new ImageIcon("src/mortada1.png"));}
				x.getLocation();
				
				
				
				if(champ instanceof SlytherinWizard){
					x.setBackground(Color.GREEN);
					x.setIcon(new ImageIcon("src/wa7shcharacter1.png"));}
				
				
				map.add(x);}}}}
			
				
	
		
		
		}
	
	
	
	
	
	
		
	
    

	return map;}






public JPanel info(Tournament u){
	
	JPanel y = new JPanel(new GridLayout(10,2));
	JLabel healthbar = new JLabel();
	
	
	Wizard w = (Wizard) u.getTheTask().getCurrentChamp();
	y.setSize(200,200);
	y.setBackground(Color.black);
	String name = w.getName();
    String hp = w.getHp() +"/"+ w.getDefaultHp();
    String ip = w.getIp() +"/"+ w.getDefaultIp();
    int potionINT = w.getInventory().size();
    String potions ="You have " + potionINT+" potions." ;
    String desc1="";
    String desc2 = "";
    String traitCoolDown ="The champion's trait cooldown: " + w.getTraitCooldown();
    
   
    if(w instanceof RavenclawWizard){
		
		 if(u.getTheTask() instanceof FirstTask){
			 desc1 = "This Trait will allow you the know";
		 		desc2="where the Dragon will fire.";}
		if(u.getTheTask() instanceof SecondTask){
			desc1 = "This Trait will the direction";
	 		desc2="to the champ's Treasure cell";}
		}if(u.getTheTask() instanceof ThirdTask){
			desc1 = "This Trait will give you the direction";
	 		desc2="to the Cup cell.";}

			  if(w.getHp()<w.getDefaultHp() && w.getHp()> (w.getDefaultHp()/2))
	    	    
	    	    healthbar.setIcon(new ImageIcon("src/health bar Ravenclawshwya.png"));
	    	    else{
	    	    	
	    	    	if(w.getHp()<w.getDefaultHp() && w.getHp()<=(w.getDefaultHp()/2))
	    	    	healthbar.setIcon(new ImageIcon("src/health bar Ravenclawablnos.png"));
	    	    else
	    	    	healthbar.setIcon(new ImageIcon("src/health bar Ravenclaw1.png"));
	    	   
	    	    }

	
    if(w instanceof GryffindorWizard){
    	desc1 = " This Trait will allow the champ";
       desc2 =   " to move 2 moves in one turn.";
       
       if(w.getHp()<w.getDefaultHp() && w.getHp()> (w.getDefaultHp()/2)){
    	    
    	    healthbar.setIcon(new ImageIcon("src/health bar gryffindor11shwya.png"));}
    	    else{
    	    	
    	    	
    	    	if(w.getHp()<w.getDefaultHp() && w.getHp()<=(w.getDefaultHp()/2)){
    	    	healthbar.setIcon(new ImageIcon("src/health bar gryffindor11ablnos.png"));}
    	    else{
    	    	healthbar.setIcon(new ImageIcon("src/health bar gryffindor3.png"));
    	    }
    	    }
    
    
    
    }

    
	
	
	

	
	

			  if(w instanceof SlytherinWizard){
		desc1="This Trait will allow the champ";
			desc2=	 " to jump Obstacles and Walls.";

			 if(w.getHp()<w.getDefaultHp() && w.getHp()> (w.getDefaultHp()/2)){
		    	    
		    	    healthbar.setIcon(new ImageIcon("src/health bar slytherin.png"));}
		    	    else{if(w.getHp()<w.getDefaultHp() && w.getHp()<=(w.getDefaultHp()/2))
		    	    	healthbar.setIcon(new ImageIcon("src/health bar slytherinablnos.png"));
		    	    else{
		    	    	healthbar.setIcon(new ImageIcon("src/health bar slytherin1.png"));}
		    	    }
	
	
	}
	
	
	
	if(w instanceof HufflepuffWizard){
		if(u.getTheTask() instanceof ThirdTask){
			desc1="This Trait will help the champ";
			desc2= " absorb half the Attack.";
		}else{
		desc1="This Trait will help the champ";
		desc2= " dodge any attacks.";}
		 if(w.getHp()<w.getDefaultHp() && w.getHp()> (w.getDefaultHp()/2)){
	    	    
	    	    healthbar.setIcon(new ImageIcon("src/health bar Hufflepufshwya.png"));}
	    	    else{if(w.getHp()<w.getDefaultHp() && w.getHp()<=(w.getDefaultHp()/2))
	    	    	healthbar.setIcon(new ImageIcon("src/health bar Hufflepufablnos.png"));
	    	    else{
	    	    	healthbar.setIcon(new ImageIcon("src/health bar Hufflepuf1.png"));}
	    	    }
	    	    }
	
		
	
     JLabel namer = new JLabel(name);
     namer.setForeground(Color.GREEN);
     namer.setFont(new Font("Harry P",Font.BOLD,23));
    y.add(namer);
    
    JLabel traitCoolDownr = new JLabel(traitCoolDown);
    traitCoolDownr.setForeground(Color.WHITE);
    traitCoolDownr.setFont(new Font("Harry P",Font.BOLD,20));
    y.add(traitCoolDownr);
    
    JLabel desc1r = new JLabel(desc1);
    desc1r.setForeground(Color.WHITE);
    desc1r.setFont(new Font("Harry P",Font.BOLD,20));
    y.add(desc1r);
    
    JLabel desc2r = new JLabel(desc2);
    desc2r.setForeground(Color.WHITE);
    desc2r.setFont(new Font("Harry P",Font.BOLD,20));
    y.add(desc2r);
   // y.add(new JLabel("________________________"));
	
    JLabel hpr = new JLabel("HP: "+hp);
    hpr.setForeground(Color.WHITE);
    hpr.setFont(new Font("Harry P",Font.BOLD,23));
    y.add(hpr);
    healthbar.setSize(50,50);
    
	y.add(healthbar);
	// y.add(new JLabel("________________________"));
	
	 JLabel potionsr = new JLabel(potions);
     potionsr.setForeground(Color.WHITE);
     potionsr.setFont(new Font("Harry P",Font.BOLD,23));
	 y.add(potionsr);
	 
	 JLabel ipr = new JLabel("IP: " +ip);
     ipr.setForeground(Color.WHITE);
     ipr.setFont(new Font("Harry P",Font.BOLD,23));
	 y.add(ipr);
	  //JLabel rm = new JLabel("RemainingMoves:" + u.getTheTask().getAllowedMoves() + ".");
	  //rm.setForeground(Color.YELLOW);
	  //y.add(rm);
	  //y.add(new JLabel("________________________"));
	  
	
	 
	 
	for(int i=0; i<w.getSpells().size();i++){
		
	Spell sp = w.getSpells().get(i);
	
	String sName = sp.getName();
	String sCoolDown = "The spell's cooldown is: " + sp.getCoolDown()+"/"+sp.getDefaultCooldown(); 
	String sCost= "The spell's cost is :" + sp.getCost() + "";
	
	if(sp instanceof DamagingSpell){
	JLabel dmg = new JLabel("Damaging Spell:");
    dmg.setForeground(Color.RED);
    dmg.setFont(new Font("Harry P",Font.BOLD,23));
	y.add(dmg);
	JLabel sNamer = new JLabel(sName +" (DMG :"+((DamagingSpell)sp).getDamageAmount()+")");
	sNamer.setForeground(Color.RED);
	sNamer.setFont(new Font("Harry P",Font.BOLD,20));
	y.add(sNamer);
	
	JLabel sCoolDownr = new JLabel(sCoolDown);
	sCoolDownr.setForeground(Color.WHITE);
	sCoolDownr.setFont(new Font("Harry P",Font.BOLD,23));
	y.add(sCoolDownr);
	JLabel sCostr = new JLabel(sCost);
	sCostr.setForeground(Color.WHITE);
	sCostr.setFont(new Font("Harry P",Font.BOLD,23));
	y.add(sCostr);
	
	}
	
	
	
	if(sp instanceof HealingSpell){
	JLabel hel = new JLabel("Healing Spell:");
    hel.setForeground(Color.RED);
    hel.setFont(new Font("Harry P",Font.BOLD,23));
	y.add(hel);
	JLabel sNamer = new JLabel(sName + " (HEL: "+ ((HealingSpell)sp).getHealingAmount()+")");
	sNamer.setForeground(Color.RED);
	sNamer.setFont(new Font("Harry P",Font.BOLD,20));
	y.add(sNamer);
	
	JLabel sCoolDownr = new JLabel(sCoolDown);
	sCoolDownr.setForeground(Color.WHITE);
	sCoolDownr.setFont(new Font("Harry P",Font.BOLD,23));
	y.add(sCoolDownr);
	JLabel sCostr = new JLabel(sCost);
	sCostr.setForeground(Color.WHITE);
	sCostr.setFont(new Font("Harry P",Font.BOLD,23));
	y.add(sCostr);
	}
	
	if(sp instanceof RelocatingSpell){
	JLabel rel = new JLabel("Relocating Spell:");
    rel.setForeground(Color.RED);
    rel.setFont(new Font("Harry P",Font.BOLD,23));
	y.add(rel);
	
	JLabel sNamer = new JLabel(sName + " (Range: "+ ((RelocatingSpell)sp).getRange()+")");
	sNamer.setForeground(Color.RED);
	sNamer.setFont(new Font("Harry P",Font.BOLD,20));
	y.add(sNamer);
	
	JLabel sCoolDownr = new JLabel(sCoolDown);
	sCoolDownr.setForeground(Color.WHITE);
	sCoolDownr.setFont(new Font("Harry P",Font.BOLD,23));
	y.add(sCoolDownr);
	JLabel sCostr = new JLabel(sCost);
	sCostr.setForeground(Color.WHITE);
	sCostr.setFont(new Font("Harry P",Font.BOLD,23));
	y.add(sCostr);
	
	
	
	
	
	
	}}
	return y;}
	
	

	
	
	
	




public ArrayList<Spell> LoadSpell(String x , String y , String z , ArrayList<Spell> generalSpell ){
	
	
	ArrayList<Spell> s = new ArrayList<Spell>();
	 for(int i = 0; i<generalSpell.size();i++){
		 Spell ss =  generalSpell.get(i);
		 if(x.equals(ss.getName())){
			 DamagingSpell ds= (DamagingSpell)ss;
			DamagingSpell d = new DamagingSpell(x,(ds.getCost()), ds.getCoolDown(), ds.getDamageAmount());
			 s.add(d);
		 }
		 if(y.equals(ss.getName())){
			 HealingSpell hs = (HealingSpell)ss;
			 HealingSpell h = new HealingSpell(y,(hs.getCost()), hs.getCoolDown(), hs.getHealingAmount());
		     s.add(h);
	 }if(z.equals(ss.getName())){
		 
		 RelocatingSpell rs = (RelocatingSpell)ss;
		 RelocatingSpell r = new RelocatingSpell(z,(rs.getCost()), rs.getCoolDown(), rs.getRange());
		 s.add(r);
	 }}return s;
	
	
	
	
}

public void addSpells(Champion c , ArrayList<Spell> s){
	Wizard w = (Wizard) c;
	
	for(int i=0;i<s.size();i++){
	if(s.get(i) instanceof DamagingSpell){
		
		DamagingSpell ds= (DamagingSpell)s.get(i);
	w.getSpells().add(new DamagingSpell(ds.getName(),(ds.getCost()), ds.getCoolDown(), ds.getDamageAmount()));
		
}if(s.get(i)instanceof RelocatingSpell){
	 RelocatingSpell rs = (RelocatingSpell)s.get(i);
	w.getSpells().add(new RelocatingSpell(rs.getName(),(rs.getCost()), rs.getCoolDown(), rs.getRange()));
}if(s.get(i) instanceof HealingSpell){
	HealingSpell hs = (HealingSpell)s.get(i);
	w.getSpells().add(new HealingSpell(hs.getName(),(hs.getCost()), hs.getCoolDown(), hs.getHealingAmount())); 
}}
}


public JComboBox addinCombo(String[]x){
	JComboBox combo = new JComboBox();
	for(int i =0; i<x.length;i++){
		combo.addItem(x[i]);
	}return combo;
}
public static void music(){
	try {
	    File yourFile;
	    AudioInputStream stream;
	    AudioFormat format;
	    DataLine.Info info;
	    Clip clip;
        yourFile = new File("src/Start_up_menu.wav");
	    stream = AudioSystem.getAudioInputStream(yourFile);
	    format = stream.getFormat();
	    info = new DataLine.Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	catch (Exception e) {
	    
	}}
	

/*public JPanel BattleMode(Champion c1, Champion c2){
	
	JPanel battle = new JPanel();
	battle.setSize(1300,1300);
	battle.setBounds(0,0,1300,1300);
	
	
	Wizard w1 = (Wizard)c1;
	Wizard w2 = (Wizard)c2;
	
	 JPanel info1  = info(c1);
	 info1.setBounds(0,0,500,300);
	 JPanel info2 = info(c2);
	 info2.setBounds(0,0,500,300);
	 
	 JComboBox jcb1 = new JComboBox();
	 jcb1.addItem("Attack");
	 jcb1.addItem("Dodge");
	 jcb1.addItem("Use Healing Spell");
	 
	 JComboBox jcb2 = new JComboBox();
	 jcb2.addItem("Attack");
	 jcb2.addItem("Dodge");
	 jcb2.addItem("Use Healing Spell");
	 
	 Boolean turn1 = true;
	 Boolean turn2 = false;
	 
	 while(w1.getHp() !=0 || w2.getHp() !=0){
		 
		 
			String x = (String) jcb1.getSelectedItem();
			
			if(turn1){
			 if(x.equals("Attack") && !(y.equals("Dodge"))){
				 try {
					u.getTheTask().castDamagingSpell((DamagingSpell)(w1.getSpells().get(0)), getTheDirection(w1.getLocation(),w2.getLocation()));
				} catch (InCooldownException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotEnoughIPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OutOfBordersException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidTargetCellException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			 else{
				 if(x.equals("Healing Spell")){
					 try {
						u.getTheTask().castHealingSpell((HealingSpell)w1.getSpells().get(2));
					} catch (InCooldownException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotEnoughIPException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 
	
			 }}	 turn1 = false;
			     turn2 = true;
			 }
			if(turn2){
				
				if(y.equals("Attack") && !(x.equals("Dodge"))){
					 try {
						u.getTheTask().castDamagingSpell((DamagingSpell)(w1.getSpells().get(0)), getTheDirection(w1.getLocation(),w2.getLocation()));
					} catch (InCooldownException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotEnoughIPException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (OutOfBordersException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidTargetCellException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
				 else{
					 if(y.equals("Healing Spell")){
						 try {
							u.getTheTask().castHealingSpell((HealingSpell)w1.getSpells().get(2));
						} catch (InCooldownException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NotEnoughIPException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				 
		
				 }}
		 
		 
		 
	 }
	 }
	
	
}*/



public Direction getTheDirection(Point g , Point e){
	
	if(g.x > e.x)
		return Direction.FORWARD;
	else{
		if(g.x<e.x)	
			return Direction.BACKWARD;
		else{
			if(g.y < e.y)
				return Direction.RIGHT;
			else{
				
					return Direction.LEFT;
			}
		}
	}}
	
	



public void addChampioninTour(ArrayList<Champion>c , Tournament u){
	for(int i =0; i< c.size();i++){
		u.addChampion(c.get(i));}
	
}

public Tournament getU() {
	return u;
}




}
