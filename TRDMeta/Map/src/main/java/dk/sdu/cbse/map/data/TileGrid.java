package dk.sdu.cbse.map.data;

//import static dk.sdu.mmmi.cbse.map.helpers.Artist.drawQuadText;

public class TileGrid {

	public Tile[][] map;
	
	public TileGrid() {
		map =  new Tile[20][15];
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Grass);
			}
		}
	}
	
	public TileGrid(int[][] newMap) {
		map = new Tile[20][15];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch (newMap[j][i]){
				default:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Grass);
					break;
				case 0:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Grass);			
					break;
				case 1:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Dirt);
					break;
				case 2:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Water);
					break;
				}
			}
		}
	}
	
	public void SetTile(int xCoord, int yCoord, TileType type) {
		map[xCoord][yCoord] = new Tile(xCoord * 64, yCoord * 64, 64, 64, type);
	}
	
	public Tile getTile(int xCoord, int yCoord){
		return map[xCoord][yCoord];
	}
	
	
	
	public void draw(){
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Tile t = map[i][j];
//				drawQuadText(t.getTexture(), t.getX(), t.getY(), t.getWidth(), t.getHeight());
			}
		}
	}
}
