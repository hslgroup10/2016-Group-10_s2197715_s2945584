
import java.awt.Color;

/**
 * The main program class of JavaPaint
 */
public class JavaPaint {

	/** Create a program instance */
	public JavaPaint() {
		/* Create a paint container */
		PaintContainer pc = new PaintContainer();

		/* Draw some stuff */

		Circle circle = new Circle();
		circle.setCircle(10, 10, 10);
		circle.setProperties(false, Color.RED);
		Rectangle rect = new Rectangle();
		rect.setRectangle(0, 25, 20, 100);
		rect.setProperties(true, Color.RED);
		Compound i = new Compound();

		i.setCompound();
		i.subShapes.add(circle);
		i.subShapes.add(rect);

		Rectangle rect2 = new Rectangle();
		rect2.setRectangle(0, 0, 25, 125);
		rect2.setProperties(true, Color.GREEN);

		Circle c1 = new Circle();
		c1.setCircle(45, 30, 30);
		c1.setProperties(true, Color.GREEN);

		Circle c2 = new Circle();
		c2.setCircle(45, 30, 15);
		c2.setProperties(true, Color.WHITE);

		Compound p = new Compound();
		p.setCompound();
		p.subShapes.add(c1);
		p.subShapes.add(c2);
		p.subShapes.add(rect2);

		Rectangle rect3 = new Rectangle();
		rect3.setRectangle(0, 0, 25, 125);
		rect3.setProperties(true, Color.BLUE);

		Triangle tri1 = new Triangle();
		tri1.setTriangle(70, 0, 10, 70, 70, 125);
		tri1.setProperties(true, Color.BLUE);

		Triangle tri2 = new Triangle();
		tri2.setTriangle(70, 20, 40, 70, 70, 105);
		tri2.setProperties(true, Color.WHITE);
		Compound k = new Compound();
		k.setCompound();
		k.subShapes.add(rect3);
		k.subShapes.add(tri1);
		k.subShapes.add(tri2);

		Quadrangle quad = new Quadrangle();
		quad.setQuadrangle(0, 0, 30, 10, 25, 100, 5, 90);
		quad.setProperties(true, Color.YELLOW);

		Square sq = new Square();
		sq.setSquare(5, 105, 20);
		sq.setProperties(false, Color.YELLOW);

		Compound fi = new Compound();
		fi.setCompound();
		fi.subShapes.add(quad);
		fi.subShapes.add(sq);

		p.draw(pc, 10, 10);
		i.draw(pc, 80, 55);
		i.draw(pc, 120, 90);
		i.draw(pc, 160, 130);
		k.draw(pc, 200, 170);
		i.draw(pc, 280, 210);
		fi.draw(pc, 320, 250);
	}

	public static void main(String args[]) {
		new JavaPaint();
	}
}
