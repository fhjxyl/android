package reflect;

import java.lang.reflect.*;

/**
 * 1.Class 类，描述具体类型
 * <p>
 * 2.TypeVariable接口，描述类型变量（如T extends Comparable<? super T>）
 * 这个代表的就是泛型变量，例如Point,这里面的T就是泛型变量，而如果我们利用一种方法获得的对象是T,那它对应的类型就是TypeVariable
 * <p>
 * 3.WildcardType接口，描述通配符 (？ super T)
 * TypeVariable对应的是泛型变量（类型变量），而如果我们得到不是泛型变量，而是通配符比如：? extends Integer,那它对应的类型就是WildcardType；
 * <p>
 * 4.ParameterizedType接口,描述泛型类或接口类型（如Comparable<? super T>）
 * 他代表的是一个泛型类型，比如Point<T>，它就是一个泛型类型
 * <p>
 * 5.GenericArrayType接口，描述泛型数组（如T[]）
 */
public class Reflect {

    public static void main(String[] args) {
//1.ParameterizedType接口,描述泛型类或接口类型（如Comparable<? super T>）
        //Point<Integer>
        Class<?> cls = PointImpl.class;
        Type type = cls.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            //Point<Integer> 完整泛型
            ParameterizedType parType = ((ParameterizedType) type);
            //getActualTypeArguments获得这个参数化类型声明时所使用的类型参数
            Type[] typeArray = parType.getActualTypeArguments();
            for (Type fillType : typeArray) {
                //如果type所代表的是一个确定的类，比如Integer,String,Double等，那这个type所对应的类型就是Class;强转之后，
                // 得到的就是他们所对应的Class对象，即Integer.Class,String.Class,Double.Class等
                Class fillTypeName = ((Class) fillType);
                System.out.println("填充类型名称：" + fillTypeName + " typeName: " + fillType.getTypeName());
            }
            //获得这个参数化类型声明时所使用的类型参数
            Type baseType = parType.getRawType();
            System.out.println("基础类型名称：" + ((Class) baseType).getName());
        }

        //2.TypeVariable接口
        Class<?> typeVariableCls = PointGenericityImpl.class;
        Type[] typeVarArray = typeVariableCls.getGenericInterfaces();
        for (Type typeVar : typeVarArray) {

            ParameterizedType parType = ((ParameterizedType) typeVar);
            //PointInterface<T, Integer>
            Type[] typeArray = parType.getActualTypeArguments();

            for (Type fillType : typeArray) {

                //T
                if (fillType instanceof TypeVariable) {
                    TypeVariable fillTypeVar = ((TypeVariable) fillType);
                    System.out.println("此接口填充类型名称：" + fillTypeVar.getName());

                    //PointGenericityImpl<T extends Number & Serializable>
                    Type[] bounds = fillTypeVar.getBounds();
                    for (Type bound : bounds) {
                        Class<?> boundCls = ((Class) bound);
                        System.out.println("bound为：" + boundCls.getName());
                    }
                }

                //Integer
                if (fillType instanceof Class) {

                    //获得这个参数化类型声明时所使用的类型参数
                    System.out.println("此接口填充类型名称：" + ((Class) fillType).getName());
                }
            }
        }
        //3.WildcardType
        // PointWildcardImpl implements PointSingleInterface<Comparable<? extends Number>>
        Class<?> wildcardCls = PointWildcardImpl.class;
        // 会有多个实现 type数组就会有多个元素
        Type[] wildTypeClsArray = wildcardCls.getGenericInterfaces();
        for (Type wildType : wildTypeClsArray) {
            if (wildType instanceof ParameterizedType) {
                //PointSingleInterface<Comparable<? extends Number>>
                ParameterizedType parType = ((ParameterizedType) wildType);
                Type[] fillTypeArray = parType.getActualTypeArguments();

                for (Type fillType : fillTypeArray) {

                    if (fillType instanceof ParameterizedType) {
                        //Comparable<? extends Number>
                        ParameterizedType pT = ((ParameterizedType) fillType);

                        Type[] fillTpArray = pT.getActualTypeArguments();

                        for (Type fillTp : fillTpArray) {

                            if (fillTp instanceof WildcardType) {

                                WildcardType wildcardType = ((WildcardType) fillTp);
                                //extends
                                Type[] wildTypeExtensArray = wildcardType.getLowerBounds();
                                for (Type extendsType : wildTypeExtensArray) {
                                    if (extendsType instanceof Class) {
                                        System.out.println("此接口类型上线：" + ((Class) extendsType).getName());
                                    }
                                }

                                //super
                                Type[] wildTypeSuperArray = wildcardType.getUpperBounds();
                                for (Type extendsType : wildTypeSuperArray) {
                                    if (extendsType instanceof Class) {
                                        System.out.println("此接口类型下线：" + ((Class) extendsType).getName());
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
