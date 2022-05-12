package ru.job4j.serialization;

public class Preserves {

    private final String content;
    private final int mass;

    public Preserves(String content, int mass) {
        this.content = content;
        this.mass = mass;
    }

    public String getContent() {
        return content;
    }

    public int getMass() {
        return mass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Preserves preserves = (Preserves) o;
        if (mass != preserves.mass) {
            return false;
        }
        return content.equals(preserves.content);
    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + mass;
        return result;
    }

    @Override
    public String toString() {
        return "Preserves{"
                + "content='" + content + '\''
                + ", mass=" + mass
                + '}';
    }
}
