import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

class RGB{
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
	
	public Figure(){
		this.cContorno = new RGB((short)0,(short)0,(short)0);
		this.cFundo = new RGB((short)255,(short)255,(short)255);
	}
	
	abstract void paint(Graphics g);
		
	public void drag(KeyEvent evt){
		if(evt.getKeyCode() == KeyEvent.VK_UP){
			y= y+1;
		}else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
			y= y-1;
		}else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
			x= x+1;
		}else if(evt.getKeyCode() == KeyEvent.VK_LEFT){
			x= x-1;	
		}
	}

	public boolean contains(int x, int y) {
		return x >= this.x && x <= (this.x + this.w) && y >= this.y && y <= (this.y + this.h);
	}
}

class Rect extends Figure {
    public Rect (int x, int y, int w, int h) {
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
		cFundo.r = (short) rand.nextInt(255);
		cFundo.g = (short) rand.nextInt(255);
		cFundo.b = (short) rand.nextInt(255);
		g2d.setColor(new Color(cFundo.r, cFundo.g,cFundo.b));
		g2d.fillRect(this.x,this.y, this.w,this.h);
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }
}

class Ellipse extends Figure {
    public Ellipse (int x, int y, int w, int h) {
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
		cFundo.r = (short) rand.nextInt(255);
		cFundo.g = (short) rand.nextInt(255);
		cFundo.b = (short) rand.nextInt(255);
		g2d.setColor(new Color(cFundo.r, cFundo.g,cFundo.b));
		g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }
}

class Paw extends Figure{
	//paw representa uma pegada de uma pata. Idealmente, um circulo de raio R com tres menores de raio R/4
	public Paw (int x, int y, int w, int h) {
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
		cFundo.r = (short) rand.nextInt(255);
		cFundo.g = (short) rand.nextInt(255);
		cFundo.b = (short) rand.nextInt(255);
		g2d.setColor(new Color(cFundo.r, cFundo.g,cFundo.b));
		g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h)); //elipse principal
		float raioMenor= R/4f;
		float fy= this.y-2*raioMenor;
		float fw = R/2.0f;
		float fx = this.x+ this.w/2f - raioMenor;
		g2d.setColor(new Color(cFundo.r, cFundo.g,cFundo.b));
		g2d.fill(new Ellipse2D.Double(fx,fy, fw,fw));
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.draw(new Ellipse2D.Double(fx,fy, fw,fw)); //circulo superior
		fx = fx - 5*R/8f;
		fy = this.y - 1.7f*raioMenor/2;
		g2d.setColor(new Color(cFundo.r, cFundo.g,cFundo.b));
		g2d.fill(new Ellipse2D.Double(fx,fy, fw,fw));
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.draw(new Ellipse2D.Double(fx,fy, fw,fw));//circulo esquerdo
		fx = fx+ 5*R/4f;
		g2d.setColor(new Color(cFundo.r, cFundo.g,cFundo.b));
		g2d.fill(new Ellipse2D.Double(fx,fy, fw,fw));
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.draw(new Ellipse2D.Double(fx,fy, fw,fw));//circulo direito
		
	}
}
class TV extends Figure{
	//uma tv/monitor. Caixa(w,h), tela(retângulo dentro da caixa de w*0.9,h*0.9). haste(w/4,(h/7)*2), e suporte(w/2,h/7) 
TV (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = Math.max(w,h);
        this.h = Math.min(w,h);
    }
	public void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Random rand = new Random();
		cContorno.g = (short) rand.nextInt(255);
		cContorno.b = (short) rand.nextInt(255);
		cFundo.r = (short) rand.nextInt(255);
		cFundo.g = (short) rand.nextInt(255);
		cFundo.b = (short) rand.nextInt(255);
		g2d.setColor(new Color(cFundo.r, cFundo.g,cFundo.b));
		g2d.fillRect(this.x,this.y, this.w,this.h);
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.drawRect(this.x,this.y, this.w,this.h);//caixa
		float fw = this.w* 0.9f;
		float fh = this.h* 0.9f;
		float fx = this.x+(this.w-fw)/2f;
		float fy = this.y+(this.h-fh)/2f;
		g2d.setColor(new Color((short)0,(short)0,(short)0));
		g2d.fillRect((int)fx,(int)fy, (int)fw,(int)fh);
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.drawRect((int)fx,(int)fy, (int)fw,(int)fh);//tela como é sempre preta, vem por ultimo.				
		fw= this.w/4.0f;
		fh = this.h/3.5f;
		fy= this.y + this.h;
		fx=this.x + 3f* this.w/8.0f;
		g2d.setColor(new Color(cFundo.r, cFundo.g,cFundo.b));
		g2d.fillRect((int)fx,(int)fy,(int)fw,(int)fh);
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.drawRect((int)fx,(int)fy, (int)fw,(int)fh);//haste
		fx= this.x + this.w/4.0f;
		fy= fy+ fh;
		fh= this.h/7.0f;
		fw= this.w/2.0f;
		g2d.setColor(new Color(cFundo.r, cFundo.g,cFundo.b));
		g2d.fillRect((int)fx,(int)fy, (int)fw,(int)fh);
		g2d.setColor(new Color(cContorno.r, cContorno.g,cContorno.b));
		g2d.drawRect((int)fx,(int)fy, (int)fw,(int)fh);//base
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
                    int x = rand.nextInt(750);
                    int y = rand.nextInt(750);
                    int w = 200+ rand.nextInt(250);
                    int h = 200+ rand.nextInt(250);
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
        this.setSize(1050, 950);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}

//FOCO
class FFig implements MouseListener{
	private Figure focus = null;

	//Referência ao array principal para acessar foco
	private ArrayList<Figure> figs;
	private ListFrame listFrame;
	
	public FFig(ArrayList<Figure> figs, ListFrame listFrame){
		this.figs =figs;
		this.listFrame = listFrame;
	}
	
	public void mousePressed(MouseEvent evt){
	for(Figure fig: figs){
		if(fig.contains(evt.getX(),evt.getY())){
			focus=fig;
			
			break;
		}
	}
	if (focus!= null){
	focus.cContorno= new RGB((short)255, (short)0, (short)0);
	}
	}
	public void keyFocusPressed(KeyEvent kEvt, MouseEvent evt){
		if(focus != null){
			focus.drag(kEvt);
			if(kEvt.getKeyChar()=='d'){
				focus.x= evt.getX();
				focus.y=evt.getY();
			}	
			}
			if (kEvt.getKeyChar() == 'x'){
				figs.remove(focus);
				focus = null;
			}
			if(kEvt.getKeyChar()=='a'){
				if (evt.getButton() == MouseEvent.BUTTON1){
					focus.w = evt.getX() -focus.x;
					if (focus.w<0){
						focus.w= -1*focus.w;
					}
				}
				else if (evt.getButton() == MouseEvent.BUTTON3){
					focus.h= evt.getY()- focus.y;
					if (focus.h<0){
						focus.h=-1*focus.h;
					}
				}
			}
			listFrame.repaint(); 
		}
	public void mouseReleased(MouseEvent evt) {
    }

    public void mouseEntered(MouseEvent evt) {
    }

    public void mouseExited(MouseEvent evt) {
    }

    public void mouseClicked(MouseEvent evt) {
    }
	}
//MAIN
class ListApp {
    public static void main (String[] args) {
		//cria quadro
        ListFrame frame = new ListFrame();
		//torna o quadro visivel
        frame.setVisible(true);
    }
}