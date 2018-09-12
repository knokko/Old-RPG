package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import main.Application;

public class Encoder {
	
	public static final byte ENCODING_BYTE_MAP = 0;
	public static final byte ENCODING_BYTES_TO_WIDTH = 1;
	public static final byte ENCODING_BYTES_TO_HEIGHT = 2;
	public static final byte ENCODING_BYTES_TO_WIDTH_2 = 3;
	public static final byte ENCODING_BYTES_TO_HEIGHT_2 = 4;
	public static final byte ENCODING_BYTES_TO_VECTOR_SOUTH_EAST = 5;
	
	public static final byte ID_FILL = -128;
	
	public static final byte ID_RECTANGLE = -127;
	public static final byte ID_LARGE_RECTANGLE = -126;
	public static final byte ID_LARGE_X_RECTANGLE = -125;
	public static final byte ID_LARGE_Y_RECTANGLE = -124;
	public static final byte ID_FAR_RECTANGLE = -123;
	public static final byte ID_FAR_LARGE_RECTANGLE = -122;
	public static final byte ID_FAR_LARGE_X_RECTANGLE = -121;
	public static final byte ID_FAR_LARGE_Y_RECTANGLE = -120;
	public static final byte ID_FAR_Y_RECTANGLE = -119;
	public static final byte ID_FAR_Y_LARGE_RECTANGLE = -118;
	public static final byte ID_FAR_Y_LARGE_X_RECTANGLE = -117;
	public static final byte ID_FAR_Y_LARGE_Y_RECTANGLE = -116;
	public static final byte ID_FAR_X_RECTANGLE = -115;
	public static final byte ID_FAR_X_LARGE_RECTANGLE = -114;
	public static final byte ID_FAR_X_LARGE_X_RECTANGLE = -113;
	public static final byte ID_FAR_X_LARGE_Y_RECTANGLE = -112;
	
