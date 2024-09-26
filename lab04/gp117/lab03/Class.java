public enum Class {
    E,
    T;

    public static Class getEnum(String s) {
        if (s.matches("T")) {
            return T;
        } else {
            return E;
        }
    }
}