package eu.bibl.banalysis.asm.refactor.asm;

import eu.bibl.banalysis.storage.CallbackMappingData;
import eu.bibl.banalysis.storage.ClassMappingData;
import eu.bibl.banalysis.storage.FieldMappingData;
import eu.bibl.banalysis.storage.HookMap;
import org.objectweb.asm.commons.Remapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sc4re
 */
public class RefactorMapper extends Remapper {

    protected final Map<String, ClassMappingData> sortedClasses;
    protected final Map<String, CallbackMappingData> sortedMethods;
    protected final Map<String, FieldMappingData> sortedFields;

    public RefactorMapper(HookMap hookMap) {
        sortedClasses = new HashMap<>();
        sortedMethods = new HashMap<>();
        sortedFields = new HashMap<>();
        for(ClassMappingData hook : hookMap.getClasses()){
            if(hook.getObfuscatedName().contains("$"))
                continue;
            String obfuscatedName = hook.getObfuscatedName();
            String refactoredName = hook.getRefactoredName();
            sortedClasses.put(obfuscatedName, hook);
            sortedClasses.put(refactoredName, hook);
        }
        for (CallbackMappingData hook : hookMap.getMethods()) {
            String obfuscatedName = hook.getMethodName().getObfuscatedName();
            String obfuscatedDesc = hook.getMethodName().getRefactoredName();
            String obfuscatedCname = hook.getMethodOwner().getObfuscatedName();
            String refactoredCname = hook.getMethodOwner().getRefactoredName();


            sortedMethods.put(obfuscatedCname + "$$$$" + obfuscatedName + "$$$$" + obfuscatedDesc, hook);
            sortedMethods.put(refactoredCname + "$$$$" + obfuscatedName + "$$$$" + obfuscatedDesc, hook);

        }
        for (FieldMappingData hook : hookMap.getFields()) {
            String obfuscatedName = hook.getName().getObfuscatedName();
            String obfuscatedDesc = hook.getDesc().getObfuscatedName();
            String obfuscatedCname = hook.getFieldOwner().getObfuscatedName();
            String refactoredCname = hook.getFieldOwner().getRefactoredName();
            sortedFields.put(obfuscatedCname + "$$$$" + obfuscatedName + "$$$$" + obfuscatedDesc, hook);
            sortedFields.put(refactoredCname + "$$$$" + obfuscatedName + "$$$$" + obfuscatedDesc, hook);
        }
    }

    @Override
    public String map(String type) {
        if (sortedClasses.containsKey(type))
            return sortedClasses.get(type).getRefactoredName();
        return type;
    }

    @Override
    public String mapFieldName(String owner, String name, String desc) {
        String obfKey = owner + "$$$$" + name + "$$$$" + desc;
        if (sortedFields.containsKey(obfKey))
            name = sortedFields.get(obfKey).getName().getRefactoredName();
        return name;
    }

    @Override
    public String mapMethodName(String owner, String name, String desc) {
        String obfKey = owner + "$$$$" + name + "$$$$" + desc;
        if (sortedMethods.containsKey(obfKey))
            name = sortedMethods.get(obfKey).getMethodName().getRefactoredName();
        return name;
    }
}
