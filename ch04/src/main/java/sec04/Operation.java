package sec04;

public enum Operation {
    ADD("+") {
        public int eval(int arg1, int arg2) {
            return arg1 + arg2;
        }
    },
    SUBTRACT("-") {
        public int eval(int arg1, int arg2) {
            return arg1 - arg2;
        }
    },
    MULTIPLY("*") {
        public int eval(int arg1, int arg2) {
            return arg1 * arg2;
        }
    },
    DIVIDE("/") {
        public int eval(int arg1, int arg2) {
            return arg1 / arg2;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract int eval(int arg1, int arg2);
}