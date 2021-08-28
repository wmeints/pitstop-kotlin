package com.infosupport.pitstop.notifications.eventlisteners

import com.infosupport.pitstop.notifications.events.JobCompleted
import io.quarkus.mailer.Mail
import io.quarkus.mailer.reactive.ReactiveMailer
import io.smallrye.mutiny.Uni
import io.vertx.core.json.JsonObject
import org.eclipse.microprofile.reactive.messaging.Incoming

class JobCompletedEventListener(private val mailer: ReactiveMailer) {
    @Incoming("job-completed")
    fun consumeJobCompleted(payload: JsonObject): Uni<Mail> {
        return Uni.createFrom()
            .item(payload.mapTo(JobCompleted::class.java))
            .map { item ->
                val messageBody = """
                    Hello ${item.customer.name},
                    
                    The work on your car is completed. The mechanic left the following notes about the work performed:
                    
                    ${item.notes}
                    
                    You're welcome to pick up your car at the shop at your convenience!
                    
                    Kind regards,
                    
                    The friendly folks at pitstop.
                """.trimIndent()

                Mail.withText(
                    "test@domain.org",
                    "The work on your car is completed.",
                    messageBody
                )
            }.invoke { message ->
                mailer.send(message)
            }
    }
}