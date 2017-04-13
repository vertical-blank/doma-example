package boilerplate;

import java.util.Optional;

public class Column {

    public final String expression;
    public final Optional<String> alias;

    public Column(String expression, String alias) {
        this.expression = expression;
        this.alias = Optional.of(alias);
    }

    public Column(String expression) {
        this.expression = expression;
        this.alias = Optional.empty();
    }

    @Override
    public String toString() {
        return this.alias.map(a -> expression + " " + a).orElse(expression);
    }
}
