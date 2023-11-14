package org.madmotor.apimadmotor.piezas.controllers;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.madmotor.apimadmotor.piezas.dto.PiezaCreateDTO;
import org.madmotor.apimadmotor.piezas.dto.PiezaResponseDTO;
import org.madmotor.apimadmotor.piezas.dto.PiezaUpdateDTO;
import org.madmotor.apimadmotor.piezas.services.PiezaService;
import org.madmotor.apimadmotor.utils.PageResponse;
import org.madmotor.apimadmotor.utils.PaginationLinksUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("api/v1/piezas")
public class PiezaController {
    private final PiezaService piezaService;
    private final PaginationLinksUtils paginationLinksUtils;

    @Autowired
    public PiezaController(PiezaService piezaService, PaginationLinksUtils paginationLinksUtils) {
        this.piezaService = piezaService;
        this.paginationLinksUtils = paginationLinksUtils;
    }
    @GetMapping()
    public ResponseEntity<PageResponse<PiezaResponseDTO>> getAllPiezas(
            @RequestParam (required = false) Optional<String> name,
            @RequestParam (required = false) Optional<String> description,
            @RequestParam (required = false) Optional<Double> price,
            @RequestParam (required = false) Optional<Integer> stock,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order,
            HttpServletRequest request
    ){
        log.info("Buscando piezas con los siguientes filtros:"+name+" "+description+" "+price+" "+stock);
        Sort sort =order.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        Page<PiezaResponseDTO> pageResult = piezaService.findAll(name,description,price,stock, PageRequest.of(page,size,sort));
        return ResponseEntity.ok()
                .header("link", paginationLinksUtils.createLinkHeader(pageResult, uriBuilder))
                .body(PageResponse.of(pageResult,sortBy,order));

    }

    @GetMapping("/{id}")
    public ResponseEntity<PiezaResponseDTO> getProductById(@PathVariable UUID id) {
        log.info("Buscando pieza por id: " + id);
        return ResponseEntity.ok(piezaService.findById(id));
    }

    @PostMapping()

    public ResponseEntity<PiezaResponseDTO> createProduct(@Valid @RequestBody PiezaCreateDTO piezaCreateDTO) {
        log.info("Creando pieza: " + piezaCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(piezaService.save(piezaCreateDTO));
    }

    @PutMapping("/{id}")

    public ResponseEntity<PiezaResponseDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody PiezaUpdateDTO piezaUpdateDTO) {
        log.info("Actualizando pieza por id: " + id + " con pieza: " + piezaUpdateDTO);
        return ResponseEntity.ok(piezaService.update(id, piezaUpdateDTO));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<PiezaResponseDTO> updatePartialPieza(@PathVariable UUID id, @Valid @RequestBody PiezaUpdateDTO piezaUpdateDTO) {
        log.info("Actualizando parcialmente pieza por id: " + id + " con pieza: " +piezaUpdateDTO );
        return ResponseEntity.ok(piezaService.update(id, piezaUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePieza(@PathVariable UUID id) {
        log.info("Borrando pieza por id: " + id);
        piezaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
