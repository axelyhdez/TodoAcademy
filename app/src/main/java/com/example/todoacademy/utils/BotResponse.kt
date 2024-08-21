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
                "Para verificar los pagos debes subirlos a la app, después un asesor revisará el pago y lo confirmará\nUna vez confirmado el pago el estatus cambiará en tu aplicación y podrás subir el siguiente pago"+
                        "\nUna vez subido el siguiente pago, el proceso es el mismo, debes esperar a que sea validado para subir el siguiente, de lo contrario se perderá el pago"+
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
                "Para verificar los pagos debes subirlos a la app, después un asesor revisará el pago y lo confirmará\nUna vez confirmado el pago el estatus cambiará en tu aplicación y podrás subir el siguiente pago"+
                        "\nUna vez subido el siguiente pago, el proceso es el mismo, debes esperar a que sea validado para subir el siguiente, de lo contrario se perderá el pago"+
                        "\n¿Puedo ayudarte en algo más?"
            }

            message.contains("cursos")->{
                "Los cursos o capacitaciones que puedes tomar se encuentran en la pantalla principal\nConstantemente se actualizan y se hacen cambios a los mismos"+
                        "\nSi deseas consultar un curso o certificación en especial comunicaté con tu asesor o con atención a clientes"+
                        "\n¿Puedo ayudarte en algo más?"
            }

            message.contains("papeles")->{
                "Para firma, recepción o entrega de papeles comunicate con tu asesor en TODO o con atención a clientes, para así acordar una reunión"
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
                "Soy un chatbot creado para ayudarte, ¿Necesitas ayuda?\n" +
                        " Para ver los temas en los que puedo ayudarte escribe 'Ayuda'"
            }

            message.contains("dónde estás")->{
                when(random){
                    0->"Me encuentro en la nube, \n" +
                            " Para ver los temas en los que puedo ayudarte escribe 'Ayuda'"
                    1->"Estoy en tu mano\n" +
                            " Para ver los temas en los que puedo ayudarte escribe 'Ayuda'"
                    2->"En las oficinas de DEVELOP\n" +
                            " Para ver los temas en los que puedo ayudarte escribe 'Ayuda'"
                    else->"Error"
                }
            }
             message.contains("no")->{
                 "¡Ten un buen día!, si necesitas ayuda escribe 'Ayuda' para así ayudarte"
             }

            else -> {
                when (random) {
                    0 -> "No entiendo, \n" +
                            " Para ver los temas en los que puedo ayudarte escribe 'Ayuda'"
                    1 -> "Prueba preguntando algo diferente\n" +
                            " Para ver los temas en los que puedo ayudarte escribe 'Ayuda'"
                    2 -> "Intenta de nuevo\n" +
                            " Para ver los temas en los que puedo ayudarte escribe 'Ayuda'"
                    else -> "error"
                }
            }
        }
    }
}