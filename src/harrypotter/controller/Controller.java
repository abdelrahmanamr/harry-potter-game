package harrypotter.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.jws.Oneway;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.ThirdTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Direction;
import harrypotter.view.Application;
import harrypotter.view.ApplicationListener;

public class Controller extends JFrame implements ActionListener, KeyListener {

	Timer t = new Timer(5, this);
	private Application app;

	public Controller() {
		this.app = new Application();
		t.start();
		app.addKeyListener(this);
		app.setFocusable(true);
		app.setFocusTraversalKeysEnabled(false);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.repaint();
		app.revalidate();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			try {
				ArrayList<Point> mc = new ArrayList<Point>();
				if(app.getU().getTheTask() instanceof FirstTask){
					app.getU().getFirstTask().setOldmarkedCells(app.getU().getFirstTask().getMarkedCells());
				}
						(this.app.getU()).getTheTask().moveForward();
				        this.app.tournament(
						this.app.getU().getTheTask().getCurrentChamp(), app.getU());
				       
				this.repaint();
				this.revalidate();
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		if(code == KeyEvent.VK_P){
			
			Wizard current = (Wizard)this.app.getU().getTheTask().getCurrentChamp();
			ArrayList<Collectible> inv = new ArrayList<Collectible>();
			inv = current.getInventory();
			
			String[]x= LoadPotionNames(inv);
			JComboBox d = new JComboBox();;
			d = app.addinCombo(x);
			JOptionPane.showMessageDialog( null, d, "select or type a value", JOptionPane.QUESTION_MESSAGE);
			String y =(String) d.getSelectedItem();
			
			Potion p = getTheSelectedPotion(y,inv);
	
			if(p!=null)
			this.app.getU().getTheTask().usePotion(p);
			try {
				this.app.tournament(
						this.app.getU().getTheTask().getCurrentChamp(), app.getU());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			}
		
		if (code == KeyEvent.VK_DOWN) {
			try {
				ArrayList<Point> mc = new ArrayList<Point>();
				if(app.getU().getTheTask() instanceof FirstTask){
					app.getU().getFirstTask().setOldmarkedCells(app.getU().getFirstTask().getMarkedCells());
				}
				(this.app.getU()).getTheTask().moveBackward();
				this.app.tournament(
						this.app.getU().getTheTask().getCurrentChamp(), app.getU());
				this.repaint();
				this.revalidate();
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		if (code == KeyEvent.VK_LEFT) {
			try { 
				ArrayList<Point> mc = new ArrayList<Point>();
				if(app.getU().getTheTask() instanceof FirstTask){
				app.getU().getFirstTask().setOldmarkedCells(app.getU().getFirstTask().getMarkedCells());
				}
				(this.app.getU()).getTheTask().moveLeft();
				this.app.tournament(
						this.app.getU().getTheTask().getCurrentChamp(), app.getU());

				this.repaint();
				this.revalidate();
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		if (code == KeyEvent.VK_RIGHT) {
			try {
				ArrayList<Point> mc = new ArrayList<Point>();
				if(app.getU().getTheTask() instanceof FirstTask){
					app.getU().getFirstTask().setOldmarkedCells(app.getU().getFirstTask().getMarkedCells());
				}
				(this.app.getU()).getTheTask().moveRight();
				this.app.tournament(this.app.getU().getTheTask().getCurrentChamp(), app.getU());
						//this.app.repaint();
						//this.app.revalidate();;
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		if (code == KeyEvent.VK_D) {
			Wizard current = (Wizard) this.app.getU().getTheTask()
					.getCurrentChamp();
			ArrayList<Spell> s = new ArrayList<Spell>();
			s = current.getSpells();
			
				//this.app.tournament(this.app.getU().getTheTask().getChampions(), app.getU());
				app.repaint();
				app.revalidate();
			//} catch (IOException e2) {
				// TODO Auto-generated catch block
				//e2.printStackTrace();
			
			for (int i = 0; i < s.size(); i++) {
				Spell ss = s.get(i);

				if (ss instanceof DamagingSpell) {
					
					String y = JOptionPane.showInputDialog(null,"Please enter the required direction to cast the spell :(UP,DOWN,LEFT,RIGHT)");
                    if(y==null){
                    	JOptionPane.showMessageDialog(null, "Invalid input");
                    }else{
					Direction d = reqDir(y);
					if(d!=null){
					try { 
						ArrayList<Point> mc = new ArrayList<Point>();
						if(app.getU().getTheTask() instanceof FirstTask){
							mc = app.getU().getFirstTask().getMarkedCells();
						}
						this.app.getU().getTheTask()
								.castDamagingSpell((DamagingSpell) ss, d);
						this.app.tournament(this.app.getU().getTheTask().getCurrentChamp(),app.getU());
					} catch (InCooldownException e1) {

						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (NotEnoughIPException e1) {

						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (OutOfBordersException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (InvalidTargetCellException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
				}
					break;
				}}
			}
			}
		if(code == KeyEvent.VK_H){
			Wizard current = (Wizard) this.app.getU().getTheTask()
					.getCurrentChamp();
			ArrayList<Spell> s = new ArrayList<Spell>();
			s = current.getSpells();
			for(int i = 0;i<s.size();i++){
				Spell ss = s.get(i);
				if(ss instanceof HealingSpell){
					try {
						ArrayList<Point> mc = new ArrayList<Point>();
						if(app.getU().getTheTask() instanceof FirstTask){
							mc = app.getU().getFirstTask().getMarkedCells();
						}
						this.app.getU().getTheTask().castHealingSpell((HealingSpell)ss);
						this.app.tournament(this.app.getU().getTheTask().getCurrentChamp(),app.getU());
						this.repaint();
						this.revalidate();
					} catch (InCooldownException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (NotEnoughIPException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
					break;
				}
			}
		}
		
		if(code == KeyEvent.VK_R){
			Wizard current = (Wizard)app.getU().getTheTask().getCurrentChamp();
			ArrayList<Spell> s = new ArrayList<Spell>();
			s = current.getSpells();
			for(int i = 0;i<s.size();i++){
		    Spell ss = s.get(i);
		    if(ss instanceof RelocatingSpell){
		    	String x = JOptionPane.showInputDialog(null,"Please enter the direction of current location of the relocated object: (UP,DOWN,LEFT,RIGHT)") ;
		    	String y = JOptionPane.showInputDialog(null,"Please enter the dircetion of relocated location you would like to relocate: (UP,DOWN,LEFT,RIGHT)");
		    	String z = JOptionPane.showInputDialog(null,"Please enter the range for the required position: (ex.:1,2..)");
		    	if(x==null || y==null || z==null){
		    		JOptionPane.showMessageDialog(null, "Invalid input");
		    	}else{
		    	Direction d = reqDir(x);
		    	Direction t = reqDir(y);
		    	int r = Integer.parseInt(z);
		    	if(d!=null&&t!=null){
		    	try {
		    		ArrayList<Point> mc = new ArrayList<Point>();
					if(app.getU().getTheTask() instanceof FirstTask){
						mc = app.getU().getFirstTask().getMarkedCells();
					}
					app.getU().getTheTask().castRelocatingSpell((RelocatingSpell)ss, d, t, r);
					this.app.tournament(this.app.getU().getTheTask().getCurrentChamp(),app.getU());
					this.repaint();
					this.revalidate();
				} catch (InCooldownException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				} catch (NotEnoughIPException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				} catch (OutOfBordersException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				} catch (InvalidTargetCellException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				} catch (OutOfRangeException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
		    }
		    	break;
			}}
			}
		}
		if(code == KeyEvent.VK_T){
			Wizard current = (Wizard)app.getU().getTheTask().getCurrentChamp();
			
			if(current instanceof SlytherinWizard){
				String a = JOptionPane.showInputDialog(null,"Please enter the trait direction");
				if(a==null){
					JOptionPane.showMessageDialog(null, "Invalid input");
				}else{
				Direction d = reqDir(a);
				((SlytherinWizard)current).setTraitDirection(d);
				try {
					if(d!=null){
					((SlytherinWizard)current).useTrait();
					this.app.tournament(this.app.getU().getTheTask().getCurrentChamp(), app.getU());
					app.repaint();
					app.revalidate();
				}
				}catch (InCooldownException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				} catch (OutOfBordersException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				} catch (InvalidTargetCellException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
			}}
			
			if(current instanceof GryffindorWizard){
			  
				  
				  
				try {
				((GryffindorWizard) current).useTrait();
				this.app.tournament(this.app.getU().getTheTask().getCurrentChamp(), app.getU());
				if(current.getName().equalsIgnoreCase("batman")){
				imBatman();}else
					Loai();
			} catch (InCooldownException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			}
			if(current instanceof HufflepuffWizard ){
				try {
					((HufflepuffWizard)current).useTrait();
					this.app.tournament(this.app.getU().getTheTask().getCurrentChamp(), app.getU());
				} catch (InCooldownException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
			}
			if(current instanceof RavenclawWizard){
				if(app.getU().getTheTask() instanceof FirstTask){
					ArrayList<Point> markedCell = new ArrayList<Point>();
					try {
						markedCell = (ArrayList<Point>)app.getU().getFirstTask().onRavenclawTrait() ;
						Mortada();
						try {
							app.tournament(app.getU().getTheTask().getCurrentChamp(), app.getU());
						} catch (IOException e2) {
							JOptionPane.showMessageDialog(null,e2.getMessage());
						
						}
							app.repaint();
							app.revalidate();
						
					} catch (InCooldownException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
				}
				if(app.getU().getTheTask() instanceof SecondTask){
					try {
						ArrayList<Direction> d = (ArrayList<Direction>)app.getU().getSecondTask().onRavenclawTrait();
						Mortada();
						JOptionPane.showMessageDialog(null, d);
						
						try {
							this.app.tournament(this.app.getU().getSecondTask().getCurrentChamp(),app.getU());
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null,e1.getMessage());
						}
					} catch (InCooldownException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
				}
				if(app.getU().getTheTask() instanceof ThirdTask){
					try {
						ArrayList<Direction> d = (ArrayList<Direction>)app.getU().getThirdTask().onRavenclawTrait();
						Mortada();
						JOptionPane.showMessageDialog(null, d);
						try {
							this.app.tournament(this.app.getU().getThirdTask().getCurrentChamp(), app.getU());
							app.repaint();
							app.revalidate();
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null,e1.getMessage());
						}
					} catch (InCooldownException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage());;
					}
				}
			}}
				
			}
			
     
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		revalidate();

	}

	public static void main(String[] args) {
		Controller c1 = new Controller();
		Application c = c1.app;

		c.setVisible(true);
	}

	public static Direction reqDir(String y) {
		String x = y.toUpperCase();
		switch (x) {
		case "UP":
			return Direction.FORWARD;
		case "DOWN":
			return Direction.BACKWARD;
		case "LEFT":
			return Direction.LEFT;
		case "RIGHT":
			return Direction.RIGHT;
		default:
			return null;
		}
	}
	public String[] LoadPotionNames(ArrayList<Collectible>generalPotion){
		String[]x = new String[generalPotion.size()];
		for(int i =0; i<generalPotion.size();i++){
			x[i]=generalPotion.get(i).getName();
		
	}return x;

}
	public Potion getTheSelectedPotion(String y , ArrayList<Collectible> inv){
		for(int i =0; i<inv.size();i++){
			Potion p = (Potion) inv.get(i);
			if(y.equals(inv.get(i).getName())){
				return p;
			}
		}
		return null;
	}public static void imBatman(){
		try {
		    File yourFile;
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;
	        yourFile = new File("src/im batman.wav");
		    stream = AudioSystem.getAudioInputStream(yourFile);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		    
		}
		catch (Exception e) {
		    
		}
	
	
}public static void Mortada(){
	try {
	    File yourFile;
	    AudioInputStream stream;
	    AudioFormat format;
	    DataLine.Info info;
	    Clip clip;
        yourFile = new File("src/Mortada.wav");
	    stream = AudioSystem.getAudioInputStream(yourFile);
	    format = stream.getFormat();
	    info = new DataLine.Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	    
	}
	catch (Exception e) {
	    
	}


}
public static void Loai(){
	try {
	    File yourFile;
	    AudioInputStream stream;
	    AudioFormat format;
	    DataLine.Info info;
	    Clip clip;
        yourFile = new File("src/Tournament.wav");
	    stream = AudioSystem.getAudioInputStream(yourFile);
	    format = stream.getFormat();
	    info = new DataLine.Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	    
	}
	catch (Exception e) {
	    
	}


}

}
