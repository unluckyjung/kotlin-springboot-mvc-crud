package com.example.mvc.validator

import com.example.mvc.annotation.ValidNickName
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class NickNameValidator: ConstraintValidator<ValidNickName, String>{
    private val inValidNickNames = listOf("goodall", "yoonsung")

    override fun isValid(nickName: String?, context: ConstraintValidatorContext?): Boolean {
        return inValidNickNames.contains(nickName).not()
    }
}
