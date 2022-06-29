package com.example.mvc.controller

import com.example.mvc.domain.ValidateMember
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/validate")
class ValidateController2 {

    @GetMapping("/sample2")
    fun fun1(@Valid @ModelAttribute req: ValidateMember.Request): ValidateMember.Response {
        return ValidateMember.Response(
            name = req.name,
            age = req.age,
            email = req.email,
            phoneNumber = req.phoneNumber
        )
    }

    @PostMapping("/sample2")
    fun fun2(@Valid @RequestBody req: ValidateMember.Request, bindingResult: BindingResult): ResponseEntity<Any> {
        // validate 에서 잡힌 경우에도, 항상 해당 블럭으로 들어온다.

        if (bindingResult.hasErrors()) {
            val errorMsg = StringBuilder()

            bindingResult.allErrors.forEach {
                val tmp = it as FieldError
                errorMsg.append(tmp.field + ": " + it.defaultMessage + "\n")
            }
            return ResponseEntity.badRequest().body(errorMsg.toString())
        }

        return ResponseEntity.ok(
            ValidateMember.Response(
                name = req.name,
                age = req.age,
                email = req.email,
                phoneNumber = req.phoneNumber
            )
        )
    }
}
