package characters;

public class Wizard extends Character{
		
	Wizard(){
		super();
		this.strength -= 1;
		this.inteligence += 1;
		this.startPosition = 6; // Town
		this.profession = "Wizard";
	}
}
