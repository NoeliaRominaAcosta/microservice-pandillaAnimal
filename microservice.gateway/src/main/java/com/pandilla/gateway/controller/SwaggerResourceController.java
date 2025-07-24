package com.pandilla.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SwaggerResourceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    private final WebClient webClient;

    public SwaggerResourceController() {
        this.webClient = WebClient.builder().build();
    }

    // Endpoint para obtener la lista de servicios con Swagger
    @GetMapping("/swagger-resources")
    public ResponseEntity<List<Map<String, String>>> swaggerResources() {
        List<Map<String, String>> resources = List.of(
                Map.of("name", "Animal Service", "url", "/animal/v3/api-docs", "swaggerVersion", "3.0"),
                Map.of("name", "Adoptions Service", "url", "/adoptions/v3/api-docs", "swaggerVersion", "3.0"),
                Map.of("name", "Finances Service", "url", "/finances/v3/api-docs", "swaggerVersion", "3.0"),
                Map.of("name", "Health Service", "url", "/health/v3/api-docs", "swaggerVersion", "3.0"),
                Map.of("name", "Pet Shop Service", "url", "/petShop/v3/api-docs", "swaggerVersion", "3.0")
        );

        return ResponseEntity.ok(resources);
    }

    // Endpoint alternativo para obtener servicios dinámicamente desde Eureka
    @GetMapping("/swagger-services")
    public ResponseEntity<List<Map<String, String>>> swaggerServices() {
        try {
            List<String> serviceNames = List.of("animal", "adoptions", "finances", "health", "petShop");

            List<Map<String, String>> services = serviceNames.stream()
                    .filter(serviceName -> isServiceAvailable(serviceName))
                    .map(serviceName -> Map.of(
                            "name", formatServiceName(serviceName),
                            "url", "/" + serviceName + "/v3/api-docs",
                            "swaggerVersion", "3.0"
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(services);
        } catch (Exception e) {
            // Si hay error con Eureka, devuelve la lista estática
            return swaggerResources();
        }
    }

    // Proxy para obtener la documentación de cada microservicio
    @GetMapping("/{service}/v3/api-docs")
    public Mono<String> getServiceApiDocs(@PathVariable String service) {
        String serviceUrl = "http://" + service + "/v3/api-docs";

        return webClient.get()
                .uri(serviceUrl)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("{}"); // Retorna JSON vacío si el servicio no está disponible
    }

    // Método auxiliar para verificar si un servicio está disponible
    private boolean isServiceAvailable(String serviceName) {
        try {
            return !discoveryClient.getInstances(serviceName).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // Método auxiliar para formatear nombres de servicio
    private String formatServiceName(String serviceName) {
        return serviceName.substring(0, 1).toUpperCase() +
                serviceName.substring(1) + " Service";
    }

    // Endpoint para configuración de Swagger UI
    @GetMapping("/swagger-resources/configuration/ui")
    public ResponseEntity<Map<String, Object>> swaggerUiConfiguration() {
        Map<String, Object> config = new java.util.HashMap<>();
        config.put("deepLinking", true);
        config.put("displayOperationId", false);
        config.put("defaultModelsExpandDepth", 1);
        config.put("defaultModelExpandDepth", 1);
        config.put("defaultModelRendering", "example");
        config.put("displayRequestDuration", false);
        config.put("docExpansion", "none");
        config.put("filter", false);
        config.put("maxDisplayedTags", null);
        config.put("operationsSorter", null);
        config.put("showExtensions", false);
        config.put("tagsSorter", null);
        config.put("validatorUrl", "");
        config.put("apisSorter", "alpha");
        config.put("jsonEditor", false);
        config.put("showRequestHeaders", false);
        config.put("supportedSubmitMethods", List.of("get", "put", "post", "delete", "options", "head", "patch", "trace"));

        return ResponseEntity.ok(config);
    }

    // Endpoint para configuración de seguridad de Swagger
    @GetMapping("/swagger-resources/configuration/security")
    public ResponseEntity<Map<String, Object>> swaggerSecurityConfiguration() {
        Map<String, Object> config = new java.util.HashMap<>();
        config.put("clientId", "");
        config.put("clientSecret", "");
        config.put("realm", "");
        config.put("appName", "Gateway Swagger UI");
        config.put("scopeSeparator", " ");
        config.put("additionalQueryStringParams", new java.util.HashMap<>());
        config.put("useBasicAuthenticationWithAccessCodeGrant", false);

        return ResponseEntity.ok(config);
    }
}