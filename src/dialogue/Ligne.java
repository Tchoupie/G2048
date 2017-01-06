package dialogue;

public class Ligne 
{
	private Case[] cases = new Case[4];
	
	public Ligne()
	{
		for(int i=0;i<4;i++)
		{
			cases[i]=new Case();
			cases[i].setValue(1);
		}
	}

	//Getteur
	public Case[] getCase()
	{
		return this.cases;
	}
	public void afficherLigne()
	{
		for(int i=0;i<4;i++)
		{
			cases[i].afficherCase();
			System.out.print(" ");
		}
	}
}
