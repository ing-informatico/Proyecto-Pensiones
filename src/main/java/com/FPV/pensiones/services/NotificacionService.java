package com.FPV.pensiones.services;

public interface NotificacionService {
    void enviarNotificacion(String destinatario, String mensaje, String tipoNotificacion);
}
