package reflect;

public class Point<T extends Comparable<T>> {

    public static final String TAG="Ponit_Java" 
    private T x, y;

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }
}
