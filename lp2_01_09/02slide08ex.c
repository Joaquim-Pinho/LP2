#include <stdio.h>
#include <stdlib.h>

typedef struct {
    short r,g,b;
} RGB;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);
typedef int  (* Figure_Area)  (struct Figure*);
typedef int (*Figure_Perimetro) (struct Figure*);

typedef struct {
    void (* print) (struct Figure*);
    int  (* area)  (struct Figure*);
    int (* perimetro) (struct Figure*);
} Figure_vtable;

typedef struct Figure {
    int x, y, w, h;
    RGB CContorno, CFundo;
    Figure_vtable* vtable;
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d) e area %d.\n",
           this->w, this->h, sup->x, sup->y, sup->vtable->area(sup));
}

int rect_area (Rect* this) {
    Figure* sup = (Figure*) this;
    return this->w * this->h;
}

int rect_perimetro(Rect* this){
	Figure* sup =(Figure*)this;
	return 2*(this->w + this->h);
}

Figure_vtable rect_vtable = {
    (Figure_Print) rect_print,
    (Figure_Area)  rect_area,
    (Figure_Perimetro) rect_perimetro
};

Rect* rect_new (int x, int y, int w, int h) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->vtable = &rect_vtable;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
} Ellipse;

void ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d) e area %d.\n",
           this->w, this->h, sup->x, sup->y, sup->vtable->area(sup));
}

int ellipse_area (Ellipse* this) {
    Figure* sup = (Figure*) this;
    return 3/4*(this->w * this->h);
}

int ellipse_perimetro(Ellipse* this){
	Figure* sup = (Figure*) this;
	return 3* sqrt(2*(this->w*this->w + this->h*this->h));
}

Figure_vtable ellipse_vtable = {
    (Figure_Print) ellipse_print,
    (Figure_Area)  ellipse_area,
    (Figure Perimetro) ellipse_perimetro
};

Ellipse* ellipse_new (int x, int y, int w, int h) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->vtable = &ellipse_vtable;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////

void main (void) {
    Figure* figs[8] = {
        (Figure*) rect_new(10,10,100,100),
        (Figure*) ellipse_new(40,10,140,300),
        (Figure*) rect_new(10,10,100,100),
        (Figure*) ellipse_new(210,110,305,130),
        (Figure*) tv_new(45,76,24,8),
        (Figure*) tv_new(57,178,281,80),
        (Figure*) paw_new(45,289,74, 132),
        (Figure*) paw_new(285,46,189, 28)
    };

    ///

    for (int i=0; i<8; i++) {
        figs[i]->vtable->print(figs[i]);
    }

    ///

    for (int i=0; i<4; i++) {
        free(figs[i]);
    }
}
