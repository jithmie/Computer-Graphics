import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class drawing extends JFrame implements MouseListener{
	Graphics g;
	int x1,y1,d,h;
	double xarr[]=new double[6];
	double yarr[]=new double[6];
	Color pixel[][]=new Color[800][800];
	
	drawing(){
		setTitle("DDA Algorithm");
		setSize(800,800);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		setLocationRelativeTo(null);
		setVisible(true);
		g=getGraphics();
	}
	public void DDA(int x1,int y1,int x2,int y2){
		int dx,dy;
		double steps,xinc,yinc,x,y;
		dx=x2-x1;
		dy=y2-y1;
		
		if(Math.abs(dx)>Math.abs(dy)){
			steps=Math.abs(dx);
		}
		else{
			steps=Math.abs(dy);
		}
		xinc=dx/steps;
		yinc=dy/steps;
		
		x=x1;
		y=y1;
		
		g.drawLine((int)Math.round(x),(int)Math.round(y),(int)Math.round(x),(int)Math.round(y));
		
		for(int i=1;i<=steps;i++){
			x=x+xinc;
			y=y+yinc;
			g.drawLine((int)Math.round(x),(int)Math.round(y),(int)Math.round(x),(int)Math.round(y));
		}
	}
	public void MidPoint(int xc,int yc,int r){
		int x=0,y=r,d=1-r;
		while(x<=y){
			PlotCircle(xc,yc,x,y);
			if(d<0){
				d=d+2*x+3;
			}
			else{
				d=d+2*(x-y)+5;
				y--;
			}
			x++;
		}
	}
	public void PlotCircle(int h,int k,int x,int y){
		setPixel(h+y,k-x,Color.red);
		setPixel(h+y,k+x,Color.red);
		setPixel(h-y,k-x,Color.red);
		setPixel(h-y,k+x,Color.red);
		setPixel(h+x,k+y,Color.red);
		setPixel(h+x,k-y,Color.red);
		setPixel(h-x,k-y,Color.red);
		setPixel(h-x,k+y,Color.red);
	}
	
	public void DrawObject(){
		DDA((int)xarr[1],(int)yarr[1],(int)xarr[2],(int)yarr[2]);
		DDA((int)xarr[1],(int)yarr[1],(int)xarr[3],(int)yarr[3]);
		DDA((int)xarr[2],(int)yarr[2],(int)xarr[3],(int)yarr[3]);
		DDA((int)xarr[3],(int)yarr[3],(int)xarr[4],(int)yarr[4]);
		DDA((int)xarr[4],(int)yarr[4],(int)xarr[5],(int)yarr[5]);
		DDA((int)xarr[5],(int)yarr[5],(int)xarr[2],(int)yarr[2]);
		//DDA((int)xarr[0],(int)yarr[0],(int)xarr[5],(int)yarr[5]);
		
		
	}
	public Color getPixel(int x,int y){
		return pixel[x][y];
	}
	public void setPixel(int x,int y,Color c){
		g=getGraphics();
		g.setColor(c);
		pixel[x][y]=g.getColor();
		g.fillOval(x,y,2,2);
	}
	public void boundryfill(int x,int y,Color fvalue,Color bvalue){
		if((getPixel(x,y)!=bvalue)&& (getPixel(x,y)!=fvalue)){
			setPixel(x,y,fvalue);
			boundryfill(x,y+1,fvalue,bvalue);
			boundryfill(x,y-1,fvalue,bvalue);
			boundryfill(x+1,y,fvalue,bvalue);
			boundryfill(x-1,y,fvalue,bvalue);
		}
	}
	public void mouseClicked(MouseEvent e){
		x1=e.getX();
		y1=e.getY();
		d=30;
		h=150;
		xarr[0]=x1;
		yarr[0]=y1;
		xarr[1]=x1;
		yarr[1]=y1+(5*d);
		xarr[2]=x1-d;
		yarr[2]=y1+(3*d);
		xarr[3]=x1+d;
		yarr[3]=y1+(3*d);
		xarr[4]=x1+d;
		yarr[4]=y1+d;
		xarr[5]=x1-d;
		yarr[5]=y1+d;
		g.setColor(Color.blue);
		DrawObject();
		
		Translate(-x1,-y1);
		Rotation(90);
		Translate(x1,y1);
		DrawObject();
		
		Translate(-x1,-y1);
		Rotation(90);
		Translate(x1,y1);
		DrawObject();
		
		Translate(-x1,-y1);
		Rotation(90);
		Translate(x1,y1);
		DrawObject();
		
		//MidPoint(x1,y1,30);
		//boundryfill(x1,y1,Color.green,Color.red);
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	
	public void Translate(double tx,double ty){
		for(int i=1;i<6;i++){
			xarr[i]+=tx;
			yarr[i]+=ty;
		}
			
	}
	public void Rotation(double angle){
		double teta=Math.toRadians(angle);
		double tempX;
		for(int i=1;i<6;i++){
			tempX=xarr[i]*Math.cos(teta)-yarr[i]*Math.sin(teta);
			yarr[i]=xarr[i]*Math.sin(teta)+yarr[i]*Math.cos(teta);
			xarr[i]=tempX;
		}
	}
	
	public static void main(String []args){
		new drawing();
	}
}