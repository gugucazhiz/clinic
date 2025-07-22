package controllers;

import java.util.List;
import models.Status;
import models.Consulta;
import models.Paciente;
import play.mvc.Controller;

public class Pacientes extends Controller {

    public static void form() {
        List<Consulta> consultas = Consulta.findAll();
        render(consultas);
    }

    public static void verificacao(Paciente paciente) {
        List<Paciente> pacientes = Paciente.findAll();

        boolean telefoneExiste = pacientes.stream()
            .anyMatch(p -> p.telefone != null && p.telefone.equals(paciente.telefone));

        if (telefoneExiste) {
            renderTemplate("Pacientes/detalhar.html", paciente);
        } else {
            renderTemplate("Pacientes/form.html", paciente);
        }
    }

    public static void salvar(Paciente paciente) {
            paciente.save();
            detalhar(paciente);
     
    }

    public static void detalhar(Paciente paciente) {
        render(paciente);
    }

    public static void login() {
        render();
    }
}
