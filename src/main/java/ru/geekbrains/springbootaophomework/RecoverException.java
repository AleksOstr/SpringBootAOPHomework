package ru.geekbrains.springbootaophomework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface RecoverException {
    Class<? extends RuntimeException>[] noRecoverFor() default {};
}
