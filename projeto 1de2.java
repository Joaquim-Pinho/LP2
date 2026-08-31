import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class RGB{
		short r,g,b;
		RGB(short r, short g, short b){
			this.r = r;
			this.g =g;
			this.b = b;
		}
}
//SUPERCLASSE Figure
abstract class Figure{
	int x,y;
	int w,h;
	RGB cContorno,cFundo;
	cContorno.r=0;
	abstract void del();
	abstract void paint(Graphics g);
	
	public void drag(KeyEvent evt){
		if(evt.KeyCode() == KeyEvent.VK_UP){
			y= y+1;
		}else if(evt.KeyCode() == KeyEvent.VK_DOWN){
			y= y-1;
		}else if(evt.KeyCode() == KeyEvent.VK_RIGHT){
			x= x+1;
		}else if(evt.KeyCode() == KeyEvent.VK_LEFT){
			x= x-1;	
		}
	}

	public boolean contains(int x, int y) {
		return x >= this.x && x <= (this.x + this.w) && y >= this.y && y <= (this.y + this.h);
	}
}

public class Rect extends Figure {
    public Rect (int x, int y, int w, int h) {
        super(x,y, w,h);
    }
	
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
		Random rand = new Random();
		cContorno.g = (short) rand.nextInt(255);
		cContorno.b = (short) rand.nextInt(255);
		cFundo.g = (short) rand.nextInt(255);
		cFundo.b = (short) rand.nextInt(255);
		g2d.setColor(new Color(cFundo.r, Fundo.g,cFundo.b));
		g2d.FillRect(this.x,this.y, this.w,this.h);
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }
}

public class Ellipse extends Figure {
    public Ellipse (int x, int y, int w, int h) {
        super(x,y, w,h);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
		Random rand = new Random();
		cContorno.g = (short) rand.nextInt(255);
		cContorno.b = (short) rand.nextInt(255);
		cFundo.g = (short) rand.nextInt(255);
		cFundo.b = (short) rand.nextInt(255);
		g2d.setColor(new Color(cFundo.r, Fundo.g,cFundo.b));
		g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }
}

class Paw extends Figure{
	//paw representa uma pegada de uma pata. Idealmente, um circulo de raio R com tres menores de raio R/4
Paw (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
	
	public void paint (Graphics g) {
		int R = Math.min(this.w,this.h);
        Graphics2D g2d = (Graphics2D) g;
		Random rand = new Random();
		cContorno.g = (short) rand.nextInt(255);
		cContorno.b = (short) rand.nextInt(255);
		cFundo.g = (short) rand.nextInt(255);
		cFundo.b = (short) rand.nextInt(255);
		g2d.setColor(new Color(cFundo.r, Fundo.g,cFundo.b));
		g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h)); //elipse principal
		float fy= this.y + R*5.0/4;
		float fw = R/4.0;
		float fx = this.x;
		float fh =this.h;
		g2d.setColor(new Color(cFundo.r, Fundo.g,cFundo.b));
		g2d.fill(new Ellipse2D.Double(fx,fy, fw,fh)));
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.draw(new Ellipse2D.Double(fx,fy, fw,fh)); //circulo superior
		fx = fx - 5.0* R/8;
		fy = y + 1.3*R*5/8;
		g2d.setColor(new Color(cFundo.r, Fundo.g,cFundo.b));
		g2d.fill(new Ellipse2D.Double(fx,fy, fw,fh));
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.draw(new Ellipse2D.Double(fx,fy, fw,fh));//circulo esquerdo
		fx = fx+ R;
		g2d.setColor(new Color(cFundo.r, Fundo.g,cFundo.b));
		g2d.fill(new Ellipse2D.Double(fx,fy, fw,fh));
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.draw(new Ellipse2D.Double(fx,fy, fw,fh));//circulo direito
		
}
class TV extends Figure{
	//uma tv/monitor. Caixa(w,h), tela(retângulo dentro da caixa de w*0.9,h*0.9). haste(w/4,(h/7)*2), e suporte(w/2,h/7) 
TV (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
	public void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Random rand = new Random();
		cContorno.g = (short) rand.nextInt(255);
		cContorno.b = (short) rand.nextInt(255);
		cFundo.g = (short) rand.nextInt(255);
		cFundo.b = (short) rand.nextInt(255);
		float fx = this.x;
		float fy = this.y;
		float fw = this.w* 0.9;
		float fh = this.h* 0.9;
		g2d.setColor(new Color(0, 0,0));
		g2d.FillRect(fx,fy, fw,fh);
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.drawRect(fx,fy, fw,fh);//tela como é sempre preta, vem primeiro.				
		fw= this.w/4.0;
		fh = this.h/3.5;
		fy= fy - this.h;
		fx=fx + 3* this.w/8.0;
		g2d.setColor(new Color(ccFundo.r, Fundo.g,cFundo.b));
		g2d.FillRect(fx,fy, fw,fh);
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.drawRect(fx,fy, fw,fh);
		fx= this.x + this.w/4.0;
		fy= fy- fh;
		fh= this.y/7.0;
		fw= this.w/2.0;
		g2d.setColor(new Color(cFundo.r, Fundo.g,cFundo.b));
		g2d.FillRect(fx,fy, fw,fh);
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.drawRect(fx,fy, fw,fh);
		g2d.setColor(new Color(cFundo.r, Fundo.g,cFundo.b));
		g2d.FillRect(this.x,this.y, this.w,this.h);
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.drawRect(this.x,this.y, this.w,this.h);//caixa
    }

}
//LISTA
class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
//CRIAÇÃO
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = rand.nextInt(350);
                    int y = rand.nextInt(350);
                    int w = rand.nextInt(50);
                    int h = rand.nextInt(50);
                    if (evt.getKeyChar() == 'r') {
                        Rect r = new Rect(x,y, w,h);
                        figs.add(r);
                    } else if (evt.getKeyChar() == 'e') {
                        figs.add(new Ellipse(x,y, w,h));
                    }
					  else if (evt.getKeyChar()== 'p'){
						  figs.add(new Paw(x,y,w,h));
					  }
					  else if(evt.getKeyChar() == 't'){
						  figs.add(new TV(x,y,w,h));
					  }
                    repaint();
                }
            }
        );
//QUADRO
        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}

//FOCO
class FFrame extends Figure{
	private Figure focus = null;
	
	public void mousePressed(MouseEvent evt){
	for(Figure fig: figs){
		if(fig.contains(evt.getX(),evt.getY())){
			focus=fig;
			break;
		}
	}
	if (focus!= null){
	focus.cContorno.r= new RGB((short)255, short(0), short(0));
	focus.repaint();
	}
}
	this.addKeyListener (
        new KeyAdapter() {
		public void keyFocusPressed(KeyEvent evt){
			if(focus != null){
				focus.drag(evt);
				focus.repaint();
				if(evt.getKeyChar()=='d'){
					focus.x= evt.getX()
					focus.y=evt.getY();
					focus.repaint();
				}	
				}
				if (evt.getKeyChar() == 'x'){
					figs.remove(focus);
                    focus = null;
				}
				if(evt.getKeyChar()=='a'){
					if (evt.getButton() == MouseEvent.BUTTON1){
						w = evt.getX() -focus.x;
						if (w<0){
							w= -1*w;
						}
					}
					if (evt.getButton() == MouseEvent.BUTTON3){
						h= evt.getY()- focus.y;
						if (h<0){
							h=-1*h;
						}
					}
					focus.repaint();
				}
		
		}
		}
	}
//MAIN
class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}
