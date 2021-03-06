/*
 * This file is part of SpoutAPI.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * SpoutAPI is licensed under the Spout License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.api.generator;

import org.spout.api.geo.World;
import org.spout.api.util.Named;
import org.spout.api.util.cuboid.CuboidBlockMaterialBuffer;

/**
 * Represents a World generator.
 *
 * WorldGenerators are used to generate {@link World}s (surprise surprise)
 */
public interface WorldGenerator extends Named {
	/**
	 * Gets the block structure for a Chunk.
	 *
	 * The CuboidBuffer will always be:<br>
	 * - One Chunk in width and length {@link org.spout.api.geo.cuboid.Chunk#CHUNKS}<br>
	 * - One Region in height {@link org.spout.api.geo.cuboid.Region#CHUNKS}<br>
	 * - Chunk aligned<br>
	 * <br>
	 * Structural blocks should not contain any lighting sources and the
	 * generator should give repeatable results.
	 *
	 * It is recommended that seeded random number generators from
	 * WorldGeneratorUtils are used.
	 *
	 * @param blockData a zeroed CuboidBuffer corresponding to the Chunk
	 * @param chunkX coordinate
	 * @param chunkY coordinate
	 * @param chunkZ coordinate
	 * @param world in which is generated
	 */
	public void generate(CuboidBlockMaterialBuffer blockData, int chunkX, int chunkY, int chunkZ, World world);

	/**
	 * Gets the surface height of the world. This is used for initialisation
	 * purposed only, so only needs reasonable accuracy.<br> <br> The result
	 * value should be a 2d array of size {@link  org.spout.api.geo.cuboid.Chunk#CHUNK_SIZE}
	 * squared.<br> <br> This hint will improve lighting calculations for
	 * players who move into new areas.
	 *
	 * @param chunkX coordinate
	 * @param chunkZ coordinate
	 * @return the surface height array for the column, or null not to provide a
	 * hint
	 */
	public int[][] getSurfaceHeight(World world, int chunkX, int chunkZ);

	/**
	 * Gets an array of Populators for the world generator
	 *
	 * @return the Populator array
	 */
	public Populator[] getPopulators();

	/**
	 * Gets the name of the generator. This name should be unique to prevent two
	 * generators overwriting the same world
	 */
	@Override
	public String getName();
}
