package dialogue;

import java.util.Random;

public class Grille 
{
	private Ligne[] lignes = new Ligne[4];
	private int CompteurLose=0;
	private int nbCase;
	private int score=0;
	
	public Grille()
	{
		for(int i=0;i<4;i++)
		{
			lignes[i]=new Ligne();
		}
	}
	
	//Getter
	public Ligne[] getLigne()
	{
		return this.lignes;
	}
	
	public int getNbCase()
	{
		return this.nbCase;
	}
	
	public void afficherGrille()
	{
		for(int i=0;i<4;i++)
		{
			lignes[i].afficherLigne();
			System.out.println("");
		}
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public void changementGrille()
	{
		int randomSpawn=0;
		for(int tour=0;tour<=3;tour++)//Première ligne
		{
			for(int j=1;j<=3;j++)//Examination de la ligne
			{
				
		
				if(this.getLigne()[tour].getCase()[j].getValue()!=1)//On vérifie si la case est à 1 ou pas
				{	
					for(int i=j-1;i>=0;i--)//On regarde la case derrière 
					{
						
						if(this.getLigne()[tour].getCase()[i].getValue()!=1)//Si elle différente de 1
						{
							if(this.getLigne()[tour].getCase()[i].getValue() == this.getLigne()[tour].getCase()[j].getValue())//On vérifier si elle est égale à la case à laquelle j est
							{
								
								this.getLigne()[tour].getCase()[i].setValue(this.getLigne()[tour].getCase()[i].getValue()*2);
								this.score=this.score+this.getLigne()[tour].getCase()[i].getValue();
								this.getLigne()[tour].getCase()[j].setValue(1);
								if(randomSpawn==0)
								{
									randomSpawn();
									randomSpawn++;
								}
							}
							else //Sinon on arrête la boucle (pour éviter de vérifier les cases encore derrière)
							{
								i = -1;
							}
						}
						else //Sinon on remplace cette case à 1 par la valeur de la case sur laquelle J est
						{
							this.getLigne()[tour].getCase()[i].setValue(this.getLigne()[tour].getCase()[j].getValue());
							this.getLigne()[tour].getCase()[j].setValue(1);
							j--; // On fais en sorte que J sois à la même valeur pour revérifier la case qu'il y a derrière
							if(randomSpawn==0)
							{
								randomSpawn();
								randomSpawn++;
							}
						}
					}
				}
			}
		}
	}
	
	public void swapDown()
	{
		Grille temp = new Grille();
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la droite
		{
			for(int i=0;i<=3;i++)
			{
				temp.getLigne()[i].getCase()[x].setValue(this.getLigne()[j].getCase()[i].getValue());
			}
		}
		temp.changementGrille();
		this.score=this.score+temp.getScore();
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la gauche
		{
			for(int i=0;i<=3;i++)
			{
				this.getLigne()[j].getCase()[i].setValue(temp.getLigne()[i].getCase()[x].getValue());
			}
		}
	}
	
	public void swapUp()
	{
		Grille temp = new Grille();
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la gauche
		{
			for(int i=0;i<=3;i++)
			{
				temp.getLigne()[j].getCase()[i].setValue(this.getLigne()[i].getCase()[x].getValue());
			}
		}
		temp.changementGrille();
		this.score=this.score+temp.getScore();
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la droite
		{
			for(int i=0;i<=3;i++)
			{
				this.getLigne()[i].getCase()[x].setValue(temp.getLigne()[j].getCase()[i].getValue());
			}
		}
	}
	
	public void swapRight()
	{
		Grille temp = new Grille();
		Grille temp2 = new Grille();
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la gauche 1
		{
			for(int i=0;i<=3;i++)
			{
				temp.getLigne()[j].getCase()[i].setValue(this.getLigne()[i].getCase()[x].getValue());
			}
		}
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la gauche 2
		{
			for(int i=0;i<=3;i++)
			{
				temp2.getLigne()[j].getCase()[i].setValue(temp.getLigne()[i].getCase()[x].getValue());
			}
		}
		temp2.changementGrille();
		this.score=this.score+temp2.getScore();
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la droite 1
		{
			for(int i=0;i<=3;i++)
			{
				temp.getLigne()[i].getCase()[x].setValue(temp2.getLigne()[j].getCase()[i].getValue());
			}
		}
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la droite 2
		{
			for(int i=0;i<=3;i++)
			{
				this.getLigne()[i].getCase()[x].setValue(temp.getLigne()[j].getCase()[i].getValue());
			}
		}
	}
	
	public void randomSpawn()
	{
		int nbCaseVide=0;
		Random r = new Random();
		Case[] TabCaseVide = new Case[16];
		for(int i=0;i<=3;i++)
		{
			for(int j=0;j<=3;j++)
			{
				if(this.getLigne()[i].getCase()[j].getValue()==1)
				{
					TabCaseVide[nbCaseVide] = this.getLigne()[i].getCase()[j];
					nbCaseVide++;
				}	
			}
		}
		int nbRandom1 = r.nextInt(nbCaseVide - 0);
		TabCaseVide[nbRandom1].setValue(Math.random() < 0.9 ? 2 : 4);
	}
	
	public void compterNbCase()
	{
		this.nbCase=16;
		for(int i=0;i<=3;i++)
		{
			for(int j=0;j<=3;j++)
			{
				if(this.getLigne()[i].getCase()[j].getValue()==1)
				{
					this.nbCase--;
				}	
				else
				{
					this.nbCase++;
				}
			}
		}
	}
	
	/*public int LoseOrNot() {
		//cas gauche
		CompteurLose=0;
		for(int i = 0;i<=3;i++)
		{
			for(int j = 1;j<=3;j++)
			{
				if(this.getLigne()[i].getCase()[j].getValue() != this.getLigne()[i].getCase()[j-1].getValue())
				{
					this.CompteurLose++;
					i=4;
					j=4;
				}
			}
		}
		//cas haut
		Grille temp = new Grille();
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la gauche
		{
			for(int i=0;i<=3;i++)
			{
				temp.getLigne()[j].getCase()[i].setValue(this.getLigne()[i].getCase()[x].getValue());
			}
		}
		for(int i = 0;i<=3;i++)
		{
			for(int j = 1;j<=3;j++)
			{
				if(temp.getLigne()[i].getCase()[j].getValue() != temp.getLigne()[i].getCase()[j-1].getValue())
				{
					CompteurLose++;
					i=4;
					j=4;
				}
			}
		}
		Grille temp2 = new Grille();
		//cas droite
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la gauche
		{
			for(int i=0;i<=3;i++)
			{
				temp2.getLigne()[j].getCase()[i].setValue(temp.getLigne()[i].getCase()[x].getValue());
			}
		}
		for(int i = 0;i<=3;i++)
		{
			for(int j = 1;j<=3;j++)
			{
				if(temp2.getLigne()[i].getCase()[j].getValue() != temp2.getLigne()[i].getCase()[j-1].getValue())
				{
					CompteurLose++;
					i=4;
					j=4;
				}
			}
		}
		Grille temp3 = new Grille();
		temp3=temp2;
		//cas Bas
		for(int j=0, x=3;j<=3;j++,x--) //Tourner la grille vers la gauche
		{
			for(int i=0;i<=3;i++)
			{
				temp.getLigne()[j].getCase()[i].setValue(this.getLigne()[i].getCase()[x].getValue());
			}
		}
		for(int i = 0;i<=3;i++)
		{
			for(int j = 1;j<=3;j++)
			{
				if(temp.getLigne()[i].getCase()[j].getValue() != temp.getLigne()[i].getCase()[j-1].getValue())
				{
					CompteurLose++;
					i=4;
					j=4;
				}
			}
		}
		return CompteurLose;
	}// Verification que deux case adjacente ne sont pas égale*/
	
	public boolean gameover()
	{
		Grille temp = new Grille();
		int compteur=0;
		for (int i=0;i<=3;i++)//Recopiage de la grille actuelle
		{
			for (int j=0;j<=3;j++)
			{
				temp.getLigne()[i].getCase()[j].setValue(this.getLigne()[i].getCase()[j].getValue());
			}
		}
		temp.changementGrille();//Vérification à gauche
		for (int i=0;i<=3;i++)
		{
			for (int j=1;j<=3;j++)
			{
				if(temp.getLigne()[i].getCase()[j].getValue()==this.getLigne()[i].getCase()[j].getValue())
				{
					compteur++;
				}
				if(compteur==12)
				{
					this.CompteurLose++;
				}
			}
		}
		compteur=0;
		temp.swapRight();//Verification à droite
		for (int i=0;i<=3;i++)
		{
			for (int j=1;j<=3;j++)
			{
				if(temp.getLigne()[i].getCase()[j].getValue()==this.getLigne()[i].getCase()[j].getValue())
				{
					compteur++;
				}
				if(compteur==12)
				{
					this.CompteurLose++;
				}
			}
		}
		compteur=0;
		temp.swapDown();//Vérification en bas
		for (int i=0;i<=3;i++)
		{
			for (int j=1;j<=3;j++)
			{
				if(temp.getLigne()[i].getCase()[j].getValue()==this.getLigne()[i].getCase()[j].getValue())
				{
					compteur++;
				}
				if(compteur==12)
				{
					this.CompteurLose++;
				}
			}
		}
		compteur=0;
		temp.swapUp();//Vérification en haut
		for (int i=0;i<=3;i++)
		{
			for (int j=1;j<=3;j++)
			{
				if(temp.getLigne()[i].getCase()[j].getValue()==this.getLigne()[i].getCase()[j].getValue())
				{
					compteur++;
				}
				if(compteur==12)
				{
					this.CompteurLose++;
				}
			}
		}
		if(CompteurLose==4)
		{
			return true;
		}
		else
		{
			this.CompteurLose=0;
			return false;
		}
		
	}
	public int maxValue()
	{
		int max=0;
		for(int i=0;i<=3;i++)
		{
			for(int j=0;j<=3;j++)
			{
				if(this.getLigne()[i].getCase()[j].getValue()>max)
				{
					max=this.getLigne()[i].getCase()[j].getValue();
				}
			}
		}
		return max;
	}
}
