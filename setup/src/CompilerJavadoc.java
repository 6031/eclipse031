import java.io.IOException;

import org.junit.Test;

/** */
public class CompilerJavadoc {

    /** */
    public class MalformedJavadocComments {

        /**
         * @param param
         * @param noSuchParam does not exist
         * @return
         * @throws IOException
         * @throws Exception no it doesn't
         */
        public int publicMethod(int param) throws IOException {
            throw new Error();
        }

        /**
         * @param param
         * @param noSuchParam does not exist
         * @return
         * @throws IOException
         * @throws Exception no it doesn't
         */
        int packageMethod(int param) throws IOException {
            throw new Error();
        }
    }

    /** */
    public class MissingJavadocTags<TypeParam> {

        /** */
        public int publicMethod(int param) throws IOException {
            throw new Error();
        }

        /** */
        int packageMethod(int param) throws IOException {
            throw new Error();
        }

        /** */
        @Override public String toString() {
            return null;
        }
    }

    public class MissingJavadocComments {

        public void publicMethod() {
            // empty
        }

        void packageMethod() {
            // empty
        }

        @Test public void testMethod() {
            // empty
        }

        @Override public String toString() {
            return null;
        }
    }
}
