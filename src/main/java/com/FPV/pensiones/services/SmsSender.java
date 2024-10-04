package com.FPV.pensiones.services;

import org.springframework.stereotype.Service;

@Service
public class SmsSender {

    public void sendSms(String phoneNumber, String message) {
        // Simulación del envío de un SMS. Aquí deberías integrar un proveedor de SMS real
        System.out.println("Enviando SMS a: " + phoneNumber);
        System.out.println("Mensaje: " + message);
        // Ejemplo de integración con Twilio o Nexmo iría aquí
    }
}
