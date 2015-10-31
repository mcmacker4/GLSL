#version 400 core

in vec3 position;

out vec3 color;

uniform mat4 transform;
uniform mat4 view;

void main(void) {
	gl_Position = view * transform * vec4(position, 1.0);
	color = vec3(position.x + 0.5, 1.0, position.y + 0.5);
}