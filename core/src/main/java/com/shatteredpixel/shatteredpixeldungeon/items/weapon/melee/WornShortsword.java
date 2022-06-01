package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class WornShortsword extends MeleeWeapon {

	{
		image = ItemSpriteSheet.WORN_SHORTSWORD;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.1f;
		RCH = 3;    //lots of extra reach
		tier = 1;

		bones = false;
	}

		@Override
		public int proc(Char attacker, Char defender, int damage ) {
		Buff.affect(defender, Bleeding.class).set(damage);
		return super.proc(attacker, defender, damage);
	}
}