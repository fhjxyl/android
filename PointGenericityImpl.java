package reflect;

import java.io.Serializable;

public class PointGenericityImpl<T extends Number & Serializable> implements PointInterface<T, Integer> {
}
