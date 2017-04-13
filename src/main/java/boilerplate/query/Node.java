package boilerplate.query;

import java.math.BigDecimal;
import java.util.Optional;

public interface Node {

    boolean isParam();

    default Object classify() {
        return this.toString();
    }
    
    public class Statement implements Node {
        public final String value;
        private Statement(String value) {
            this.value = value;
        }
        public static Statement of(String value) {
            return new Statement(value);
        }

        @Override
        public boolean isParam() {
            return false;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public class Parameter implements Node {
        public final Object value;
        public final Class<?> clazz;

        private Parameter(Object value, Optional<Class<?>> classOpt) {
            this.value = value;
            this.clazz = classOpt.orElse(value.getClass());
        }

        public Object classify() {
            if (clazz == String.class) {
                return value;
            }
            if (clazz == Number.class) {
                return new BigDecimal(String.valueOf(value));
            }
            if (clazz == int.class) {
                return Integer.valueOf(String.valueOf(value));
            }
            if (clazz == Integer.class) {
                return Integer.valueOf(String.valueOf(value));
            }
            if (clazz == Boolean.class) {
                return Boolean.valueOf(String.valueOf(value));
            }

            return null;
        }

        public static Parameter of(String value, Class<?> clazz) {
            return new Parameter(value, Optional.of(clazz));
        }

        public static Parameter of(String value, String clazz) throws ClassNotFoundException {
            return new Parameter(value, Optional.of(Class.forName(clazz)));
        }
        
        public static Parameter of(Object value) {
            return new Parameter(value, Optional.empty());
        }

        @Override
        public boolean isParam() {
            return true;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

}



