package eu.bibl.banalysis.storage.gson;

import java.lang.reflect.Type;

import org.objectweb.asm.tree.InsnNode;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * User: Stl
 * Date: 4/20/2014
 * Time: 1:29 PM
 * Use:
 */
public class InsnNodeSerializer implements JsonSerializer<InsnNode>, JsonDeserializer<InsnNode> {
    
	@Override
    public InsnNode deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) json;
        int opcode = jsonObject.get("opcode").getAsInt();
        return new InsnNode(opcode);
    }

    @Override
    public JsonElement serialize(InsnNode src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("opcode", context.serialize(src.getOpcode(), Integer.class));
        return jsonObject;
    }
}