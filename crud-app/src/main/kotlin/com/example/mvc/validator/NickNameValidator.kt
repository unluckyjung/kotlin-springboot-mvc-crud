package com.example.mvc.validator

import com.example.mvc.annotation.ValidNickName
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class NickNameValidator : ConstraintValidator<ValidNickName, String> {
    private val inValidNickNames = mutableListOf("goodall", "yoonsung")

    override fun initialize(constraintAnnotation: ValidNickName?) {
        constraintAnnotation?.let {
            inValidNickNames.addAll(it.inValidNickNames)
        }
    }

    override fun isValid(nickName: String?, context: ConstraintValidatorContext?): Boolean {
        return inValidNickNames.contains(nickName).not()
    }
}
