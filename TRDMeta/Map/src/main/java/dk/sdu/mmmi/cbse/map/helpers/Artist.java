package dk.sdu.mmmi.cbse.map.helpers;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
//import org.newdawn.slick.opengl.Texture;
//import org.newdawn.slick.opengl.TextureLoader;
//import org.newdawn.slick.util.ResourceLoader;
import static org.lwjgl.opengl.GL11.*;

public class Artist {
	
	public static final int WIDTH = 1280, HEIGHT = 960;
	
	public static void beginSession(){
		
		Display.setTitle("TRD GAME");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
				
		glMatrixMode(GL_PROJECTION);	
	glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
	}
	
	
	public static void drawQuad(float x, float y, float width, float height){
		glBegin(GL_QUADS);
		glVertex2f(x, y); //Top left
		glVertex2f(x + width, y); //Top right
		glVertex2f(x + width, y + height); //Bottom right
		glVertex2f(x, y + height); //Bottom left
		glEnd();
	}
	
//	public static void drawQuadText(Texture tex, float x, float y, float width, float height) {
//		tex.bind();
//		glTranslatef(x, y, 0); //0 for z axis, as we are running 2D
//		glBegin(GL_QUADS);
//		glTexCoord2f(0, 0);
//		glVertex2f(0, 0);
//		glTexCoord2f(1, 0);
//		glVertex2f(width, 0);
//		glTexCoord2f(1, 1);
//		glVertex2f(width, height);
//		glTexCoord2f(0 ,1);
//		glVertex2f(0, height);
//		glEnd();
//		glLoadIdentity();
//	}
//	
//	public static Texture loadTexture(String path, String fileType){
//		Texture tex = null;
//		InputStream in = ResourceLoader.getResourceAsStream(path);
//		try {
//			tex = TextureLoader.getTexture(fileType, in);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return tex;
//	}
//	
//	public static Texture quickLoad(String name) {
//		Texture tex = null;
//		tex = loadTexture("resources/" + name +".png", "PNG");
//		return tex;
//	}
}
