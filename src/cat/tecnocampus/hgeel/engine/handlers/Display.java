package cat.tecnocampus.hgeel.engine.handlers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL;

import cat.tecnocampus.hgeel.input.Keyboard;
import cat.tecnocampus.hgeel.input.Mouse;

import static org.lwjgl.glfw.GLFW.*;

public class Display {
	
	public static long window;
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	private static GLFWErrorCallback errorCallback;
	
	public static void create() {
		
		glfwSetErrorCallback(errorCallback = Callbacks.errorCallbackPrint(System.err));
		
		if(glfwInit() != GL_TRUE)
			throw new IllegalStateException("Could not initialize GLFW.");
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		
		window = glfwCreateWindow(WIDTH, HEIGHT, "GLSL Shaders", NULL, NULL);
		if(window == NULL)
			throw new RuntimeException("Could not create GLFW window.");
		
		glfwSetKeyCallback(window, new Keyboard());
		glfwSetCursorPos(window, 0, 0);
		glfwSetCursorPosCallback(window, new Mouse());
		
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(
				window,
				(GLFWvidmode.width(vidmode) - WIDTH) / 2,
				(GLFWvidmode.height(vidmode) - HEIGHT) / 2
		);
		
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);
		GL.createCapabilities();
		glClearColor(0f, 0f, 0f, 0f);
		glEnable(GL_DEPTH_TEST);
		
	}
	
	public static void destroy() {
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	public static GLFWErrorCallback getErrorCallback() {
		return errorCallback;
	}
	
}
