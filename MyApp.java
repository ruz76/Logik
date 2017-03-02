import java.awt.*;
import java.applet.*;
import java.util.*;

class Puntik {
	int px;
	int py;
	int psize;
	Color pclr;
	Puntik(int x, int y, int size, Color clr) {
	  	px = x;
	  	py = y;
	 	psize = size;
		pclr = clr;
	}
	public void display(Graphics g){
			Color pom;
			pom = g.getColor();
			g.setColor(pclr);
			g.fillOval(px, py, psize, psize);
			g.setColor(pom);
	}
}


public class MyApp extends Applet
{
	int sx;
	int sy;
	TextField text_field = new TextField(20);
	Puntik mujPuntik[] = new Puntik[24];
	Rectangle mujRect[] = new Rectangle[24];
	Puntik mujMalyPuntik[] = new Puntik[24];
	Rectangle mujMalyRect[] = new Rectangle[24];
	Rectangle mujBarva[] = new Rectangle[6];
	Color Barva[] = new Color[6];
	int ActBarva = 0;
	int Radek = 0;
	Puntik HadanyPuntik[] = new Puntik[4];
	Rectangle Reseni = new Rectangle(15, 260, 160, 40);
	int Konec = 0;
		
	public void init() {
		//add(text_field);
		
		for (int i = 0 ; i < mujPuntik.length ; i++) {
			mujPuntik[i] = new Puntik(0, 0, 10, Color.darkGray);
			mujRect[i] = new Rectangle(0, 0, 10, 10);
			mujMalyPuntik[i] = new Puntik(0, 0, 3, Color.lightGray);
			mujMalyRect[i] = new Rectangle(0, 0, 10, 10);
		}
		
		for (int i = 0 ; i < mujBarva.length ; i++) {
			mujBarva[i] = new Rectangle(265, i * 25 + 30, 20, 20);
		}
					
		Barva[0] = new Color(0, 0, 255);
		Barva[1] = new Color(0, 0, 0);
		Barva[2] = new Color(255, 255, 0);
		Barva[3] = new Color(255, 0, 0);
		Barva[4] = new Color(255, 255, 255);
		Barva[5] = new Color(0, 255, 0);
				
		for (int i = 0 ; i < 6 ; i++) { 
			for (int j = 0 ; j < 4 ; j++) {
		     mujPuntik[i * 4 + j].px = j * 40 + 30;
		     mujPuntik[i * 4 + j].py = i * 40 + 30;
			  mujPuntik[i * 4 + j].psize = 10;
			  mujRect[i * 4 + j].x = j * 40 + 15;
		     mujRect[i * 4 + j].y = i * 40 + 15;
			  mujRect[i * 4 + j].width = 40;
  			  mujRect[i * 4 + j].height = 40;
  		     mujMalyPuntik[i * 4 + j].px = j * 15 + 185;
		     mujMalyPuntik[i * 4 + j].py = i * 40 + 15;
			  mujMalyPuntik[i * 4 + j].psize = 10;
			  mujMalyRect[i * 4 + j].x = j * 15 + 185;
		     mujMalyRect[i * 4 + j].y = i * 40 + 15;
			  mujMalyRect[i * 4 + j].width = 10;
  			  mujMalyRect[i * 4 + j].height = 10;

			}
	   }
	   
	   for (int i = 0 ; i < HadanyPuntik.length ; i++) {
			HadanyPuntik[i] = new Puntik(i * 40 + 20, 265, 30, Color.darkGray);
		}
	   Cupliky();
	}
	