	public static void save(){
		ArrayList<Byte> data = new ArrayList<Byte>();
		data.addAll(storeTiles(Application.tiles1));
		data.addAll(storeTiles(Application.tiles2));
		data.addAll(storeTiles(Application.tiles3));
		byte[] bytes = new byte[data.size()];
		int i = 0;
		while(i < data.size()){
			bytes[i] = data.get(i);
			++i;
		}
		try {
			FileOutputStream stream = new FileOutputStream("generated.map");
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void load(){
		byte[] bytes;
		try {
			FileInputStream stream = new FileInputStream("generated.map");
			bytes = new byte[0];
			while(stream.available() > 0){
				bytes = Arrays.copyOf(bytes, bytes.length + stream.available());
				stream.read(bytes);
				Thread.sleep(20);
			}
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		int index = 0;
		index = loadTiles(bytes, index, 1);
		index = loadTiles(bytes, index, 2);
		index = loadTiles(bytes, index, 3);
	}
	
	private static ArrayList<Byte> storeTiles(byte[][] tiles){
		ArrayList<Byte> byteMap = new ArrayList<Byte>();
		ArrayList<Byte> bytesToWidth = new ArrayList<Byte>();
		ArrayList<Byte> bytesToHeight = new ArrayList<Byte>();
		ArrayList<Byte> bytesToWidth2 = new ArrayList<Byte>();
		ArrayList<Byte> bytesToHeight2 = new ArrayList<Byte>();
		encodeInByteMap(byteMap, tiles);
		encodeInBytesToWidth(bytesToWidth, tiles);
		encodeInBytesToHeight(bytesToHeight, tiles);
		encodeInBytesToWidth2(bytesToWidth2, tiles);
		encodeInBytesToHeight2(bytesToHeight2, tiles);
		ArrayList<Byte> best = byteMap;
		if(bytesToWidth.size() < best.size())
			best = bytesToWidth;
		if(bytesToHeight.size() < best.size())
			best = bytesToHeight;
		if(bytesToWidth2.size() < best.size())
			best = bytesToWidth2;
		if(bytesToHeight2.size() < best.size())
			best = bytesToHeight2;
		return best;
	}
	
	private static int loadTiles(byte[] data, int index, int layer){
		byte encoding = data[index];index++;
		short width = ByteBuffer.wrap(data, index, 2).getShort();index += 2;
		short height = ByteBuffer.wrap(data, index, 2).getShort();index += 2;
		byte[][] tiles = new byte[width][height];
		if(encoding == ENCODING_BYTE_MAP)
			index = decodeFromByteMap(data, tiles, index);
		if(encoding == ENCODING_BYTES_TO_WIDTH)
			index = decodeFromBytesToWidth(data, tiles, index);
		if(encoding == ENCODING_BYTES_TO_HEIGHT)
			index = decodeFromBytesToHeight(data, tiles, index);
		if(encoding == ENCODING_BYTES_TO_WIDTH_2)
			index = decodeFromBytesToWidth2(data, tiles, index);
		if(encoding == ENCODING_BYTES_TO_HEIGHT_2)
			index = decodeFromBytesToHeight2(data, tiles, index);
		if(layer == 1)
			Application.tiles1 = tiles;
		if(layer == 2)
			Application.tiles2 = tiles;
		if(layer == 3)
			Application.tiles3 = tiles;
		return index;
	}

	private static void encodeInByteMap(ArrayList<Byte> bytes, byte[][] tiles){
		bytes.add(ENCODING_BYTE_MAP);
		encodeBase(bytes, tiles);
		int x = 0;
		while(x < tiles.length){
			int y = 0;
			while(y < tiles[x].length){
				bytes.add(tiles[x][y]);
				++y;
			}
			++x;
		}
	}
	
	private static int decodeFromByteMap(byte[] data, byte[][] tiles, int index){
		int x = 0;
		while(x < tiles.length){
			int y = 0;
			while(y < tiles[x].length){
				tiles[x][y] = data[index];
				++y;
				++index;
			}
			++x;
		}
		return index;
	}

	private static void encodeInBytesToWidth(ArrayList<Byte> bytes, byte[][] tiles) {
		bytes.add(ENCODING_BYTES_TO_WIDTH);
		encodeBase(bytes, tiles);
		byte[] stream = new byte[tiles.length * tiles[0].length];
		int i = 0;
		int x = 0;
		while(x < tiles.length){
			int y = 0;
			while(y < tiles[x].length){
				stream[i] = tiles[x][y];
				++y;
				++i;
			}
			++x;
		}
		putFillBytes(stream, bytes);
	}
	
	private static int decodeFromBytesToWidth(byte[] data, byte[][] tiles, int index){
		byte[] stream = new byte[tiles.length * tiles[0].length];
		index = getFillBytes(stream, data, index);
		int i = 0;
		int x = 0;
		while(x < tiles.length){
			int y = 0;
			while(y < tiles[x].length){
				tiles[x][y] = stream[i];
				++y;
				++i;
			}
			++x;
		}
		return index;
	}
	
	private static void encodeInBytesToWidth2(ArrayList<Byte> bytes, byte[][] tiles) {
		bytes.add(ENCODING_BYTES_TO_WIDTH_2);
		encodeBase(bytes, tiles);
		byte[] stream = new byte[tiles.length * tiles[0].length];
		int i = 0;
		int x = 0;
		while(x < tiles.length){
			int y = 0;
			while(y < tiles[x].length){
				stream[i] = tiles[x][y];
				++y;
				++i;
			}
			++x;
		}
		putFillBytes2(stream, bytes);
	}
	
	private static int decodeFromBytesToWidth2(byte[] data, byte[][] tiles, int index){
		byte[] stream = new byte[tiles.length * tiles[0].length];
		index = getFillBytes2(stream, data, index);
		int i = 0;
		int x = 0;
		while(x < tiles.length){
			int y = 0;
			while(y < tiles[x].length){
				tiles[x][y] = stream[i];
				++y;
				++i;
			}
			++x;
		}
		return index;
	}
	
	private static void encodeInBytesToHeight(ArrayList<Byte> bytes, byte[][] tiles){
		bytes.add(ENCODING_BYTES_TO_HEIGHT);
		encodeBase(bytes, tiles);
		byte[] stream = new byte[tiles.length * tiles[0].length];
		int i = 0;
		int y = 0;
		while(y < tiles[0].length){
			int x = 0;
			while(x < tiles.length){
				stream[i] = tiles[x][y];
				++x;
				++i;
			}
			++y;
		}
		putFillBytes(stream, bytes);
	}
	
	private static int decodeFromBytesToHeight(byte[] data, byte[][] tiles, int index){
		byte[] stream = new byte[tiles.length * tiles[0].length];
		index = getFillBytes(stream, data, index);
		int i = 0;
		int y = 0;
		while(y < tiles[0].length){
			int x = 0;
			while(y < tiles.length){
				tiles[x][y] = stream[i];
				++x;
				++i;
			}
			++y;
		}
		return index;
	}
	
	private static void encodeInBytesToHeight2(ArrayList<Byte> bytes, byte[][] tiles){
		bytes.add(ENCODING_BYTES_TO_HEIGHT_2);
		encodeBase(bytes, tiles);
		byte[] stream = new byte[tiles.length * tiles[0].length];
		int i = 0;
		int y = 0;
		while(y < tiles[0].length){
			int x = 0;
			while(x < tiles.length){
				stream[i] = tiles[x][y];
				++x;
				++i;
			}
			++y;
		}
		putFillBytes2(stream, bytes);
	}
	
	private static int decodeFromBytesToHeight2(byte[] data, byte[][] tiles, int index){
		byte[] stream = new byte[tiles.length * tiles[0].length];
		index = getFillBytes2(stream, data, index);
		int i = 0;
		int y = 0;
		while(y < tiles[0].length){
			int x = 0;
			while(y < tiles.length){
				tiles[x][y] = stream[i];
				++x;
				++i;
			}
			++y;
		}
		return index;
	}
	
	private static void encodeInByteVector(ArrayList<Byte> bytes, byte[][] tiles){
		bytes.add(ENCODING_BYTES_TO_VECTOR_SOUTH_EAST);
		encodeBase(bytes, tiles);
		ArrayList<TileRectangle> patterns = findPatterns(tiles);
		int i = 0;
		while(i < patterns.size()){
			++i;
		}
	}
	
	private static ArrayList<TileRectangle> findPatterns(byte[][] tiles){
		ArrayList<TileRectangle> patterns = new ArrayList<TileRectangle>();
		return patterns;
	}
	
	private static void putFillBytes(byte[] stream, ArrayList<Byte> bytes){
		int i = 0;
		while(i < stream.length){
			short counts = 1;
			byte tile = stream[i];
			while(i + counts < stream.length && stream[i + counts] == tile && counts < Short.MAX_VALUE){
				++counts;
			}
			if(counts > 4){
				bytes.add(ID_FILL);
				byte[] length = ByteBuffer.allocate(2).putShort(counts).array();
				bytes.add(length[0]);
				bytes.add(length[1]);
				bytes.add(tile);
				i += counts;
			}
			else {
				bytes.add(tile);
				++i;
			}
		}
	}
	
	private static int getFillBytes(byte[] stream, byte[] data, int index){
		int i = 0;
		while(i < stream.length){
			byte tile = data[index];
			if(tile == ID_FILL){
				short length = ByteBuffer.wrap(data, index + 1, 2).getShort();
				byte fillTile = data[index + 3];
				index += 4;
				int j = 0;
				while(j < length){
					stream[i] = fillTile;
					++j;
					++i;
				}
			}
			else {
				stream[i] = tile;
				++index;
				++i;
			}
		}
		return index;
	}
	
	private static void putFillBytes2(byte[] stream, ArrayList<Byte> bytes){
		int i = 0;
		while(i < stream.length){
			int counts = 1;
			byte tile = stream[i];
			while(i + counts < stream.length && stream[i + counts] == tile && counts < Integer.MAX_VALUE){
				++counts;
			}
			if(counts > 6){
				bytes.add(ID_FILL);
				byte[] length = ByteBuffer.allocate(4).putInt(counts).array();
				bytes.add(length[0]);
				bytes.add(length[1]);
				bytes.add(length[2]);
				bytes.add(length[3]);
				bytes.add(tile);
				i += counts;
			}
			else {
				bytes.add(tile);
				++i;
			}
		}
	}
	
	private static int getFillBytes2(byte[] stream, byte[] data, int index){
		int i = 0;
		while(i < stream.length){
			byte tile = data[index];
			if(tile == ID_FILL){
				int length = ByteBuffer.wrap(data, index + 1, 4).getInt();
				byte fillTile = data[index + 5];
				index += 6;
				int j = 0;
				while(j < length){
					stream[i] = fillTile;
					++j;
					++i;
				}
			}
			else {
				stream[i] = tile;
				++index;
				++i;
			}
		}
		return index;
	}
	
	private static void encodeBase(ArrayList<Byte> bytes, byte[][] tiles){
		byte[] width = ByteBuffer.allocate(2).putShort((short) tiles.length).array();
		byte[] height = ByteBuffer.allocate(2).putShort((short) tiles[0].length).array();
		bytes.add(width[0]);
		bytes.add(width[1]);
		bytes.add(height[0]);
		bytes.add(height[1]);
	}
	
	private static abstract class TilePattern {
		
		public abstract int getSize();
		
		public abstract byte getTile(int x, int y);
		
		public abstract void save(ArrayList<Byte> bytes);
	}
	
	private static class TileRectangle extends TilePattern {
		
		public final short minX;
		public final short minY;
		public final short width;
		public final short height;
		
		public final byte tile;
		
		public TileRectangle(int minX, int minY, int maxX, int maxY, int tile){
			this.minX = (short) minX;
			this.minY = (short) minY;
			this.width = (short) (maxX - minX);
			this.height = (short) (maxY - minY);
			this.tile = (byte) tile;
		}
		
		@Override
		public int getSize(){
			return width * height;
		}
		
		@Override
		public byte getTile(int x, int y){
			return tile;
		}
		
		@Override
		public void save(ArrayList<Byte> bytes){
			byte id = -1;
			if(minX > 127 && minY > 127 && width <= 127 && height <= 127)
				id = ID_FAR_RECTANGLE;
			else if(minX > 127 && minY <= 127 && width <= 127 && height <= 127)
				id = ID_FAR_X_RECTANGLE;
			else if(minX > 127 && minY <= 127 && width > 127 && height <= 127)
				id = ID_FAR_X_LARGE_X_RECTANGLE;
			else if(minX > 127 && minY <= 127 && width <= 127 && height > 127)
				id = ID_FAR_X_LARGE_Y_RECTANGLE;
			else if(minX > 127 && minY <= 127 && width > 127 && height > 127)
				id = ID_FAR_X_LARGE_RECTANGLE;
			else if(minX <= 127 && minY > 127 && width <= 127 && height <= 127)
				id = ID_FAR_Y_RECTANGLE;
			else if(minX <= 127 && minY <= 127 && width > 127 && height > 127)
				id = ID_LARGE_RECTANGLE;
			else if(minX <= 127 && minY <= 127 && width <= 127 && height > 127)
				id = ID_LARGE_X_RECTANGLE;
			else if(minX <= 127 && minY <= 127 && width > 127 && height <= 127)
				id = ID_LARGE_X_RECTANGLE;
			else if(minX <= 127 && minY <= 127 && width <= 127 && height <= 127)
				id = ID_RECTANGLE;
			bytes.add(id);
			if(minX <= 127)
				bytes.add((byte) minX);
			else {
				byte[] x = ByteBuffer.allocate(2).putShort(minX).array();
				bytes.add(x[0]);
				bytes.add(x[1]);
			}
			if(minY <= 127)
				bytes.add((byte) minY);
			else {
				byte[] y = ByteBuffer.allocate(2).putShort(minY).array();
				bytes.add(y[0]);
				bytes.add(y[1]);
			}
			if(width <= 127)
				bytes.add((byte) width);
			else {
				byte[] w = ByteBuffer.allocate(2).putShort(width).array();
				bytes.add(w[0]);
				bytes.add(w[1]);
			}
			if(height <= 127)
				bytes.add((byte) height);
			else {
				byte[] h = ByteBuffer.allocate(2).putShort(height).array();
				bytes.add(h[0]);
				bytes.add(h[1]);
			}
		}
	}
}
