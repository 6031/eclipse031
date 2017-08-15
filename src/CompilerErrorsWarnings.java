import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class CodeStyle {

    static int staticField;

    static void staticMethod() {
        // empty
    }

    int instanceField;

    class Subclass extends CodeStyle {
        // empty
    }

    void nonStaticAccessToStaticMember() {
        // use a static member through the instance
        this.staticMethod();
    }

    void indirectAccessToStaticMember() {
        // use a static member through a subclass
        Subclass.staticMethod();
    }

    void unqualifiedAccessToInstanceField() {
        // use an instance field without `this.`
        System.out.println(instanceField);
    }

    void accessToNonAccessibleMemberOfEnclosingType() {
        // TODO
    }

    void parameterAssignment(int param) {
        // assign to a method argument variable
        param = 0;
    }

    void nonExternalizedStrings() {
        // use string literals without special dispensation
        System.out.println("string literal");
    }

    void undocumentedEmptyBlock() {
    }
    // an empty block with no comment inside

    void resourceNotManagedViaTryWithResource() {
        // TODO
    }

    // methodWithConstructorName
    void CodeStyle() {
        // a method whose name is the name of the class
    }

    private void methodCanBeStatic() {
        // a (private) method that can be made static without changing the public API
        staticMethod();
    }

    {
        methodCanBeStatic(); // use the above method to silence unused method warning
    }

    public void methodCanPotentiallyBeStatic() {
        // a (public) method that can be made static, changing the public API of the
        // class
        staticMethod();
    }
}

class PotentialProgrammingProblems {

    synchronized void synchronizedInstanceMethod() {
        // empty
    }

    void comparingIdenticalValues(int x) {
        System.err.println(x == x);
    }

    void assignmentHasNoEffect() {
        int x = 5;
        x = x;
    }

    void possibleAccidentalBooleanAssignment() {
        boolean a, b = true;
        if (a = b) {
            System.out.println(a);
        }
    }

    void boxingAndUnboxingConversions() {
        // TODO
    }

    void usingCharArrayInStringConcatenation() {
        System.out.println("Hello, " + new char[] { 'w', 'o' });
    }

    void inexactTypeMatchForVarargArguments() {
        // TODO
    }

    void unlikelyArgumentTypeForCollectionMethodsUsingObject() {
        // TODO
    }

    void unlikelyArgumentTypeForMethodEquals() {
        "1000".equals(1000);
    }

    void emptyStatement() {
        ;
    }

    void unusedObjectAllocation() {
        new Object();
    }

    void incompleteSwitchCasesOnEnum() {
        // TODO
    }

    void switchMissingDefaultCase() {
        // TODO
    }

    void switchCaseFallThrough() {
        // TODO
    }

    void hiddenCatchBlock() {
        // TODO
    }

    void finallyDoesNotCompleteNormally() {
        try {
            throw new RuntimeException();
        } finally {
            throw new RuntimeException();
        }
    }

    void deadCode() {
        if (false) {
            System.out.println(false);
        }
    }

    void resourceLeak() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(null));
        System.out.println(reader.readLine());
    }

    void potentialResourceLeak() throws IOException {
        InputStream in = new URL(null).openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        System.out.println(reader.readLine());
    }

    class SerializableClassWithoutSerialVersionUID implements Serializable {
        // no serialVersionUID declared
    }

    class MissingSynchronizedModifierOnInheritedMethod extends PotentialProgrammingProblems {

        @Override void synchronizedInstanceMethod() {
            // missing synchronized modifier
        }
    }

    class ClassOverridesEqualsButNotHashCode {

        @Override public boolean equals(Object obj) {
            return false;
        }
    }
}

class NameShadowingAndConflicts<TypeParam> {

    int instanceField;

    NameShadowingAndConflicts() {
        // empty
    }

    class FieldDeclarationHidesAnotherFieldOrVariable extends NameShadowingAndConflicts<String> {

        int instanceField;
    }

    void localVariableDeclarationHidesAnotherFieldOrVariable() {
        int instanceField = 5;
        System.out.println(instanceField);
    }

    // localVariableDeclarationHidesAnotherFieldOrVariable constructor parameter
    public NameShadowingAndConflicts(int instanceField) {
        System.out.println(instanceField);
    }

    class TypeParameterHidesAnotherType<TypeParam> extends NameShadowingAndConflicts<String> {
        // empty
    }

    void methodDoesNotOverridePackageVisibleMethod() {
        // TODO
    }

    interface InterfaceMethodConflictsWithProtectedObjectMethod {

        Object clone() throws IOException;
    }
}

class DeprecatedAndRestrictedAPI {
    // TODO
}

class UnnecessaryCode {
    // TODO
}

class GenericTypes {

    // TODO

    void redundantTypeArguments() {
        List<String> list = new ArrayList<String>();
        System.out.println(list);
    }
}

class Annotations {

    class MissingOverrideAnnotation implements Runnable {

        public String toString() {
            return null;
        }

        public void run() {
            // empty
        }
    }

    // TODO
}

class NullAnalysis {
    // TODO
}
