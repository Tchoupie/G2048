package main;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import dialogue.*;

public class main extends JPanel
{
	private static final Color BG_COLOR = new Color(0xbbada0);
	private static final String FONT_NAME = "Arial";
	private static final int TILE_SIZE = 64;
	private static final int TILES_MARGIN = 16;
	private static Jeu jeu = new Jeu();
	private static JLabel casee = new JLabel();
	private static JLabel[] tabcase = new JLabel[15];
	private static JPanel panel = new JPanel();
	private static JFrame game = new JFrame();
	
	
	
	public static void main(String[] args) 
	{
	    game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    game.setSize(340, 400);
	    game.setResizable(false);
	    panel.setLayout(new GridLayout(6, 5));
	    game.addKeyListener(gameKeyAdapter());
	    game.setContentPane(MainPanel());
	    game.repaint();
		game.setVisible(true);
		casee.setOpaque(true);
		jeu.compterNbCase();
		     
		   
	    
	        
	}
	private static KeyAdapter gameKeyAdapter()
	 {
	  return new KeyAdapter()
	  {
	   @Override
	   public void keyReleased(KeyEvent e)
	   {
		   switch (e.getKeyCode()) {
	         case KeyEvent.VK_LEFT:
	              jeu.changementGrille();
	              panel.removeAll();
	              game.setContentPane(RefreshPanel());
	              if(jeu.getNbCase()/2==16)
	              {
	            	 if(jeu.gameover())
	            	 {
	            		 game.setTitle("Perdu !");
	            		 panel.removeAll();
	   	              	 game.setContentPane(LosePanel());
	            	 }
	              }
	              if(jeu.maxValue()==2048)
	              {
	            	  game.setTitle("Gagné !");
	            	  panel.removeAll();
	   	              game.setContentPane(WinPanel());
	              }
	         break;
	         case KeyEvent.VK_RIGHT:
	              jeu.swapRight();
	              panel.removeAll();
	              game.setContentPane(RefreshPanel());
	              if(jeu.getNbCase()/2==16)
	              {
	            	 if(jeu.gameover())
	            	 {
	            		 game.setTitle("Perdu !");
	            		 panel.removeAll();
	   	              	 game.setContentPane(LosePanel());
	            	 }
	              }
	              if(jeu.maxValue()==2048)
	              {
	            	  game.setTitle("Gagné !");
	            	  panel.removeAll();
	   	              game.setContentPane(WinPanel());
	              }
	         break;
	         case KeyEvent.VK_DOWN:
	              jeu.swapDown();
	              panel.removeAll();
	              game.setContentPane(RefreshPanel());
	              if(jeu.getNbCase()/2==16)
	              {
	            	 if(jeu.gameover())
	            	 {
	            		 game.setTitle("Perdu !");
	            		 panel.removeAll();
	   	              	 game.setContentPane(LosePanel());
	            	 }
	              }
	              if(jeu.maxValue()==2048)
	              {
	            	  game.setTitle("Gagné !");
	            	  panel.removeAll();
	   	              game.setContentPane(WinPanel());
	              }
	         break;
	         case KeyEvent.VK_UP:
	              jeu.swapUp();
	              panel.removeAll();
	              game.setContentPane(RefreshPanel());
	              if(jeu.getNbCase()/2==16)
	              {
	            	 if(jeu.gameover())
	            	 {
	            		 game.setTitle("Perdu !");
	            		 panel.removeAll();
	   	              	 game.setContentPane(LosePanel());
	            	 }
	              }
	              if(jeu.maxValue()==2048)
	              {
	            	  game.setTitle("Gagné !");
	            	  panel.removeAll();
	   	              game.setContentPane(WinPanel());
	              }
	         break;
	    }
	   }
	  };
	 }
	
	private static Container MainPanel() {
		jeu.randomSpawn();
		jeu.randomSpawn();
		jeu.compterNbCase();
		for(int i=0;i<=3;i++)
		{
			for(int j=0;j<=3;j++)
			{
				int value = jeu.getLigne()[i].getCase()[j].getValue();
				if(value != 1)
				{	
					JLabel lb2 = new JLabel("<html><h1>" + value + "</h1></html>",JLabel.CENTER);
					lb2.setOpaque(true);
					lb2.setBackground(getBackground(value));
					panel.add(lb2);
				}
				else
				{	
					JLabel lb = new JLabel("   ",JLabel.CENTER);
					lb.setOpaque(true);
					lb.setBackground(getBackground(value));
					panel.add(lb);
				}
			}
		}
		panel.add(new JLabel("<html><h1>Score :</h1><html>"));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		return panel;
	}
	private static Container RefreshPanel() {
		jeu.compterNbCase();
		for(int i=0;i<=3;i++)
		{
			for(int j=0;j<=3;j++)
			{
				int value = jeu.getLigne()[i].getCase()[j].getValue();
				if(value != 1)
				{	
					JLabel lb2 = new JLabel("<html><h1>" + value + "</h1></html>",JLabel.CENTER);
					lb2.setOpaque(true);
					lb2.setBackground(getBackground(value));
					panel.add(lb2);
				}
				else
				{	
					JLabel lb = new JLabel("   ",JLabel.CENTER);
					lb.setOpaque(true);
					lb.setBackground(getBackground(value));
					panel.add(lb);
				}
			}
		}
		panel.add(new JLabel("<html><h1>Score :</h1><html>"));
		panel.add(new JLabel("<html><h1>"+jeu.getScore()+"</h1></html>"));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		return panel;
	}
	
	private static Container LosePanel() {
		jeu.compterNbCase();
		for(int i=0;i<=3;i++)
		{
			for(int j=0;j<=3;j++)
			{
				int value = jeu.getLigne()[i].getCase()[j].getValue();
				if(value != 1)
				{	
					JLabel lb2 = new JLabel("<html><h1>" + value + "</h1></html>",JLabel.CENTER);
					lb2.setOpaque(true);
					lb2.setBackground(getBackground(value));
					panel.add(lb2);
				}
				else
				{	
					JLabel lb = new JLabel("   ",JLabel.CENTER);
					lb.setOpaque(true);
					lb.setBackground(getBackground(value));
					panel.add(lb);
				}
			}
		}
		panel.add(new JLabel("<html><h1>Score :</h1><html>"));
		panel.add(new JLabel("<html><h1>"+jeu.getScore()+"</h1></html>"));
		panel.add(new JLabel("<html><h1>Perdu !</h1></html>"));
		panel.add(new JLabel(" "));
		return panel;
	}
	private static Container WinPanel() {
		jeu.compterNbCase();
		for(int i=0;i<=3;i++)
		{
			for(int j=0;j<=3;j++)
			{
				int value = jeu.getLigne()[i].getCase()[j].getValue();
				if(value != 1)
				{	
					JLabel lb2 = new JLabel("<html><h1>" + value + "</h1></html>",JLabel.CENTER);
					lb2.setOpaque(true);
					lb2.setBackground(getBackground(value));
					panel.add(lb2);
				}
				else
				{	
					JLabel lb = new JLabel("   ",JLabel.CENTER);
					lb.setOpaque(true);
					lb.setBackground(getBackground(value));
					panel.add(lb);
				}
			}
		}
		panel.add(new JLabel("<html><h1>Score :</h1><html>"));
		panel.add(new JLabel("<html><h1>"+jeu.getScore()+"</h1></html>"));
		panel.add(new JLabel("<html><h1>Gagné !</h1></html>"));
		panel.add(new JLabel(" "));
		return panel;
	}
	
	 public static Color getBackground(int value) {
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
