package ocdSim;

import processing.core.PVector;

public class OverworldTriggerFactory extends PComponent implements OverworldObjectFactory {

	@Override
	public OverworldObject generate(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ,
			float checkRadius, PVector quaternion) {
		dim.y=dim.x;
	    return new OverworldTrigger(pos, dim, parentTranslationX, parentTranslationZ, checkRadius, quaternion);
	}

}
