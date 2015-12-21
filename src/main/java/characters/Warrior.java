package characters;

public class Warrior extends Character{

	Warrior(){
		super();
		this.strength += 1;
		this.inteligence -= 1;
		this.startPosition = 0; // Tawern
		this.profession = "Warrior";
	}
	
}
