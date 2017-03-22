/*
 * Copyright (C) 2014 Benny Bobaganoosh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#version 330

in vec2 texCoord0;

out vec4 fragColor;

uniform vec3 color;
uniform sampler2D sampler;

void main()
{
    vec4 textureColor = texture(sampler, texCoord0.xy);

    if(textureColor == vec4(0,0,0,0))
        fragColor = vec4(color, 1);
    else
        fragColor = textureColor * vec4(color, 1);
}
