package ocdSim;
import processing.core.*;
public class ImageTexture extends Texture {
	private PImage img;

	public ImageTexture(PImage img) {

		this.img=img;
	}

	@Override
	public void apply() {
		ocdSimulator.image(img, -img.width/2, -img.height/2);

	}

	@Override
	public void resize(int width, int height) {
		img.resize(width, height);

	}

}
