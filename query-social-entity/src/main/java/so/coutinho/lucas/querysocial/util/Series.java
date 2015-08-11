package so.coutinho.lucas.querysocial.util;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
public class Series {

    public static enum Type {

        AREA("area"), BAR("bar"), LINE("line");

        private final String value;

        private Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

    }

    private final String type;
    private final String name;
    private Object[][] data;

    public Series(String name) {
        this.type = Type.LINE.toString();
        this.name = name;
    }

    public Series(String name, Object[][] data) {
        this(name);
        this.data = data;
    }

    public Series(Type type, String name) {
        this.type = type.toString();
        this.name = name;
    }

    public Series(Type type, String name, Object[][] data) {
        this(type, name);
        this.data = data;
    }

}
