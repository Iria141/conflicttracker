package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.FormularioConflict;
import com.example.conflicttracker.entity.Conflict;
import com.example.conflicttracker.entity.ConflictStatus;
import com.example.conflicttracker.service.ConflictService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/web/conflicts")
public class WebConflicController {

    private final ConflictService conflictService;

    public WebConflicController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    //Listar y filtrar por estado
    @GetMapping
    public String listar(@RequestParam(required = false) String status, Model model) {

        List<Conflict> conflicts;

        if (status == null || status.trim().isEmpty()) {
            conflicts = conflictService.obtenerTodos();
        } else {
            ConflictStatus parsed = parseStatus(status);
            conflicts = conflictService.listarPorEstado(parsed);
        }

        model.addAttribute("conflicts", conflicts);
        model.addAttribute("statuses", ConflictStatus.values()); // para el select
        model.addAttribute("selectedStatus", status == null ? "" : status);

        return "conflicts/list";
    }

//formulario
    @GetMapping("/new")
    public String formNuevo(Model model) {
        model.addAttribute("conflictForm", new FormularioConflict());
        model.addAttribute("statuses", ConflictStatus.values());
        return "conflicts/new";
    }

  //crear nuevo conflicto y mensaje de guardado
    @PostMapping
    public String crear(@Valid @ModelAttribute("conflictForm") FormularioConflict form,
                        BindingResult br,
                        Model model,
                        RedirectAttributes ra) {

        if (br.hasErrors()) {
            model.addAttribute("statuses", ConflictStatus.values());
            return "conflicts/new";
        }

        Conflict c = new Conflict();
        c.setNombre(form.getNombre());
        c.setFechaInicio(form.getFechaInicio());
        c.setEstado(form.getEstado());
        c.setDescripcion(form.getDescripcion());

        conflictService.guardar(c);
// mensaje de OK esta creado
        model.addAttribute("success",
                "✅ Conflicto guardado correctamente");

        // opcional: limpiar formulario
        model.addAttribute("conflictForm", new FormularioConflict());
        model.addAttribute("statuses", ConflictStatus.values());

        return "conflicts/new";
    }


    private ConflictStatus parseStatus(String status) {
        String s = status.trim().toUpperCase();
        return switch (s) {
            case "ACTIVE" -> ConflictStatus.ACTIVO; //usamos el mismo parseo
            case "FROZEN" -> ConflictStatus.CONGELADO;
            case "ENDED"  -> ConflictStatus.FINALIZADO;
            default -> ConflictStatus.valueOf(s);
        };
    }
}
