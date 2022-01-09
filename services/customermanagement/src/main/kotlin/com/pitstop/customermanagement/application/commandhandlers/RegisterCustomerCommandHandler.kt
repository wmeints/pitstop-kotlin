package com.pitstop.customermanagement.application.commandhandlers

import com.pitstop.customermanagement.common.CommandHandler
import com.pitstop.customermanagement.domain.aggregates.customers.commands.RegisterCustomerCommand
import com.pitstop.customermanagement.domain.aggregates.customers.commands.RegisterCustomerCommandResult
import org.springframework.stereotype.Component

@Component
class RegisterCustomerCommandHandler: CommandHandler<RegisterCustomerCommand, RegisterCustomerCommandResult> {
    override fun execute(request: RegisterCustomerCommand): RegisterCustomerCommandResult {
        TODO("Not yet implemented")
    }
}