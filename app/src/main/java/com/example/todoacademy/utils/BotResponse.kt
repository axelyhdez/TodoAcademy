package com.example.todoacademy.utils

object BotResponse {

    fun basicResponses(_message: String): String{
        val random = (0..2).random()
        val message =_message.lowercase()

        return when {

            ///Saludo
            message.contains("hola") -> {
                "¡Hola!, me alegro de verte por aquí\n Para ver los temas en los que puedo ayudarte escribe 'Ayuda'"
            }

            //Ayuda
            message.contains("ayuda") -> {
                "¿Acerca de qué tema puedo ayudarte?\n1.Pagos\n2.Cursos\n3.Papeles"
            }

            message.contains("1")->{
                "Para verificar los pagos debes subirlos a la app, después un asesor revisará el pago y lo confirmará\nUna vez confirmado el pago se te notificará"+
                        "\n¿Puedo ayudarte en algo más?"
            }

            message.contains("2")->{
                "Los cursos o capacitaciones que puedes tomar se encuentran en la pantalla principal\nConstantemente se actualizan y se hacen cambios a los mismos"+
                "\nSi deseas consultar un curso o certificación en especial comunicaté con tu asesor o con atención a clientes"+
                "\n¿Puedo ayudarte en algo más?"
            }

            message.contains("3")->{
                "Para firma, recepción o entrega de papeles comunicate con tu asesor en TODO o con atención a clientes"+
                        "\n¿Puedo ayudarte en algo más?"
            }

            message.contains("pagos")->{
                "Para verificar los pagos debes subirlos a la app, después un asesor revisará el pago y lo confirmará\nUna vez confirmado el pago se te notificará"+
                        "\n¿Puedo ayudarte en algo más?"
            }

            message.contains("cursos")->{
                "Los cursos o capacitaciones que puedes tomar se encuentran en la pantalla principal\nConstantemente se actualizan y se hacen cambios a los mismos"+
                        "\nSi deseas consultar un curso o certificación en especial comunicaté con tu asesor o con atención a clientes"+
                        "\n¿Puedo ayudarte en algo más?"
            }

            message.contains("papeles")->{
                "Para firma, recepción o entrega de papeles comunicate con tu asesor en TODO o con atención a clientes"
                "\n¿Puedo ayudarte en algo más?"
            }

            message.contains("si")->{
                "¿Acerca de qué tema puedo ayudarte?\n" +
                        "1.Pagos\n" +
                        "2.Cursos\n" +
                        "3.Papeles"
            }

            //Extras
            message.contains("quién eres") -> {
                "Soy un chatbot creado para ayudarte, ¿Necesitas ayuda?"
            }

            message.contains("dónde estás")->{
                when(random){
                    0->"Me encuentro en la nube"
                    1->"Estoy en tu mano"
                    2->"¿Por qué quieres saber eso?"
                    else->"Error"
                }
            }

            else -> {
                when (random) {
                    0 -> "No entiendo..."
                    1 -> "Prueba preguntando algo diferente"
                    2 -> "Intenta de nuevo"
                    else -> "error"
                }
            }
        }
    }
}