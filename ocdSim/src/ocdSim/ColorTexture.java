package ocdSim;
import processing.core.*;
public class ColorTexture extends Texture {
	private int fill;
	private PVector dimensions=new PVector();

	public ColorTexture(int fill) {
		this.fill=fill;
	}

	@Override
	public void apply() {
		pSimulator.pushStyle();
		pSimulator.noStroke();
		pSimulator.fill(fill);
		pSimulator.rect(-dimensions.x/2, -dimensions.y/2, dimensions.x, dimensions.y);
		pSimulator.popStyle();

	}

	@Override
	public void resize(int width, int height) {
		dimensions.x=width;
		dimensions.y=height;

	}

}
