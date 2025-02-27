package sec02;

import javax.script.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class CompileDemo {
    public static void main(String[] args) throws IOException, ScriptException {
        var manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        //if (engine implements Compilable) {
        Reader reader = Files.newBufferedReader(Path.of("/tmp/next.js"));
        CompiledScript script = ((Compilable) engine).compile(reader);
        script.eval();
        //}    
    }
}
