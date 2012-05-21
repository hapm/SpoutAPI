/*
 * This file is part of SpoutAPI.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutAPI is licensed under the SpoutDev License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.api.util.pool;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class PoolableObject {

	boolean isPooled;
	boolean isFreed;
	ObjectPool parentPool;

	protected void pool(ObjectPool p) {
		if (isPooled) {
			throw new IllegalArgumentException("Object already pooled! Cannot pool again");
		}
		isPooled = true;
		isFreed = false;
		parentPool = p;
	}
		
	/**
	 * Frees the object back into the pool.
	 * Notice: After calling this, you cannot trust the contents of this object.  
	 */
	public void free() {
		if (!isPooled) return; //Ignore if not pooled
		parentPool.reclaim(this);
	}

	@Override
	public void finalize() {
		if (isPooled) {
			free();
		}
	}
}
