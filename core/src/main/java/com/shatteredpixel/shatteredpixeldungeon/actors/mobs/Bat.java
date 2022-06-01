/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BatSprite;
import com.watabou.utils.Random;

public class Bat extends Mob {

	{
		spriteClass = BatSprite.class;
		
		HP = HT = 100;
		defenseSkill = 5;

		baseSpeed = 5f;
		
		EXP = 32;
		maxLvl = 90;
		
		flying = true;
		
		loot = new PotionOfHealing();
		lootChance = 0.1667f; //by default, see lootChance()
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 4, 12 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 50;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 4);
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		int reg = Math.min( damage - 4, HT - HP );
		
		if (reg > 0) {
			HP += 5;
			sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
		}
		
		return damage;
	}
	@Override
	public void damage(int dmg, Object src) {
		if (dmg >= 10){
			//takes 5/6/7/8/9/10 dmg at 5/7/10/14/19/25 incoming dmg
			dmg = 9 - (int)(Math.sqrt(8*(dmg - 4) + 1) - 1)/2;
		}
		super.damage(dmg, src);
	}


	@Override
	public float lootChance(){
		return super.lootChance() * ((7f - Dungeon.LimitedDrops.BAT_HP.count) / 7f);
	}
	
	@Override
	public Item createLoot(){
		Dungeon.LimitedDrops.BAT_HP.count++;
		return super.createLoot();
	}
	
}
