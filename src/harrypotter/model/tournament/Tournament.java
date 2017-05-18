package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.view.Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tournament implements TaskListener {

	private ArrayList<Champion> champions;
	private ArrayList<Spell> spells;
	private FirstTask firstTask;
	private SecondTask secondTask;
	private ThirdTask thirdTask;
    private Task theTask;
    
	public Task getTheTask() {
		return theTask;
	}

	public void setTheTask(Task theTask) {
		this.theTask = theTask;
	}

	public Tournament() throws IOException {

		champions = new ArrayList<Champion>();
		spells = new ArrayList<Spell>();
		loadSpells("Spells.csv");

	}

	public ArrayList<Champion> getChampions() {
		return champions;
	}

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public FirstTask getFirstTask() {
		return firstTask;
	}

	public SecondTask getSecondTask() {
		return secondTask;
	}

	public ThirdTask getThirdTask() {
		return thirdTask;
	}
   
    
	public void addChampion(Champion c) {
		champions.add(c);
	}

	private void loadSpells(String filePath) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {

			String[] content = line.split(",");
			switch (content[0]) {

			case "DMG":
				spells.add(new DamagingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			case "HEL":
				spells.add(new HealingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			case "REL":
				spells.add(new RelocatingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			}

			line = br.readLine();

		}

		br.close();

	}

	public void beginTournament() throws IOException {

		firstTask = new FirstTask(champions);
		firstTask.setListener(this);
		setTheTask(firstTask);

	}

	public void onFinishingFirstTask(ArrayList<Champion> winners)
			throws IOException {

		if (!winners.isEmpty()) {
			secondTask = new SecondTask(winners);
			secondTask.setListener(this);
			setTheTask(secondTask);
		}

	}

	public void onFinishingSecondTask(ArrayList<Champion> winners)
			throws IOException {

		if (!winners.isEmpty()) {
			thirdTask = new ThirdTask(winners);
			thirdTask.setListener(this);
			setTheTask(thirdTask);
		}

	}

	public void onFinishingThirdTask(Champion winner) {
         ClappingEffect();
		 JOptionPane.showMessageDialog(null, "CONGRATULATIONS! "+ ((Wizard)winner).getName() +" has won the TOURNAMENT!");
		 System.exit(0);

	}
	public static void ClappingEffect(){
		try {
		    File yourFile;
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;
	        yourFile = new File("src/Clapping.wav");
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