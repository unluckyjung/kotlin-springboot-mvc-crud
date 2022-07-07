package com.example.mvc.annotation

import com.example.mvc.validator.NickNameValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [NickNameValidator::class])
@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FIELD,
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ValidNickName(
    val inValidNickNames: Array<String> = [],
    val message: String = "금지된 닉네임 입니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
