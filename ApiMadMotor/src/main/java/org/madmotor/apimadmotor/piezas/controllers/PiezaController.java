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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<PiezaResponseDTO> getProductById(@PathVariable UUID id) {
        log.info("Buscando producto por id: " + id);
        return ResponseEntity.ok(piezaService.findById(id));
    }
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Producto a crear", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado"),
            @ApiResponse(responseCode = "400", description = "Producto no válido"),
    })
    @PostMapping()

    public ResponseEntity<PiezaResponseDTO> createProduct(@Valid @RequestBody PiezaCreateDTO productoCreateRequest) {
        log.info("Creando producto: " + productoCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(piezaService.save(productoCreateRequest));
    }
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Producto a actualizar", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "400", description = "Producto no válido"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
    })
    @PutMapping("/{id}")

    public ResponseEntity<PiezaResponseDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody PiezaUpdateDTO productoUpdateRequest) {
        log.info("Actualizando producto por id: " + id + " con producto: " + productoUpdateRequest);
        return ResponseEntity.ok(piezaService.update(id, productoUpdateRequest));
    }

    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Producto a actualizar", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "400", description = "Producto no válido"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<PiezaResponseDTO> updatePartialProduct(@PathVariable UUID id, @Valid @RequestBody PiezaUpdateDTO productoUpdateRequest) {
        log.info("Actualizando parcialmente producto por id: " + id + " con producto: " + productoUpdateRequest);
        return ResponseEntity.ok(piezaService.update(id, productoUpdateRequest));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto borrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        log.info("Borrando producto por id: " + id);
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
