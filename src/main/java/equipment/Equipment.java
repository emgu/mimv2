package equipment;

import java.util.AbstractMap;
import java.util.Set;
import java.util.TreeMap;

import equipment.Obj;
import iohandling.IO;

public class Equipment<K, V> extends TreeMap<K, V>{
	private static final long serialVersionUID = 1L;
	int equMax;
	
	public Equipment(int size){
		super();
		equMax = size;
	}
	
	public V put(K objType, V o){
		if(super.size() < equMax)
			return super.put(objType, o);
		return null;
	}
	
	public int strengthBoost(K k) {
		if(super.containsKey(k)){	
			Obj obj = (Obj) super.get(k);
			//IO.display("You get " + obj.getStrengthBoost() + " from " + obj.getClass().getSimpleName());
			return obj.getStrengthBoost();
		}
	return 0;
	}
	public int craftBoost(K k) {
		if(super.containsKey(k)){	
			Obj obj = (Obj) super.get(k);
		//	IO.display("You get " + obj.getCraftBoost() + " from " + obj.getClass().getSimpleName());
			return obj.getCraftBoost();
		}
	return 0;
	}
	
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public String printStrengthBoost(K k) {
		int boost = strengthBoost(k);
		if(boost != 0){
			return "(+" + boost + " from " + super.get(k).getClass().getSimpleName() + ")";
		}
		return "";
	}
	public String printCraftBoost(K k) {
		int boost = craftBoost(k);
		if(boost != 0){
			return "(+" + boost + " from " + super.get(k).getClass().getSimpleName() + ")";
		}
		return "";
	}
	public String printObj(K k){
		if(super.containsKey(k)){
			switch((String) k){
				case "Weapon" : return "Weapon: " + super.get(k).getClass().getSimpleName();
				case "Armour" : return "Armour: " + super.get(k).getClass().getSimpleName();
				case "Shield" : return "Shield: " + super.get(k).getClass().getSimpleName();
				case "Helmet" : return "Helmet: " + super.get(k).getClass().getSimpleName();
				default : return "";
			}
		} else return "no " + k;
	}

	public int protectionBoost(K k) {
			if(super.containsKey(k)){	
				Obj obj = (Obj) super.get(k);
				//IO.display("You get " + obj.getStrengthBoost() + " from " + obj.getClass().getSimpleName());
				return obj.getStrengthBoost();
			}
		return 0;
	}
}
