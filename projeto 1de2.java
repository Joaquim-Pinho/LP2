import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

//SUPERCLASSE Figure
abstract class Figure{
	int x,y;
	int w,h;
	int z;
	public class RGB{
		short r,g,b;
		RGB(short r, short g, short b){
			this.r = r;
			this.g =g;
			this.b = b;
		}
	RGB cContorno,cFundo;
	}
	abstract void new;
	abstract void del;
	abstract void paint;
	
	//void drag{	
}

public class Rect extends Figure {
    public Rect (int x, int y, int w, int h) {
        super(x,y, w,h);
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }
}

public class Ellipse extends Figure {
    public Ellipse (int x, int y, int w, int h) {
        super(x,y, w,h);
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }
}

class Paw extends Figure{
Paw (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

}
class TV extends Figure{
TV (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

}

//
arrayList<Figure> figs= 

//CRIAÇÃO


//FOCO
class FFrame extends Figure{
	public void mousePressed(MouseEvent evt){
	for(Figure fig: figs){
		if(fig.x vs evt.getX() && fig.y vs evt.getY()){
			focus=fig;
			fig.z +=1;
		}
	}
	focus.cContorno.r=255;
	focus.cContorno.g=0;
	focus.cContorno.b=0;
}
}