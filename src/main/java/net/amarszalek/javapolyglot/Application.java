package net.amarszalek.javapolyglot;

import org.graalvm.polyglot.*;

class Application {

    public static void main(String[] args) {
        Context jsContext = Context.create("js");

        printHello(jsContext);
        exchangeData(jsContext);
        accessJavaObjectFromScript(jsContext);

        jsContext.close();
    }

    private static void printHello(Context ctx) {
        runScript("console.log('Hello from the project')", ctx);
    }

    private static void exchangeData(Context ctx) {
        String script = "console.log('I will welcome you ' + welcomeCount + ' times.');" +
                "for(var i=0; i<welcomeCount; i++){ console.log('Welcome') }";
        Value bindings = ctx.getBindings("js");
        bindings.putMember("welcomeCount", 3);

        runScript(script, ctx);

        runScript("var x = 'jsVariable';", ctx);
        String jsVariableValue = ctx.getBindings("js").getMember("x").asString();
        System.out.println(jsVariableValue);
    }

    private static void accessJavaObjectFromScript(Context ctx) {
        String printNumberScript = "console.log(phone.number)";
        String callingScript = "phone.call('Someone')";

        Phone phone = new Phone(123456);
        ctx.getBindings("js").putMember("phone", phone);

        runScript(printNumberScript, ctx);
        runScript(callingScript, ctx);
    }

    private static Value runScript(String script, Context context) {
        return context.eval("js", script);
    }
}
