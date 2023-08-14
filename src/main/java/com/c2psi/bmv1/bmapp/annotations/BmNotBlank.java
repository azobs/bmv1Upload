package com.c2psi.bmv1.bmapp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***********************************************************************
 * @BmNotBlank is different than @NotNull
 *      Illustration:
 *          -> null value don't pas @NotNull also @BmNotBlank
 *          -> "" (empty value) pass @NotNull but not pass @BmNotBlank
 *          -> "   " (blank value) pass @NotNull but not pass @BmNotBlank
 * @BmNotBlank is different than @NotEmpty
 *      Illustration:
 *          -> null value don't pass @NotEmpty (because it start by check if the string is notNull) but pass @BmNotBlank
 *          -> "" (empty value) don't pass @NotEmpty also @BmNotBlank
 *          -> "    " (blank value) pass @NotEmpty but not pass @BmNotBlank
 * @BmNotBlank is different than @NotBlank
 *      Illustration:
 *          -> null value don't pass @NotBlank (because it start by check if the string is notNull) but pass @BmNotBlank
 *          -> "" (empty value) don't pass @NotEmpty also @BmNotBlank
 *          -> "    " (blank value) don't pass @NotEmpty also @BmNotBlank
 * @BmNotBlank is different than @Size
 *      Illustration:
 *          -> null value pass @Size also @BmNotBlank
 *          -> "" (empty value) don't pass @Size if the min is > 0 but pass if min = 0 but don't pass @BmNotBlank
 *          -> "    " (blank value) don't pass @Size if the lenght is not between min and max but pass
 * if that lenght is between min and max but don't pass @BmNotBlank
 *
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD, ElementType.METHOD})
public @interface BmNotBlank {
    String message() default "The value of this field can't be blank value when it is not null";
}
