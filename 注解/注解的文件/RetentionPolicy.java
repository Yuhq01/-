package java.lang.annotation;

/**
 * Annotation retention policy.  The constants of this enumerated type
 * describe the various policies for retaining annotations.  They are used
 * in conjunction with the {@link Retention} meta-annotation type to specify
 * how long annotations are to be retained.
 *
 * @author  Joshua Bloch
 * @since 1.5
 */
public enum RetentionPolicy {
    /**
     * Annotations are to be discarded by the compiler.
     */
    SOURCE,

    /**
     * Annotations are to be recorded in the class file by the compiler
     * but need not be retained by the VM at run time.  This is the default
     * behavior.
     */
    CLASS,

    /**
     * Annotations are to be recorded in the class file by the compiler and
     * retained by the VM at run time, so they may be read reflectively.
     *
     * @see java.lang.reflect.AnnotatedElement
     */
    RUNTIME
}

深入理解编译注解（五）RetentionPolicy.SOURCE 和 RetentionPolicy.CLASS区别讨论

这篇我觉得应该是一个讨论篇，因为我自己还没有找到一个非常满意的答案，希望大家一起来讨论。

正文
元注解RetentionPolicy，表明注解的生命周期：

1、SOURCE：在原文件中有效，被编译器丢弃。
2、CLASS：在class文件有效，可能会被虚拟机忽略。
3、RUNTIME：在运行时有效。

RUNTIME肯定没有意见，因为只有他能够在运行时得到注解，例如之前的数据库框架。那么SOURCE和CLASS呢？

他俩概念上的区别是 CLASS被编译到.class文件中，SOURCE没有，也很好理解。那么再仔细思考一下之前的编译注解demo，如果换成是SOURCE行不信呢？我抱着试一试的心态，运行了一下…

不试不知道，一试吓一跳，卧草草，竟然运行完美！？？

然后我就懵逼了，既然运行无差别，那定义成CLASS有什么用？再冷静的思考一下：

1、SOURCE 是在编译之后被抛弃的，那么他存活的时间是在源码和编译中，所以我们的编译处理器仍然能正常工作。

2、.class文件我们无法操作，这对于我们来说有什么意义？

于是我在网上了搜索了很多的资料，都没有找到答案，于是和我的好基友讨论了这个问题，最终得出了一个合情合理的答案：

首先我们知道android从源码到apk文件的大体流程：

Java源码 —> Class文件 —> Dex文件 —> apk

也就是说CLASS的作用时间最多是在Class文件 —> apk这个过程，这个过程是由android系统来进行编译和打包的，那么能够操作CLASS注解的人，也就只有android系统，换句话说，也就是来定制开发编译过程的开发人员来做。

仔细一想，这样应该是合情合理，RetentionPolicy.SOURCE仅仅是给应用层开发人员用的，RetentionPolicy.CLASS 需要应用层和底层系统开发人员配合使用的，所以仅仅是应用层开发的我们是一脸懵逼，意识CLASS和SOURCE的区别。

顺便说一句，自定义注解默认是RetentionPolicy.CLASS，看的出来Java是推荐把注解放到.class中去的。

总结
这是我的思考和总结，如果你有其他的想法和理解，千万不要忘记留言，众人拾柴火焰高，大家一起努力，一定会找到更正确的答案。
