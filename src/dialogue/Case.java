package dialogue;

import java.awt.Color;

public class Case 
{
	private int value;
	
	public Case()
	{
		this.value=1;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public void setValue(int value)
	{
		this.value=value;
	}
	
	public void afficherCase()
	{
		System.out.print(this.value);
	}

	public Color getBackground(int value) {
	      switch (value) {
	        case 2:    return new Color(0xeee4da);
	        case 4:    return new Color(0xede0c8);
	        case 8:    return new Color(0xf2b179);
	        case 16:   return new Color(0xf59563);
	        case 32:   return new Color(0xf67c5f);
	        case 64:   return new Color(0xf65e3b);
	        case 128:  return new Color(0xedcf72);
	        case 256:  return new Color(0xedcc61);
	        case 512:  return new Color(0xedc850);
	        case 1024: return new Color(0xedc53f);
	        case 2048: return new Color(0xedc22e);
	      }
	      return new Color(0xcdc1b4);
	 }
}