	public void paint(Graphics g)
	{
		//g.drawString("Hello World", 20, 20);
		//g.fillOval(sx, sy, sx + sy * 2, sy + sx * 2);
	   for (int i = 0 ; i < mujPuntik.length ; i++) {
			mujPuntik[i].display(g);
			g.drawRect(mujRect[i].x, mujRect[i].y, mujRect[i].width, mujRect[i].height);
			mujMalyPuntik[i].display(g);
			g.drawRect(mujMalyRect[i].x, mujMalyRect[i].y, mujMalyRect[i].width, mujMalyRect[i].height);
		}
		for (int i = 0 ; i < mujBarva.length ; i++) {
			g.setColor(Color.magenta);
			if (i == ActBarva) g.drawRect(mujBarva[i].x - 2, mujBarva[i].y - 2, mujBarva[i].width + 4, mujBarva[i].height + 4);
			if (i == ActBarva) g.drawRect(mujBarva[i].x - 3, mujBarva[i].y - 3, mujBarva[i].width + 5, mujBarva[i].height + 5);
			g.setColor(Barva[i]);
			g.fillRect(mujBarva[i].x, mujBarva[i].y, mujBarva[i].width, mujBarva[i].height);
		}
		
		g.setColor(Color.black);
		g.drawRect(Reseni.x, Reseni.y, Reseni.width, Reseni.height);
		if (Konec == 1) {
			for (int i = 0 ; i < HadanyPuntik.length ; i++) {
				HadanyPuntik[i].display(g);
			}
		} else {
			g.drawString("Zobrazit reseni", 45, 285); 
		}

	}
	
	
	public boolean mouseDown(Event eve, int x, int y) {
		/*mujPuntik.px = x;
		mujPuntik.py = y;*/
		for (int i = 0 ; i < mujPuntik.length ; i++) {
			if (mujRect[i].inside(x, y)) {
				System.out.println("tak si ho trefil " + i + ".");
				if (mujPuntik[i].psize != 30) {			
					mujPuntik[i].px = mujPuntik[i].px - 10;
   	         mujPuntik[i].py = mujPuntik[i].py - 10;
					mujPuntik[i].psize = 30;
				}	
				mujPuntik[i].pclr = Barva[ActBarva];
			}
		}
		
		for (int i = Radek * 4 ; i < Radek * 4 + 4 ; i++) {
			if (mujMalyRect[i].inside(x, y)) {
				Srovnani();
			}
		}
		
		for (int i = 0 ; i < mujBarva.length ; i++) {
			if (mujBarva[i].inside(x, y)) {
				System.out.println("barva " + i + ".");
				ActBarva = i;			
			}
		}

		if (Reseni.inside(x, y)) Konec = 1;

	   repaint();
		return true;
	}

	public boolean mouseMove(Event eve, int x, int y) {
		sx = x;
		sy = y;
		//repaint();
		return true;
	}
	
	public void Cupliky() {
		int a, b, c, d;
		double pom; 
		pom = 6 * Math.random();
		a = (int) pom;
		
		pom = 6 * Math.random();
		b = (int) pom;
		while (a == b) {
			pom = 6 * Math.random();
			b = (int) pom;
		}
		
		pom = 6 * Math.random();
		c = (int) pom;
		while ((a == c) || (b == c))  {
			pom = 6 * Math.random();
			c = (int) pom;
		}
		
		pom = 6 * Math.random();
		d = (int) pom;
		while ((a == d) || (b == d) || (c == d))  {
			pom = 6 * Math.random();
			d = (int) pom;
		}
		
		HadanyPuntik[0].pclr = Barva[a];
		HadanyPuntik[1].pclr = Barva[b];
		HadanyPuntik[2].pclr = Barva[c];
		HadanyPuntik[3].pclr = Barva[d];
	}
	
	public void Srovnani() {
		int gPossition = 0;
		int gColor = 0;
		
		for (int a = 0 ; a < 4 ; a++) {
				if (mujPuntik[Radek * 4 + a].pclr == HadanyPuntik[a].pclr) {
					gPossition++; 
				} else {
					if ((mujPuntik[Radek * 4 + a].pclr == HadanyPuntik[0].pclr) || (mujPuntik[Radek * 4 + a].pclr == HadanyPuntik[1].pclr) 
					|| (mujPuntik[Radek * 4 + a].pclr == HadanyPuntik[2].pclr) || (mujPuntik[Radek * 4 + a].pclr == HadanyPuntik[3].pclr))
					gColor++; 
				}
		}
				
		for (int a = 0 ; a < gPossition; a++) {
			mujMalyPuntik[Radek * 4 + a].pclr = Barva[4];
		}
	
		for (int a = gPossition ; a < gPossition + gColor ; a++) {
			int c = Radek * 4 + a;
			System.out.println("tak " + c + ".");
			mujMalyPuntik[Radek * 4 + a].pclr = Barva[1];
		}
		
		Radek++;
		
		if (gPossition==4) Konec = 1;	
	}
}	