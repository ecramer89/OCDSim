package ocdSim;

import processing.core.PVector;

public class OverworldJewelFactory implements OverworldObjectFactory {

	@Override
	public OverworldObject generate(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ,
			float checkRadius, PVector quaternion) {
		// TODO Auto-generated method stub
		dim.y=dim.x;
		return new OverworldJewel(pos, dim, parentTranslationX, parentTranslationZ, checkRadius, quaternion);
	}

}
