package eu.bibl.banalysis.asm.refactor;

import eu.bibl.banalysis.asm.ClassNode;
import eu.bibl.banalysis.storage.classes.ClassContainer;
import eu.bibl.banalysis.asm.refactor.asm.RefactorMapper;
import eu.bibl.banalysis.storage.HookMap;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.RemappingClassAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sc4re
 */
public class Refactorer {

    protected HookMap hooks;
    protected ClassContainer container;

    public Refactorer(HookMap hooks) {
        setHooks(hooks);
    }

    public Refactorer(ClassContainer ct) {
        setContainer(ct);
    }

    public Refactorer(HookMap hooks, ClassContainer ct) {
        setHooks(hooks);
        setContainer(ct);
    }

    public HookMap getHooks() {
        return hooks;
    }

    public void setHooks(HookMap hooks) {
        this.hooks = hooks;
    }

    public ClassContainer getContainer() {
        return container;
    }

    public void setContainer(ClassContainer ct) {
        this.container = ct;
    }

    public void run() {
        if (getHooks() == null)
            return;
        if (getContainer() == null)
            return;
        RefactorMapper mapper = new RefactorMapper(getHooks());
        Map<String, ClassNode> refactored = new HashMap<>();
        for (ClassNode cn : getContainer().getNodes().values()) {
            String oldName = cn.name;
            ClassReader cr = new ClassReader(getClassNodeBytes(cn));
            ClassWriter cw = new ClassWriter(cr, 0);
            RemappingClassAdapter rca = new RemappingClassAdapter(cw, mapper);
            cr.accept(rca, ClassReader.EXPAND_FRAMES);
            cr = new ClassReader(cw.toByteArray());
            cn  = new ClassNode();
            cr.accept(cn, 0);
            refactored.put(oldName, cn);
        }
        for (Map.Entry<String, ClassNode> factor : refactored.entrySet()) {
            getContainer().relocate(factor.getKey(), factor.getValue());
        }
    }

    private byte[] getClassNodeBytes(ClassNode cn) {
        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        byte[] b = cw.toByteArray();
        return b;
    }
}
