package java.lang.annotation;

/**
 * The constants of this enumerated type provide a simple classification of the
 * syntactic locations where annotations may appear in a Java program. These
 * constants are used in {@link Target java.lang.annotation.Target}
 * meta-annotations to specify where it is legal to write annotations of a
 * given type.
 *
 * <p>The syntactic locations where annotations may appear are split into
 * <em>declaration contexts</em> , where annotations apply to declarations, and
 * <em>type contexts</em> , where annotations apply to types used in
 * declarations and expressions.
 *
 * <p>The constants {@link #ANNOTATION_TYPE} , {@link #CONSTRUCTOR} , {@link
 * #FIELD} , {@link #LOCAL_VARIABLE} , {@link #METHOD} , {@link #PACKAGE} ,
 * {@link #PARAMETER} , {@link #TYPE} , and {@link #TYPE_PARAMETER} correspond
 * to the declaration contexts in JLS 9.6.4.1.
 *
 * <p>For example, an annotation whose type is meta-annotated with
 * {@code @Target(ElementType.FIELD)} may only be written as a modifier for a
 * field declaration.
 *
 * <p>The constant {@link #TYPE_USE} corresponds to the 15 type contexts in JLS
 * 4.11, as well as to two declaration contexts: type declarations (including
 * annotation type declarations) and type parameter declarations.
 *
 * <p>For example, an annotation whose type is meta-annotated with
 * {@code @Target(ElementType.TYPE_USE)} may be written on the type of a field
 * (or within the type of the field, if it is a nested, parameterized, or array
 * type), and may also appear as a modifier for, say, a class declaration.
 *
 * <p>The {@code TYPE_USE} constant includes type declarations and type
 * parameter declarations as a convenience for designers of type checkers which
 * give semantics to annotation types. For example, if the annotation type
 * {@code NonNull} is meta-annotated with
 * {@code @Target(ElementType.TYPE_USE)}, then {@code @NonNull}
 * {@code class C {...}} could be treated by a type checker as indicating that
 * all variables of class {@code C} are non-null, while still allowing
 * variables of other classes to be non-null or not non-null based on whether
 * {@code @NonNull} appears at the variable's declaration.
 *
 * @author  Joshua Bloch
 * @since 1.5
 * @jls 9.6.4.1 @Target
 * @jls 4.1 The Kinds of Types and Values
 */
public enum ElementType {
    /** 
        Class, interface (including annotation type), or enum declaration 
        @Target(ElementType.TYPE)   //接口、类、枚举
    */
    TYPE,

    /** 
        Field declaration (includes enum constants)
        @Target(ElementType.FIELD) //字段、枚举的常量
    */
    FIELD,

    /**
        Method declaration 
        @Target(ElementType.METHOD) //方法
    */
    METHOD,

    /** 
        Formal parameter declaration 
        @Target(ElementType.PARAMETER) //方法参数
    */
    PARAMETER,

    /** 
        Constructor declaration
        @Target(ElementType.CONSTRUCTOR)  //构造函数
    */
    CONSTRUCTOR,

    /** 
        Local variable declaration
        @Target(ElementType.LOCAL_VARIABLE)//局部变量
    */
    LOCAL_VARIABLE,

    /**
        Annotation type declaration
        @Target(ElementType.ANNOTATION_TYPE)//注解
    */
    ANNOTATION_TYPE,

    /**
        Package declaration
        @Target(ElementType.PACKAGE) ///包
    */
    PACKAGE,

    /**
     * Type parameter declaration
     *
       ElementType.TYPE_PARAMETER(Type parameter declaration) 用来标注类型参数， 栗子如下：
          @Target(ElementType.TYPE_PARAMETER)
          @Retention(RetentionPolicy.RUNTIME)
          public @interface TypeParameterAnnotation {

          }

          // 如下是该注解的使用例子
          public class TypeParameterClass<@TypeParameterAnnotation T> {
              public <@TypeParameterAnnotation U> T foo(T t) {
                  return null;
              }
          }
     * @since 1.8
     */
    TYPE_PARAMETER,

    /**
     * Use of a type
     *
     * ElementType.TYPE_USE(Use of a type) 能标注任何类型名称，包括上面这个（ElementType.TYPE_PARAMETER的），栗子如下：
            @Target(ElementType.TYPE_USE)
            @Retention(RetentionPolicy.RUNTIME)
            public @interface TypeUseAnnotation {
            }
    
            public static @TypeUseAnnotation class TypeUseClass<@TypeUseAnnotation T> extends @TypeUseAnnotation Object {
                public void foo(@TypeUseAnnotation T t) throws @TypeUseAnnotation Exception {
            
                }
            }
     * @since 1.8
     */
    TYPE_USE
}
