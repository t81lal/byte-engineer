package eu.bibl.banalysis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.bibl.banalysis.storage.gson.*;
import org.objectweb.asm.tree.*;

public final class AnalysisCore {
	
	public static final Gson GSON_INSTANCE = getBuilder().create();
	
	public static GsonBuilder getBuilder() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(FieldInsnNode.class, new FieldInsnNodeSerializer());
        builder.registerTypeAdapter(FrameNode.class, new FrameNodeSerializer());
        builder.registerTypeAdapter(IincInsnNode.class, new IincInsNodeSerializer());
        builder.registerTypeAdapter(InsnNode.class, new InsnNodeSerializer());
        builder.registerTypeAdapter(IntInsnNode.class, new IntInsnNodeSerializer());
        builder.registerTypeAdapter(JumpInsnNode.class, new JumpInsnNodeSerializer());
        builder.registerTypeAdapter(LabelNode.class, new LabelNodeSerializer());
        builder.registerTypeAdapter(LdcInsnNode.class, new LdcInsnNodeSerializer());
        builder.registerTypeAdapter(LineNumberNode.class, new LineNumberNodeSerializer());
        builder.registerTypeAdapter(LookupSwitchInsnNode.class, new LookupSwitchInsnNodeSerializer());
        builder.registerTypeAdapter(MethodInsnNode.class, new MethodInsnNodeSerializer());
        builder.registerTypeAdapter(MultiANewArrayInsnNode.class, new MultiANewArrayInsnNodeSerializer());
        builder.registerTypeAdapter(TableSwitchInsnNode.class, new TableSwitchInsnNodeSerializer());
        builder.registerTypeAdapter(TypeInsnNode.class, new TypeInsnNodeSerializer());
        builder.registerTypeAdapter(VarInsnNode.class, new VarInsnNodeSerializer());
        builder.setPrettyPrinting();
        return builder;
    }
}