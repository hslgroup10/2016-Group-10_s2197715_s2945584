import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

/**
 * This class is used to represent two-dimensional geometric shapes which can be
 * drawn in a PaintContainer.
 */
abstract public class Shape {

	/** * Shape types ** */
	/*
	 * A compound shape is a special shape that consists of zero or more other
	 * shapes. The other types represent primitive geometric shapes.
	 */
	public static final int SHAPE_COMPOUND = 1;

	public static final int SHAPE_CIRCLE = 0;

	public static final int SHAPE_RECTANGLE = 2;

	public static final int SHAPE_SQUARE = 3;

	public static final int SHAPE_QUADRANGLE = 4;

	public static final int SHAPE_TRIANGLE = 5;
	
	/* The type of this shape */
	protected int shapeType = SHAPE_COMPOUND;

	/** * Shape definitions ** */
	/*
	 * The list of subShapes, i.e. member shapes, in case this shape is used as
	 * a compound shape.
	 */
	protected Vector subShapes = new Vector();

	/*
	 * The position of the shape (center for circles, top-left corner for
	 * squares and rectangles.
	 */
	protected int posx, posy;

	/*
	 * The width and height of squares (width only) and rectangles.
	 */
	protected int w, h;

	/*
	 * Corner coordinates for triangles and quadrangles.
	 */
	protected int x1, y1, x2, y2, x3, y3, x4, y4;

	/*
	 * Radius for circles
	 */
	protected int cradius;

	/** * Shape properties ** */
	/*
	 * Specifies whether this shape should be outlined or filled.
	 */
	protected boolean filled = false;

	/*
	 * The color of the shape
	 */
	protected Color col = Color.BLACK;

	/**
	 * Create a new shape
	 */
	protected Shape() {
		// Nothings needs to be initialized in the abstract constructor
	}

	
	/**
	 * Set the drawing properties, like filled or not, and the color for this
	 * shape. This only works with primitive shapes!
	 * 
	 * @param fill
	 *            Whether this shape should be filled or not
	 * @param color
	 *            The color of this shape
	 */
	public void setProperties(boolean fill, Color color) {
		filled = fill;
		col = color;
	}
	/**
	 * Draw the shape in the specified paintcontainer, with a specified extra
	 * offset from the top-left corner of the paintcontainer.
	 * 
	 * @param pc
	 *            the PaintContainer to draw in
	 * @param xoff
	 *            the x offset from the top-left corner of pc
	 * @param yoff
	 *            the y offset from the top-left corner of pc
	 */
	abstract void draw(PaintContainer pc, int xoff, int yoff);

	
	
}

class Circle extends Shape {	
	
	public void setCircle(int x, int y, int radius) {
		shapeType = SHAPE_CIRCLE;
		cradius = radius;
		posx = x;
		posy = y;
	}
	
	void draw(PaintContainer pc, int xoff, int yoff){
		Graphics g = pc.getDrawingArea();
		g.setColor(col);
		if (filled) {
			g.fillOval(xoff + posx - cradius, yoff + posy - cradius,
					cradius * 2, cradius * 2);
		} else {
			g.drawOval(xoff + posx - cradius, yoff + posy - cradius,
					cradius * 2, cradius * 2);
		}
		pc.repaint();
	}
}

 class Square extends Shape {
	
	public void setSquare(int x, int y, int width) {
		shapeType = SHAPE_SQUARE;
		w = width;
		posx = x;
		posy = y;
	}
	
	void draw(PaintContainer pc, int xoff, int yoff){
		Graphics g = pc.getDrawingArea();
		g.setColor(col);
		if (filled) {
			g.fillRect(xoff + posx, yoff + posy, w, w);
		} else {
			g.drawRect(xoff + posx, yoff + posy, w, w);
		}
		pc.repaint();
	}
}

class Rectangle extends Shape {
	
	public void setRectangle(int x, int y, int width, int height) {
		shapeType = SHAPE_RECTANGLE;
		w = width;
		h = height;
		posx = x;
		posy = y;
	}
	
	void draw(PaintContainer pc, int xoff, int yoff) {
		Graphics g = pc.getDrawingArea();
		g.setColor(col);
		if (filled) {
			g.fillRect(xoff + posx, yoff + posy, w, h);
		} else {
			g.drawRect(xoff + posx, yoff + posy, w, h);
		}
		pc.repaint();
	}
}

class Quadrangle extends Shape {
	
	public void setQuadrangle(int xa, int ya, int xb, int yb, int xc, int yc,
			int xd, int yd) {
		shapeType = SHAPE_QUADRANGLE;
		x1 = xa;
		y1 = ya;
		x2 = xb;
		y2 = yb;
		x3 = xc;
		y3 = yc;
		x4 = xd;
		y4 = yd;
	}
	
	void draw(PaintContainer pc, int xoff, int yoff) {
		Graphics g = pc.getDrawingArea();
		g.setColor(col);
		int[] qpx = { x1 + xoff, x2 + xoff, x3 + xoff, x4 + xoff };
		int[] qpy = { y1 + yoff, y2 + yoff, y3 + yoff, y4 + yoff };
		if (filled) {
			g.fillPolygon(qpx, qpy, 4);
		} else {
			g.drawPolygon(qpx, qpy, 4);
		}
		pc.repaint();
	}
}

class Triangle extends Shape {
	
	public void setTriangle(int xa, int ya, int xb, int yb, int xc, int yc) {
		shapeType = SHAPE_TRIANGLE;
		x1 = xa;
		y1 = ya;
		x2 = xb;
		y2 = yb;
		x3 = xc;
		y3 = yc;
	}
	
	void draw(PaintContainer pc, int xoff, int yoff){
		Graphics g = pc.getDrawingArea();
		g.setColor(col);
		int[] tpx = { x1 + xoff, x2 + xoff, x3 + xoff };
		int[] tpy = { y1 + yoff, y2 + yoff, y3 + yoff };
		if (filled) {
			g.fillPolygon(tpx, tpy, 3);
		} else {
			g.drawPolygon(tpx, tpy, 3);
		}
		pc.repaint();
	}
}

class Compound extends Shape {
	
	public void setCompound() {
		shapeType = SHAPE_COMPOUND;
	}
	
	void draw(PaintContainer pc, int xoff, int yoff) {
		
		Iterator i = subShapes.iterator();
		while (i.hasNext()) {
			Shape s = (Shape) i.next();
			s.draw(pc, xoff, yoff);
		}
		pc.repaint();
	}
}
