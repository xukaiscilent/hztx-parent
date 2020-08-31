package com.hztx.util.json;

import com.google.gson.*;

import java.lang.reflect.Type;

@SuppressWarnings("rawtypes")
public class TypeClassAdapter implements JsonSerializer<Class>, JsonDeserializer<Class> {

	@Override
	public Class deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return null;
	}

	@Override
	public JsonElement serialize(Class src, Type typeOfSrc, JsonSerializationContext context) {
		return null;
	}

}
