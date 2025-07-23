package controllers;

import java.util.List;
import models.Status;
import models.Paciente;
import play.mvc.Controller;

public class Pacientes extends Controller {

	
	public static void form(Paciente paciente) {
		render();
	}
	
    public static void salvar(Paciente paciente) {
    	if(isEmpty(paciente.nome) || isEmpty(paciente.telefone) || (paciente.dataNascimento == null) || isEmpty(paciente.cpf) || isEmpty(paciente.convenio)) {
    		flash.error("Todos os campos devem ser preenchidos");
    		form(paciente);
    	} else {
    		paciente.save();
            detalhar(paciente); 
    	}
            
    }
    public static void detalhar(Paciente paciente) {
        render(paciente);
    }

    public static void login() {
        render();
    }
    public static void lista_all(){
        List<Paciente> pacientes = Paciente.findAll();
        render(pacientes);
    }

    private static boolean isEmpty(String value){
        return value == null || value.trim().isEmpty();
    }
    
    public static void verificacao(Paciente paciente) {
        List<Paciente> pacientes = Paciente.findAll();

        boolean telefoneExiste = pacientes.stream()
            .anyMatch(p -> p.telefone != null && p.telefone.equals(paciente.telefone));

        if (telefoneExiste) {
            Paciente pacienteEncontrado = Paciente.find("SELECT p FROM Paciente p WHERE p.telefone = :telefone")
            .bind("telefone", paciente.telefone)
            .first();
            if(pacienteEncontrado == null){
                flash.error("Paciente Não encontrado!");
            }
            else{
                detalhar(pacienteEncontrado);
            }
        } else{
            renderTemplate("Pacientes/form.html", paciente);
        }
    }
    
}