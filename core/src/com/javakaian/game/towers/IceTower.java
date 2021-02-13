package com.javakaian.game.towers;

import java.util.List;
import java.util.Map.Entry;

import com.javakaian.game.entity.Bullet;
import com.javakaian.game.entity.Bullet.EnumBulletType;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;

public class IceTower extends BaseTower {

	public IceTower(float x, float y, List<Enemy> enemyList) {
		super(x, y, enemyList);
		type = TowerType.ICE;
		damage = GameConstants.TOWER_DAMAGE_ICE;
		sprite = MyAtlas.ICE_TOWER;
		spriteSelected = MyAtlas.ICE_TOWER;
	}

	@Override
	public void projectileShoot() {

		for (Entry<Enemy, Float> entry : enemyMap.entrySet()) {
			bulletList.add(new Bullet(center.x, center.y, entry.getKey(), damage, EnumBulletType.ICE_BULLET));
		}
	}

}
